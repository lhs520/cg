package scut.legend.cg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import scut.legend.cg.po.Customer;

public interface CustomerDao {

	/**
	 * 筛选或获取顾客
	 * @param name	用于筛选顾客的名字
	 * @param begin	偏移量
	 * @param number 要返回的记录数
	 * @return
	 */
	List<Customer> getCustomers(@Param("name")String name,@Param("begin")int begin,@Param("number") int number);
	
	/**
	 * 创建顾客
	 * @param customer
	 */
	void create(Customer customer);
	//要注解一下，不然Mybatis会解析成_parameter
	/**
	 * 获取符合条件的记录数
	 * @param name
	 * @return
	 */
	int getCountByName(@Param("name")String name);
	

}
