package com.rhb.ginkgo.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhb.ginkgo.api.dto.ProjectDTO;
import com.rhb.ginkgo.repository.entity.BoardEntity;
import com.rhb.ginkgo.repository.entity.StageEntity;
import com.rhb.ginkgo.repository.entity.ProjectEntity;

@Repository("BoardRepositoryImpl")
public class BoardRepositoryImpl implements BoardRepository{
	private String boardsjsonFile = "d:\\workspace\\boards.json";
	private String stagesjsonFile = "d:\\workspace\\stages.json";
	private String projectsjsonFile = "d:\\workspace\\projects.json";
	private List<BoardEntity> boards = new ArrayList<BoardEntity>();
	private List<StageEntity> stages = new ArrayList<StageEntity>();
	private List<ProjectEntity> projects = new ArrayList<ProjectEntity>();

	public BoardRepositoryImpl(){
		//System.out.println("*********** init BoardRepository ********");
		this.init();
	}

	@Override
	public List<BoardEntity> getBoards() {
		return this.boards;
	}

	@Override
	public BoardEntity getBoard(String boardid) {
		return this.getBoardEntityById(boardid);
	}
	
	private BoardEntity getBoardEntityById(String boardid){
		BoardEntity boardEntity = null;
		for(BoardEntity b : boards){
			if(b.getBoardid().equals(boardid)){
				boardEntity  = b;
				break;
			}
		}
		return boardEntity;
	}

	@Override
	public List<StageEntity> getStages(String boardId) {
		List<StageEntity> l = new ArrayList<StageEntity>();
		for(StageEntity se : stages){
			if(se.getBoardid().equals(boardId)){
				l.add(se);
			}
		}
		
		Collections.sort(l, new Comparator<StageEntity>(){
			public int compare(StageEntity t1, StageEntity t2) {
				return t1.getOrderNo().compareTo(t2.getOrderNo());
			}
			
		});
		
		return l;
	}

	@Override
	public List<ProjectEntity> getProjects(String stageId) {
		List<ProjectEntity> l = new ArrayList<ProjectEntity>();
		for(ProjectEntity te : this.projects){
			if(te.getStageid().equals(stageId)){
				l.add(te);
			}
		}
		
		Collections.sort(l, new Comparator<ProjectEntity>(){
			public int compare(ProjectEntity t1, ProjectEntity t2) {
				return t1.getOrderNo().compareTo(t2.getOrderNo());
			}
			
		});
		
		return l;
	}

	@Override
	public void updateProjectStageidAndOrder(String stageId, List<ProjectEntity> projectlist) {
		for(ProjectEntity te : projectlist){
			ProjectEntity t = this.getProjectEntityById(te.getProjectid());
			t.setOrderNo(te.getOrderNo());
			t.setStageid(stageId);
			//this.projects.get(te.getProjectid()).setOrderNo(te.getOrderNo());
			
		}
		this.writeToFile(projectsjsonFile, this.projects);
		//System.out.println("...............updated projects. ");
	}
	
	private ProjectEntity getProjectEntityById(String projectid){
		ProjectEntity pe = null;
		for(ProjectEntity p : this.projects){
			if(p.getProjectid().equals(projectid)){
				pe = p;
				break;
			}
		}
		return pe;
	}
	
	private void init(){
		System.out.println("do init............");
		
		ObjectMapper mapper = new ObjectMapper();
    	try {
        	JavaType boardsjavaType =  mapper.getTypeFactory().constructParametricType(List.class,BoardEntity.class); 
        	boards =  (List<BoardEntity>)mapper.readValue(new File(boardsjsonFile), boardsjavaType);
        	

        	JavaType stagejavaType =  mapper.getTypeFactory().constructParametricType(List.class, StageEntity.class); 
        	stages =  (List<StageEntity>)mapper.readValue(new File(stagesjsonFile), stagejavaType);

        	
        	JavaType projectsjavaType =  mapper.getTypeFactory().constructParametricType(List.class, ProjectEntity.class); 
        	projects =  (List<ProjectEntity>)mapper.readValue(new File(projectsjsonFile), projectsjavaType);

    	} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveProject(ProjectEntity projectEntity) {
		this.projects.add(projectEntity);
		this.writeToFile(projectsjsonFile, this.projects);
	}
	
	private void writeToFile(String jsonfile, Object object){
		ObjectMapper mapper = new ObjectMapper();
    	try {
			mapper.writeValue(new File(jsonfile),object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProjectidAndTaskid(String projectid, String taskid) {
		ProjectEntity pe = getProjectEntityById(projectid);
		String taskids = pe.getTaskids();
		if(!taskid.trim().isEmpty() && !taskids.contains(taskid)){
			pe.setTaskids((taskids.trim().isEmpty()? "" : taskids+",") + taskid);
		}
		
		///System.out.println("before: " + taskids);
		//System.out.println("after: " + pe.getTaskids());

		this.writeToFile(projectsjsonFile, this.projects);
	}

	@Override
	public ProjectEntity getProject(String projectid) {
		return this.getProjectEntityById(projectid);
	}

	@Override
	public void removeTaskidFromProject(String projectid, String taskid) {
		ProjectEntity pe = getProjectEntityById(projectid);
		String taskids = pe.getTaskids();
		String t = taskids.replace(taskid, "");
		t = t.replace(",,", ",");
		if(t.endsWith(",")){
			t = t.substring(0, t.length()-1);
		}
		
		pe.setTaskids(t);
		
		//System.out.println("before: " + taskids);
		//System.out.println("after: " + pe.getTaskids());
	
		this.writeToFile(projectsjsonFile, this.projects);		
	}

	@Override
	public void deleteProject(String projectid) {
		ProjectEntity pe = getProjectEntityById(projectid);
		this.projects.remove(pe);
		this.writeToFile(projectsjsonFile, this.projects);		
	}

}