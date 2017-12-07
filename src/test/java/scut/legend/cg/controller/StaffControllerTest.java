package scut.legend.cg.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class StaffControllerTest {

	 @Autowired
	 private WebApplicationContext wac;
	 
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
	}

	@Test
	public void test() throws Exception {
//		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/test")).andReturn();
//		MockHttpServletResponse response=result.getResponse();
//		System.out.println(response.getContentAsString());
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/encry").param("password", "123456")).andReturn();
		MockHttpServletResponse response=result.getResponse();
		System.out.println(response.getContentAsString());
		
//		ModelAndViewAssert.aser
	}
	
	

}
