package com.rhb.ginkgo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rhb.ginkgo.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BoardServiceTest {
	@Autowired
	BoardService boardService;
	
	@Test
	public void testGetBoards(){
		System.out.println(boardService.getBoards());
	}
	
	@Test
	public void testGetBoard(){
		String boardid = "1";
		System.out.println(boardService.getBoard(boardid));
	}

}
