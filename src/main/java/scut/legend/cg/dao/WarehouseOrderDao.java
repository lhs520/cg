package scut.legend.cg.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import scut.legend.cg.po.Customer;
import scut.legend.cg.po.WarehouseOrder;

/**
 * 
 * @author yaoyou
 *
 */
public interface WarehouseOrderDao {
	
	/**
	 * 创建订单
	 * @param warehouseOrder 入库订单
	 */
	void createOrder(WarehouseOrder warehouseOrder);
	
	/**
	 * 获取订单后缀
	 * @param time 开始时间
	 * @param endTime 结束时间
	 * @return 订单后缀
	 */
	int getWarehouseOrderNumSuffix(Long time,long endTime);
	
	Integer getProductId(WarehouseOrder warehouseOrder);
	
	void createCustomer(Customer customer);
	
	/**
	 * 根据订单编号获取订单
	 * @param orderNum 订单编号
	 * @return 订单
	 */
	WarehouseOrder getWarehouseOrderByOrderNum(String orderNum);
	
	/**
	 * 根据id获取订单
	 * @param id 订单ID
	 * @return 订单
	 */
	WarehouseOrder getWarehouseOrderById(Integer id);
	
//	List<WarehouseOrder> getWarehouseOrder(HashMap<String, String> params);
	
	
	/**
	 * 获取订单状态为“未配送”和“部分配送”的所有订单
	 * @param warehouseManagerId 
	 * @return
	 */
	List<WarehouseOrder> getWarehouseOrderToDelivery(@Param("warehouseManagerId")Integer warehouseManagerId);
	
	
	/**
	 * 获取指定时间段内的订单
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param offset 偏移量
	 * @param pageNum 多少条记录
	 * @return
	 */
	List<WarehouseOrder> getWarehouseOrderByTime(Long startTime,Long endTime,Integer offset,Integer pageNum);
	
	/**
	 * 根据条件筛选订单
	 * @param orderNum 订单编号
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param orderStatus 订单状态
	 * @param customerName 客户姓名
	 * @param offset 偏移量
	 * @param pageNum 多少条记录
	 * @return
	 */
	List<WarehouseOrder> getWarehouseOrders(@Param("orderNum")String orderNum,
										    @Param("beginTime")Long beginTime,
										    @Param("endTime")Long endTime,
										    @Param("orderStatus")String orderStatus,
										    @Param("customerName")String customerName,
										    @Param("offset")Integer offset,
										    @Param("pageNum")Integer pageNum);
	
	/**
	 * 获取符合条件的订单数
	 * @param orderNum 订单编号
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param orderStatus 订单状态
	 * @param customerName 客户姓名
	 * @return 订单数
	 */
	Integer getWarehouseOrdersCount(@Param("orderNum")String orderNum,
			                        @Param("beginTime")Long beginTime,
			                        @Param("endTime")Long endTime,
			                        @Param("orderStatus")String orderStatus,
			                        @Param("customerName")String customerName);
	
	
	Integer updateOrderDeliveryNeed(Integer id,Float deliveryQuantityNeed);
	
	Integer updateOrderStatus(Integer id,String orderStatus);
	
	Integer getCountByTime(Long startTime,Long endTime);

	Integer getCount(HashMap<String, String> params);
	
	/**
	 * 更新订单
	 * @param warehouseOrder 包含更新数据的订单
	 * @return
	 */
	int updateOrder(WarehouseOrder warehouseOrder);
	
	//（暂时）获取客户ID
	Integer getCustomerId(String customerName,String customerAddress,String customerPhone);

	Float getTotalPrice(@Param("beginTime")Long beginTime,@Param("endTime") Long endTime);
}
