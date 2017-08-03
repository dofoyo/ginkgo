package com.rhb.ginkgo.api;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhb.ginkgo.api.dto.BoardDTO;
import com.rhb.ginkgo.api.dto.ProjectDTO;
import com.rhb.ginkgo.service.BoardService;

@RestController
public class BoardControllerImp implements BoardController{
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board")
    public ResponseContent<BoardDTO> getBoard(@RequestParam(value="id", defaultValue="1") String boardid) {
    	//System.out.println("boardid: " + boardid);	
        return new ResponseContent<BoardDTO>(ResponseEnum.SUCCESS,boardService.getBoard(boardid));
    }
    
    @PutMapping("/stage")
    public void updateStage(@RequestParam(value="id") String stageid, @RequestBody String body){
    	//System.out.println("update Stage " + stageid + ", " + body);
    	
    	List<ProjectDTO> projects = null;
    	try {
        	ObjectMapper mapper = new ObjectMapper();
        	JavaType javaType =  mapper.getTypeFactory().constructParametricType(ArrayList.class, ProjectDTO.class); 
        	projects =  (List<ProjectDTO>)mapper.readValue(body, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	if(projects != null){
    		boardService.updateProjectStageidAndOrder(stageid, projects);
    	}
    }
    
    @PutMapping("/project")
    public void createProject(@RequestParam(value="id") String projectid, @RequestBody String body){
    	//System.out.println("save project " + projectid + ", " + body);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			ProjectDTO projectDTO = mapper.readValue(body, ProjectDTO.class);
			boardService.createProject(projectDTO);
			//System.out.println(projectDTO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @PutMapping("/task")
    public ResponseContent<String> addTask(@RequestParam(value="id") String projectid_taskid, @RequestBody String body){
    	//System.out.println("add Task, projectid_taskid=" + projectid_taskid);
    	String[] ids = projectid_taskid.split(",");
    	String projectid = ids[0];
    	String taskid = ids[1];
		boardService.updateProjectidAndTaskid(projectid,taskid);
		return new ResponseContent<String>(ResponseEnum.SUCCESS,new String());
    }

    
    
	@GetMapping("/projects")
    public ResponseContent<ProjectDTO> getProject(@RequestParam(value="id") String projectid) {
    	//System.out.println("projectid: " + projectid);	
        return new ResponseContent<ProjectDTO>(ResponseEnum.SUCCESS,boardService.getProject(projectid));
    }   
}
