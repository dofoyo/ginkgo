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
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${thePath}")
	private String thePath;
	
	private String boardsjsonFile;
	private String stagesjsonFile;
	private String projectsjsonFile;
	private List<BoardEntity> boards = null;
	private List<StageEntity> stages = null;
	private List<ProjectEntity> projects = null;

	@Override
	public List<BoardEntity> getBoards() {
		if(this.boards == null){
			this.init();
		}
		return this.boards;
	}

	@Override
	public BoardEntity getBoard(String boardid) {
		if(this.boards == null){
			this.init();
		}
		return this.getBoardEntityById(boardid);
	}
	
	private BoardEntity getBoardEntityById(String boardid){
		if(this.boards == null){
			this.init();
		}
		
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
	
	private ProjectEntity getProjectEntityByName(String projectname){
		ProjectEntity pe = null;
		for(ProjectEntity p : this.projects){
			if(p.getProjectname().equals(projectname)){
				pe = p;
				break;
			}
		}
		return pe;
	}
	
	
	private void init(){
		System.out.println("do init............");
		
		boardsjsonFile = this.thePath + "boards.json";
		stagesjsonFile = this.thePath + "stages.json";
		projectsjsonFile = this.thePath + "projects.json";
		
		boards = new ArrayList<BoardEntity>();
		stages = new ArrayList<StageEntity>();
		projects = new ArrayList<ProjectEntity>();

		
		//System.out.println("boardsjsonFile: " + this.boardsjsonFile);
		//System.out.println("stagesjsonFile: " + this.boardsjsonFile);
		//System.out.println("projectsjsonFile: " + this.boardsjsonFile);
		
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

	@Override
	public void deleteProjects(List<ProjectEntity> projectEntitys) {
		for(ProjectEntity p : projectEntitys){
			ProjectEntity pe = getProjectEntityByName(p.getProjectname());
			if(pe != null){
				this.projects.remove(pe);
			}
		}
		this.writeToFile(projectsjsonFile, this.projects);		
	}

	@Override
	public void saveProjects(List<ProjectEntity> projectEntitys) {
		for(ProjectEntity p : projectEntitys){
			ProjectEntity pe = getProjectEntityByName(p.getProjectname());
			if(pe == null){
				p.setOrderNo(0);
				p.setStageid("0");
				p.setProjectid(UUID.randomUUID().toString());
				this.projects.add(p);
			}
		}		
		this.writeToFile(projectsjsonFile, this.projects);		
		
	}

	@Override
	public void refreshProjectsTaskids(String projectid, Set<String> taskids) {
		ProjectEntity pe = getProjectEntityById(projectid);
		StringBuffer sb = new StringBuffer();
		for(String taskid : taskids){
			sb.append((sb.length()==0 ? "" : ",") + taskid);
		}

		pe.setTaskids(sb.toString());
		this.writeToFile(projectsjsonFile, this.projects);		
	}

	@Override
	public void saveAndDeleteProjects(List<String> saves, List<String> deletes) {
		if(this.boards == null){
			this.init();
		}
		
		ProjectEntity pe = null;
		for(String projectname : saves){
			pe = getProjectEntityByName(projectname);
			if(pe == null){
				pe = new ProjectEntity();
				pe.setProjectname(projectname);
				this.projects.add(pe);
			}
		}		
		
		for(String projectname : deletes){
			pe = getProjectEntityByName(projectname);
			if(pe != null){
				this.projects.remove(pe);
			}
		}
		
		this.writeToFile(projectsjsonFile, this.projects);				
	}

}