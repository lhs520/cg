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

import scut.legend.cg.po.Customer;
import scut.legend.cg.po.Product;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.WarehouseOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class WarehouseOrderControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
	}
	
	
	@Test
	public void testCreateOrder() throws Exception {
		WarehouseOrder warehouseOrder = new WarehouseOrder();
		warehouseOrder.setOrderDate(1495814400000L);
		Product prodcut = new Product();
		ProductModelInfo productModelInfo = new ProductModelInfo();
		productModelInfo.setProductModel("1");
		prodcut.setProductModelInfo(productModelInfo);
		prodcut.setProductShape('1');
		prodcut.setProductSize("1");
		warehouseOrder.setProduct(prodcut);
		warehouseOrder.setDeliveryQuantityTotal(100F);
		warehouseOrder.setUnitPrice(5.20F);
		Customer customer=new Customer();
		customer.setCustomerName("小吴");
		customer.setCustomerPhone("18826077188");
		customer.setCustomerAddress("C10");
		warehouseOrder.setCustomer(customer);
		warehouseOrder.setDeliveryDate(1498752000000L);
		
		String requestJson = JSONObject.toJSONString(warehouseOrder);
		
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.post("/WarehouseOrder").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andReturn();
		MockHttpServletResponse response=result.getResponse();
		System.out.println(response.getContentAsString());
	}

	
//	@Test
	public void testGet() throws Exception{
		
	}
}
