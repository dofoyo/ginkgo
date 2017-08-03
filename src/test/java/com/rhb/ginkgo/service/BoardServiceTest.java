package com.rhb.ginkgo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhb.ginkgo.Application;
import com.rhb.ginkgo.api.dto.ProjectDTO;
import com.rhb.ginkgo.repository.entity.ProjectEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BoardServiceTest {
	@Autowired
	BoardService boardService;
	
	//@Test
	public void testGetBoards(){
		System.out.println(boardService.getBoards());
	}
	
	//@Test
	public void testGetBoard(){
		String boardid = "1";
		System.out.println(boardService.getBoard(boardid));
	}
	
	//@Test
	public void testFindTaskById(){
		ProjectDTO p = boardService.getProject("");
		ObjectMapper mapper = new ObjectMapper();
		try {
			String str = mapper.writeValueAsString(p);
			System.out.println(str);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
