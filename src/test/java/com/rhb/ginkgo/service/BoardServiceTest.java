package com.rhb.ginkgo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rhb.ginkgo.api.dto.BoardDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BoardServiceTest {
	@Autowired
	BoardService boardService;
	

	@Test
	public void testGetBoard(){
		String boardid = "1";
		BoardDTO board = boardService.getBoard(boardid,false);
		Assert.assertTrue(board!=null);
	}


}
