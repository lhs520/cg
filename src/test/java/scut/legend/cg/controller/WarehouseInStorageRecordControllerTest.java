package scut.legend.cg.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.alibaba.fastjson.JSONObject;

import scut.legend.cg.po.Product;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.Staff;
import scut.legend.cg.po.WarehouseInStorageRecord;
import scut.legend.cg.vo.CommonDTO;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class WarehouseInStorageRecordControllerTest {

	@Autowired
	private WebApplicationContext wac;
	 
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
	}

//	@Test
	public void testCreate() throws Exception {
		WarehouseInStorageRecord warehouseInStorageRecord = new WarehouseInStorageRecord();
		ProductModelInfo productModeInfo= new ProductModelInfo();
		productModeInfo.setProductModel("TX100");
		Product product = new Product();
		product.setProductModelInfo(productModeInfo);
		product.setProductShape('直');
		product.setProductSize("0.1*0.1*0.1");
		warehouseInStorageRecord.setProduct(product);
		warehouseInStorageRecord.setInStorageDate(1498579200000L);
		warehouseInStorageRecord.setInStorageQuantity(20.0F);
		
		String requestJson = JSONObject.toJSONString(warehouseInStorageRecord);
		
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post("/WarehouseInStorageRecord").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andReturn();
		MockHttpServletResponse response=result.getResponse();
		System.out.println(response.getContentAsString());
		
//		ModelAndViewAssert.aser
	}
	
//	@Test
	public void testGet() throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/WarehouseInStorageRecord/1483200000000/1493568000000")).andReturn();
		MockHttpServletResponse response= result.getResponse();
		System.out.println(response.getContentAsString());
	}
	
//	@Test
	public void testUpdate() throws Exception{
		WarehouseInStorageRecord warehouseInStorageRecord = new WarehouseInStorageRecord();
		warehouseInStorageRecord.setInStorageDate(1498492800000L);
		warehouseInStorageRecord.setInStorageQuantity(60F);
		ProductModelInfo productModeInfo= new ProductModelInfo();
		productModeInfo.setProductModel("2");
		Product product = new Product();
		product.setProductModelInfo(productModeInfo);
		product.setProductShape('1');
		product.setProductSize("1");
		warehouseInStorageRecord.setProduct(product);
		
		String requestJson = JSONObject.toJSONString(warehouseInStorageRecord);
		
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.put("/WarehouseInStorageRecord/KR201706280001").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andReturn();
		MockHttpServletResponse response=result.getResponse();
		System.out.println(response.getContentAsString());
	}
	

}
