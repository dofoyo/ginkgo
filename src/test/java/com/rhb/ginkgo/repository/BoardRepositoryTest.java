package com.rhb.ginkgo.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rhb.ginkgo.repository.entity.BoardEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void testGetBoard(){
		String boardid = "1";
		BoardEntity board = boardRepository.getBoard(boardid);
		Assert.assertTrue(board!=null);
	}

}
