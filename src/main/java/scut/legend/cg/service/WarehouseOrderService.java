package scut.legend.cg.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import scut.legend.cg.po.WarehouseOrder;
import scut.legend.cg.vo.CommonDTO;

public interface WarehouseOrderService {
	
	/**
	 * 创建订单
	 * @param warehouseOrder 仓库订单
	 * @return
	 */
	CommonDTO createOrder(WarehouseOrder warehouseOrder);
	
	CommonDTO getWarehouseOrderByOrderNum(String orderNum);
	
	/**
	 * 获取所有订单状态为“未配送”和“部分配送的订单”
	 * @return
	 */
	CommonDTO getWarehouseOrderToDelivery();
	
	CommonDTO getWarehouseOrderByTime(Long startTime,Long endTime,Integer pageNum);
	
	/**
	 * 更新订单
	 * @param warehouseOrder 仓库订单
	 * @return
	 */
	CommonDTO updateWarehouseOrder(WarehouseOrder warehouseOrder);
	
	/**
	 *  根据条件筛选订单
	 * @param page 页数
	 * @param orderNum 订单编号
	 * @param beginTime 起始时间
	 * @param endTime 终止时间
	 * @param orderStatus 订单状态
	 * @param customerName 客户姓名
	 * @return
	 */
	CommonDTO getWarehouseOrders(Integer page, String orderNum,Long beginTime,Long endTime,String orderStatus,String customerName);

	CommonDTO getTotalPrice(Long beginTime, Long endTime);
}
