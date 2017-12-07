package scut.legend.cg.service;

import scut.legend.cg.vo.CommonDTO;

public interface CustomerService {

	CommonDTO getCustomers(String name,int page);

}
