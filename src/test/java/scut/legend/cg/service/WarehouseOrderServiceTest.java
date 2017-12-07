package scut.legend.cg.service;

import javax.annotation.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import scut.legend.cg.vo.CommonDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class WarehouseOrderServiceTest {

	@Resource
	WarehouseOrderService warehouseOrderService;
	
	@Test
	public void testGetByNum() {
		String orderNum = "XS201706260001";
		CommonDTO commonDTO = warehouseOrderService.getWarehouseOrderByOrderNum(orderNum);
		System.out.println(commonDTO);
	}

}
