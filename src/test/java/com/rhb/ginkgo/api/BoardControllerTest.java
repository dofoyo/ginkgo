package com.rhb.ginkgo.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringJUnit4ClassRunner.class) 
@WebAppConfiguration
public class BoardControllerTest {
	MockMvc mvc; 
	
    @Autowired  
    WebApplicationContext webApplicationContext;  
    
    @Before  
    public void setUp() throws JsonProcessingException {  
        //this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();  
    	this.mvc = MockMvcBuilders.standaloneSetup(new GreetingController()).build();
    }  
    
    @Test
    public void testGetBoard()throws Exception{
    	String expectedResult = "{\"id\":1,\"content\":\"Hello, World!\"}";  
        String uri = "/greeting";  
        MvcResult mvcResult = this.mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn(); 
        int status = mvcResult.getResponse().getStatus();  
        String content = mvcResult.getResponse().getContentAsString();  
        
        System.out.println("status: " + status);
        System.out.println("content: " + content);

        Assert.assertTrue("错误，正确的返回值为200", status == 200);  
        Assert.assertFalse("错误，正确的返回值为200", status != 200);  
        Assert.assertTrue("数据一致", expectedResult.equals(content));  
        Assert.assertFalse("数据不一致", !expectedResult.equals(content));  
        
    }
    
    

}
