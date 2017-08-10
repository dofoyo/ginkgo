package com.rhb.ginkgo.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rhb.ginkgo.repository.entity.TaskdetailEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskdetailRepositoryTest {
	@Autowired
	TaskdetailRepository taskdetailRepository;
	
	@Test
	public void testFindByIsrecallNotAndText_content(){
		 List<TaskdetailEntity> taskdetailEntities = taskdetailRepository.findByIsrecallNotAndTextcontent("2af29bb5-a359-4afd-bb0c-181b62372a96");
		 for(TaskdetailEntity entity : taskdetailEntities){
			 System.out.println(entity);
		 }
		 Assert.assertFalse(taskdetailEntities.isEmpty());
		 
	}
	
	
}
