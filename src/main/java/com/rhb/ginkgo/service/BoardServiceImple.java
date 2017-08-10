package com.rhb.ginkgo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rhb.ginkgo.api.dto.BoardDTO;
import com.rhb.ginkgo.api.dto.StageDTO;
import com.rhb.ginkgo.api.dto.TaskDTO;
import com.rhb.ginkgo.api.dto.TaskDetailDTO;
import com.rhb.ginkgo.api.dto.ProjectDTO;
import com.rhb.ginkgo.repository.AttachmentRepository;
import com.rhb.ginkgo.repository.BoardRepository;
import com.rhb.ginkgo.repository.PersonRepository;
import com.rhb.ginkgo.repository.TaskRepository;
import com.rhb.ginkgo.repository.TaskdetailRepository;
import com.rhb.ginkgo.repository.entity.AttachmentEntity;
import com.rhb.ginkgo.repository.entity.BoardEntity;
import com.rhb.ginkgo.repository.entity.PersonEntity;
import com.rhb.ginkgo.repository.entity.StageEntity;
import com.rhb.ginkgo.repository.entity.TaskEntity;
import com.rhb.ginkgo.repository.entity.TaskdetailEntity;
import com.rhb.ginkgo.repository.entity.TaskuserEntity;
import com.rhb.ginkgo.repository.entity.ProjectEntity;

@Service("BoardServiceImple")
public class BoardServiceImple implements BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Autowired
	private TaskdetailRepository taskdetailRepository;
	
	@Value("${theTaskid}")
	private String theTaskid;
	
	private Map<String, String> persons = null;
	
	private void refreshPersons(){
		this.persons = new HashMap<String,String>();
		
		List<PersonEntity> ps = personRepository.findAll();
		
		for(PersonEntity p : ps){
			this.persons.put(p.getEmpnum(), p.getRealname());
		}
	}

	@Override
	public Map<String, String> getBoards() {
		List<BoardEntity> boards = boardRepository.getBoards();
		Map<String,String> bs = new HashMap<String,String>();
		for(BoardEntity b : boards){
			bs.put(b.getBoardid(), b.getBoardname());
		}
		return bs;
	}

	@Override
	public BoardDTO getBoard(String boardid, boolean refresh) {
		if(refresh){
			this.refreshProjects();
		}
		
		BoardEntity be = boardRepository.getBoard(boardid);
		BoardDTO boardDTO = new BoardDTO(be.getBoardid(),be.getBoardname());
		
		List<StageEntity> stages = boardRepository.getStages(boardid);
		
		List<ProjectEntity> projects;
		
		for(StageEntity se : stages){
			StageDTO stage = new StageDTO(se.getStageid(),se.getStagename(),se.getOrderNo());
			projects = boardRepository.getProjects(se.getStageid());
			for(ProjectEntity te : projects){
				ProjectDTO project = new ProjectDTO(te.getProjectid(),te.getProjectname(),te.getDescription(),te.getOrderNo());
				stage.getProjects().add(project);
			}
			boardDTO.getStages().add(stage);
		}
		
		return boardDTO;
	}

	@Override
	public void updateProjectStageidAndOrder(String stageid, List<ProjectDTO> projects) {
		List<ProjectEntity> projectlist = new ArrayList<ProjectEntity>();
		int i = 0;
		for(ProjectDTO td : projects){
			ProjectEntity project = new ProjectEntity(stageid,td.getProjectid(),td.getProjectname(),td.getDescription(),i++);
			projectlist.add(project);
		}
		boardRepository.updateProjectStageidAndOrder(stageid, projectlist);
	}


	@Override
	public ProjectDTO getProject(String projectid, boolean refresh) {
		if(this.persons == null){
			this.refreshPersons();
		}
		
		if(refresh){
			this.refreshProjectsTaskids(projectid);
		}
	
		ProjectEntity pe = boardRepository.getProject(projectid);

		ProjectDTO projectDTO = new ProjectDTO();
		projectDTO.setProjectid(projectid);
		projectDTO.setProjectname(pe.getProjectname());

		
		String[] taskids = pe.getTaskids().split(",");
				
		for(String taskid : taskids){
			TaskEntity taskEntity = taskRepository.findOne(taskid);
			if(taskEntity!=null){
				TaskDTO taskDTO = new TaskDTO();
				taskDTO.setTaskid(taskid);
				taskDTO.setSubject(taskEntity.getSubject());
				String status = "";
				for(TaskuserEntity user : taskEntity.getTaskusers()){
					if(user.getType() == 2){
						continue;
					}else if(user.getType() == 0){
						if(user.getStatus() == 0){
							status = "(*)";
						}else{
							status = "";
						}
						taskDTO.addActor(persons.get(user.getEmpnum()) + status);
					}else if(user.getType() == 1){
						
						taskDTO.addOther(persons.get(user.getEmpnum()));
					}
				}
				
				projectDTO.addTask(taskDTO);
				
				TaskDetailDTO taskDetailDTO;
				/*
				TaskDetailDTO taskDetailDTO = new TaskDetailDTO();
				taskDetailDTO.setDateAndTime(taskEntity.getCreatetime());
				taskDetailDTO.setPerson(persons.get(taskEntity.getCreater()));  
				taskDetailDTO.setDone(taskEntity.getSubject());
				taskDTO.addTaskDetail(taskDetailDTO);
				*/
				
				taskDetailDTO = new TaskDetailDTO();
				taskDetailDTO.setDateAndTime(taskEntity.getCreatetime());
				taskDetailDTO.setPerson(persons.get(taskEntity.getCreater())); 
				taskDetailDTO.setDone(taskEntity.getContent());
				taskDTO.addTaskDetail(taskDetailDTO);
				
				taskDetailDTO = new TaskDetailDTO();
				taskDetailDTO.setDateAndTime(taskEntity.getCreatetime());
				taskDetailDTO.setPerson(persons.get(taskEntity.getCreater())); 
				taskDetailDTO.setDone(this.getAttachmentHtml(taskEntity.getAttachmentid())); 
				taskDTO.addTaskDetail(taskDetailDTO);
				
				for(TaskdetailEntity detail : taskEntity.getTaskdetails()){
					if(!detail.getContent().equals("发起任务") && detail.getIsrecall()!=2){
						taskDetailDTO = new TaskDetailDTO();
						taskDetailDTO.setDateAndTime(detail.getCreatetime());
						taskDetailDTO.setPerson(persons.get(detail.getEmpnum())); 
						taskDetailDTO.setDone(detail.getContent());
						if(detail.getAttachmentid()!=null && !detail.getAttachmentid().trim().isEmpty()){
							taskDetailDTO.setDone((detail.getContent().trim().isEmpty()? "" : detail.getContent()+"<br>") + this.getAttachmentHtml(detail.getAttachmentid()));	
						}
						taskDTO.addTaskDetail(taskDetailDTO);
					}
				}
			}
		}
		
		return projectDTO;
	}

	
	private String getAttachmentHtml(String attachmentids){
		String html = "";
		if(attachmentids!=null && !attachmentids.trim().isEmpty()){
			String[] ids = attachmentids.split(",");
			AttachmentEntity attachmentEntity = null;
			for(String id : ids){
				//System.out.println("attachementid = '" + id + "'");
				attachmentEntity = attachmentRepository.findOne(id);
				//System.out.println(attachmentEntity);
				if(attachmentEntity!=null){
					html += attachmentEntity.getHtml() + "<br>";
				}
			}
		}
		return html;
		
	}

	@Override
	public void refreshProjects() {
		String createRegexp = "create:";
		String removeRegexp = "remove:";
		
		int begin = 7;
		
		List<String> creates = new ArrayList<String>();
		List<String> removes = new ArrayList<String>();
 	
		TaskEntity taskEntity = taskRepository.findOne(theTaskid);
    	String projectName = null;
		for(TaskdetailEntity detail : taskEntity.getTaskdetails()){
			if((detail.getText_content().contains(createRegexp) || detail.getText_content().contains(removeRegexp)) && detail.getIsrecall()!=2){
				projectName = detail.getText_content().substring(begin);
				if(detail.getText_content().contains(createRegexp)){
					creates.add(projectName);					
				}else if(detail.getText_content().contains(removeRegexp)){
					removes.add(projectName);					
				}
			}
		}
		
		boardRepository.saveAndDeleteProjects(creates, removes);
	}
	
	@Override
	public void refreshProjectsTaskids(String projectid) {
		if(projectid!=null && !projectid.trim().isEmpty()){
			List<TaskdetailEntity> details = taskdetailRepository.findByIsrecallNotAndTextcontent(projectid);
			
			//System.out.println("findByContentLike(" + projectid + "), size = " + details.size());
			
			Set<String> taskids = new HashSet<String>();
			for(TaskdetailEntity detail : details){
				if(!detail.getTaskid().equals(theTaskid)){
					taskids.add(detail.getTaskid());
				}
			}
			boardRepository.refreshProjectsTaskids(projectid, taskids);
		
		}
	}
}
