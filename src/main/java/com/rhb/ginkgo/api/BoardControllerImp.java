package com.rhb.ginkgo.api;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
		System.out.println("boardService: " + boardService);
        return new ResponseContent<BoardDTO>(ResponseEnum.SUCCESS,boardService.getBoard(boardid,false));
    }
	
	@GetMapping("/refreshBoardAndProject")
    public ResponseContent<BoardDTO> getBoardAfterRefreshed(@RequestParam(value="id", defaultValue="1") String boardid) {
    	//System.out.println("boardid: " + boardid);	
        return new ResponseContent<BoardDTO>(ResponseEnum.SUCCESS,boardService.getBoard(boardid,true));
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
    
	@GetMapping("/project")
    public ResponseContent<ProjectDTO> getProject(@RequestParam(value="id") String projectid) {
    	//System.out.println("projectid: " + projectid);	
        return new ResponseContent<ProjectDTO>(ResponseEnum.SUCCESS,boardService.getProject(projectid,false));
    }
	
	@GetMapping("/refreshProjectAndTask")
    public ResponseContent<ProjectDTO> getProjectAfterRefreshed(@RequestParam(value="id") String projectid) {
    	//System.out.println("projectid: " + projectid);	
        return new ResponseContent<ProjectDTO>(ResponseEnum.SUCCESS,boardService.getProject(projectid,true));
    } 
	
}
