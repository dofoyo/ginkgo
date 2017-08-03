package com.rhb.ginkgo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rhb.ginkgo.Application;
import com.rhb.ginkgo.repository.entity.ProjectEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BoardRepositoryTest {
	@Autowired
	BoardRepository boardRepository;
	
	//@Test
	public void testGetBoards(){
		System.out.println(boardRepository.getBoards());
	}
	
	//@Test
	public void testGetBoard(){
		String boardid = "1";
		
		System.out.println(boardRepository.getBoard(boardid));
		
	}
	
	@Test
	public void saveProject(){
		ProjectEntity p = new ProjectEntity();
		p.setProjectname("ddd");
		boardRepository.saveProject(p);
		
	}
}
