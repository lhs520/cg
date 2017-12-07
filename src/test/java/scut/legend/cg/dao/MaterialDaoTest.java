package scut.legend.cg.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class MaterialDaoTest {
	@Resource
	private MaterialDao materialDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetInventoryByClass() {
		double inventory=materialDao.getInventoryByClass("银");
		System.out.println(inventory);
	}
	
	@Test
	public void testUpdateInventoryByClass(){
		//System.out.println(materialDao.updateInventoryByClass("银", -10.00));
	}
	

}
