package scut.legend.cg.service;

import javax.annotation.Resource;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import scut.legend.cg.po.WarehouseProductInventory;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class WarehouseProductInventoryServiceTest{
	private static final Logger LOGGER=LoggerFactory.getLogger(WarehouseInStorageRecordServiceTest.class);

	@Resource
	WarehouseProductInventoryService wpis;
	
	@Test
	public void getProductInventoryTest() {
		HashMap<String, String> params = new HashMap<String,String>();
		params.put("productSize","1");
		CommonDTO results = wpis.getProductInventory(params);
		System.out.println(results);
	}
	
}
