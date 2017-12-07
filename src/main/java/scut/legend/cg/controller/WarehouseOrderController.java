package scut.legend.cg.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import scut.legend.cg.po.WarehouseOrder;
import scut.legend.cg.service.WarehouseOrderService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class WarehouseOrderController {

	@Resource
	private WarehouseOrderService warehouseOrderService;

	@RequestMapping(value = "/WarehouseOrder", method = RequestMethod.POST)
	@ResponseBody
	public CommonDTO createWarehouseOrder(@RequestBody WarehouseOrder warehouseOrder) {
		try {
			CommonDTO result = warehouseOrderService.createOrder(warehouseOrder);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}

	@RequestMapping(value = "/WarehouseOrder/{orderNum}", method = RequestMethod.GET)
	@ResponseBody
	public CommonDTO getOrderByNum(@PathVariable String orderNum) {
		try {
			CommonDTO result = warehouseOrderService.getWarehouseOrderByOrderNum(orderNum);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}

	@RequestMapping(value = "/WarehouseOrder/orderSet", method = RequestMethod.GET)
	@ResponseBody
	public CommonDTO getOrder() {
		try {
			CommonDTO result = warehouseOrderService.getWarehouseOrderToDelivery();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	
	@RequestMapping(value="/WarehouseOrder",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getWarehouseInstorageRecord(@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,
												@RequestParam(defaultValue="0") Long beginTime,
												@RequestParam(defaultValue="253370736000000") Long endTime){
		try{
			CommonDTO result = warehouseOrderService
									.getWarehouseOrderByTime(beginTime, endTime,pageNum);
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	
	@RequestMapping(value="/WarehouseOrder/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public CommonDTO updateWarehouseOrder(@RequestBody WarehouseOrder warehouseOrder,
										  @PathVariable Integer id){
		try {
//			warehouseOrder.setOrderNum(orderNum);
			warehouseOrder.setId(id);
			synchronized (WarehouseOrderController.class) {
				CommonDTO result = warehouseOrderService.updateWarehouseOrder(warehouseOrder);
			return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	//全部条件合并20170701
	@RequestMapping(value="/WarehouseOrders",method = RequestMethod.GET)
	@ResponseBody
	public CommonDTO getWarehouseOrders(@RequestParam(defaultValue="1",required=false) Integer pageNum,
										@RequestParam(required=false) String orderNum,
										@RequestParam(defaultValue="0",required=false) Long beginTime,
										@RequestParam(defaultValue="253370736000000",required=false) Long endTime,
										@RequestParam(required=false) String orderStatus,
										@RequestParam(required=false) String customerName){
		try {
			CommonDTO result = warehouseOrderService.getWarehouseOrders(pageNum, orderNum, beginTime, endTime, orderStatus, customerName);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	@RequestMapping(value="/WarehouseOrders/totalPrice")
	@ResponseBody
	public CommonDTO getTotalPrice(@RequestParam(value="beginTime",required=false)Long beginTime,
			@RequestParam(value="endTime",required=false)Long endTime){
		try{
			CommonDTO result=warehouseOrderService.getTotalPrice(beginTime,endTime);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
}
