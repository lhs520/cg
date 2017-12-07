package scut.legend.cg.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import scut.legend.cg.po.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class CustomerDaoTest {

	@Resource
	private CustomerDao customerDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreate() {
		Customer customer=new Customer();
		customer.setCustomerName("小吴");
		customer.setCustomerPhone("18826077188");
		customer.setCustomerAddress("C10");
		customerDao.create(customer);
	}

}
