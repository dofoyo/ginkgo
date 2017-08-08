package com.rhb.ginkgo.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rhb.ginkgo.repository.entity.PersonEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void test(){
		List<PersonEntity> persons = personRepository.findAll();
		Assert.assertTrue(persons!=null);
		
	}
	
}
