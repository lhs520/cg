package scut.legend.cg.service;

import javax.annotation.Resource;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import scut.legend.cg.dao.WarehouseInStorageRecordDao;
import scut.legend.cg.po.Product;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.Staff;
import scut.legend.cg.po.WarehouseInStorageRecord;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class WarehouseInStorageRecordServiceTest {
	private static final Logger LOGGER=LoggerFactory.getLogger(WarehouseInStorageRecordServiceTest.class);
	
	@Resource
	private WarehouseInStorageRecordService warehouseInStorageRecordService;
	
	

//	@Test
	public void test() {
		
		Product product = new Product();
		product.setProductId(1);
		Staff staff = new Staff();
		staff.setStaffId(1);
		
		WarehouseInStorageRecord warehouseInStorageRecord = new WarehouseInStorageRecord();
		warehouseInStorageRecord.setInStorageDate(1483200000000L);
		warehouseInStorageRecord.setInStorageNum("2014306");
		warehouseInStorageRecord.setInStorageQuantity(20.0F);
		warehouseInStorageRecord.setProduct(product);
		warehouseInStorageRecord.setStaffName("iyou");
		warehouseInStorageRecord.setStaff(staff);
		
//		CommonDTO commonDTO=warehouseInStorageRecordService.createWarehouseInStorageRecord(warehouseInStorageRecord);
		
//		assertEquals(commonDTO.getMsg(),"成功");
		
	}
	
	@Resource
	private WarehouseInStorageRecordDao wisDao;
	
//	@Test
	public void testGetProductId() {
		WarehouseInStorageRecord warehouseInStorageRecord = new WarehouseInStorageRecord();
		ProductModelInfo productModeInfo= new ProductModelInfo();
		productModeInfo.setProductModel("1");
		Product product = new Product();
		product.setProductModelInfo(productModeInfo);
		product.setProductShape('1');
		product.setProductSize("1");
		warehouseInStorageRecord.setProduct(product);
		int result = wisDao.getProductId(warehouseInStorageRecord);
		
		assertEquals(result,1);
		
	}
	
//	@Test
	public void testTime(){
		Long startTime = 1483200000000L;
		Long endTime = 1493568000000L;
		CommonDTO result = warehouseInStorageRecordService.getWarehouseInStorageRecordByTime(startTime, endTime,1);
		System.out.println("end");
	}
	
//	@Test
	public void testUpdate(){
		WarehouseInStorageRecord warehouseInStorageRecord = new WarehouseInStorageRecord();
		warehouseInStorageRecord.setInStorageNum("KR201705010001");
		warehouseInStorageRecord.setInStorageQuantity(40F);
//		warehouseInStorageRecordService.updateWarehouseInStorageRecord(warehouseInStorageRecord);
	}
}
