package com.rhb.ginkgo.api;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhb.ginkgo.api.dto.BoardDTO;
import com.rhb.ginkgo.api.dto.TaskDTO;
import com.rhb.ginkgo.service.BoardService;

@RestController
public class BoardControllerImp implements BoardController{
	@Autowired
	private BoardService boardService;

	/*
	@Autowired 
	public BoardController(BoardService boardService){
		this.boardService = boardService;
	}
	*/
	
	@GetMapping("/board")
    public ResponseContent<BoardDTO> getBoard(@RequestParam(value="id", defaultValue="1") String boardid) {
    	//System.out.println("boardid: " + boardid);	
        return new ResponseContent<BoardDTO>("100","ok",boardService.getBoard(boardid));
    }
    
    @PutMapping("/stage")
    public void updateStage(@RequestParam(value="id") String stageid, @RequestBody String body){
    	//System.out.println("update Stage " + stageid + ", " + body);
    	
    	List<TaskDTO> tasks = null;
    	try {
        	ObjectMapper mapper = new ObjectMapper();
        	JavaType javaType =  mapper.getTypeFactory().constructParametricType(ArrayList.class, TaskDTO.class); 
        	tasks =  (List<TaskDTO>)mapper.readValue(body, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	if(tasks != null){
    		boardService.updateStage(stageid, tasks);
    	}
    }
}
