package scut.legend.cg.service;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import scut.legend.cg.po.Staff;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class StaffServiceTest {
	private static final Logger LOGGER=LoggerFactory.getLogger(StaffServiceTest.class);
	@Resource
	private StaffService staffService;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(staffService);
		Staff staff=staffService.getStaffByStaffNum("2017060001");
		LOGGER.info("staff:{}",staff);
		Staff staff2=staffService.getStaffByStaffNum("2017060002");
		LOGGER.info("staff2:{}",staff2);
	}

	
}
