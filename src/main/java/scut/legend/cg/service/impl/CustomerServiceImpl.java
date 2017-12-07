package scut.legend.cg.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import scut.legend.cg.dao.CustomerDao;
import scut.legend.cg.po.Customer;
import scut.legend.cg.service.CustomerService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;
@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	@Resource
	private CustomerDao customerDao;
	
	@Override
	public CommonDTO getCustomers(String name,int page) {
		int count=customerDao.getCountByName(name);
		int number=count/PageUtils.NUMBER_PER_PAGE+(count%PageUtils.NUMBER_PER_PAGE==0?0:1);
		CommonDTO result=new CommonDTO(Result.SUCCESS);
		result.setMsg(number+"");
		List<Customer> customerList=customerDao.getCustomers(name,(page-1)*PageUtils.NUMBER_PER_PAGE,PageUtils.NUMBER_PER_PAGE);;
		result.setResult(customerList);
		return result;
	}

}
