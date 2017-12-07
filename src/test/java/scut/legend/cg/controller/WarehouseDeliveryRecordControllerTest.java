package scut.legend.cg.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;

import scut.legend.cg.po.WarehouseDeliveryRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class WarehouseDeliveryRecordControllerTest {

	@Autowired
	private WebApplicationContext wac;
	 
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
	}

	
	@Test
	public void testCreate() throws Exception {
		WarehouseDeliveryRecord warehouseDeliveryRecord = new WarehouseDeliveryRecord();
		warehouseDeliveryRecord.setDeliveryDate(1498406400000L);
		warehouseDeliveryRecord.setDeliveryQuantity(20F);
		
		String requestJson = JSONObject.toJSONString(warehouseDeliveryRecord);
		
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post("/warehouseDeliveryRecord/XS201706260001").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andReturn();
		MockHttpServletResponse response=result.getResponse();
		System.out.println(response.getContentAsString());
	}
	
//	@Test
	public void testUpdate() throws Exception{
		WarehouseDeliveryRecord warehouseDeliveryRecord = new WarehouseDeliveryRecord();
		warehouseDeliveryRecord.setDeliveryQuantity(60F);
		
		String requestJson = JSONObject.toJSONString(warehouseDeliveryRecord);
		
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.put("/warehouseDeliveryRecord/KP201706260006").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andReturn();
		MockHttpServletResponse response=result.getResponse();
		System.out.println(response.getContentAsString());
	}

}
