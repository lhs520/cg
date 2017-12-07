package scut.legend.cg.controller;

import javax.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import scut.legend.cg.exception.DeliveryQuantityException;
import scut.legend.cg.exception.InventoryShortageException;
import scut.legend.cg.po.WarehouseDeliveryRecord;
import scut.legend.cg.service.WarehouseDeliveryRecordService;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class WarehouseDeliveryRecordController {

	@Resource
	WarehouseDeliveryRecordService warehouseDeliveryRecordService;
	
	@RequestMapping(value="/warehouseDeliveryRecord/{orderNum}",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO createwarehouseDeliveryRecord(@RequestBody WarehouseDeliveryRecord warehouseDeliveryRecord,
													@PathVariable String orderNum){
		try {
			CommonDTO commonDTO = warehouseDeliveryRecordService.createWarehouseDeliveryRecord(warehouseDeliveryRecord,orderNum);
			return commonDTO;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof InventoryShortageException) {
				return new CommonDTO(Result.INVENTORY_SHORTAGE);
			}else if (e instanceof DeliveryQuantityException) {
				return new CommonDTO(Result.DELIVERY_QUANTITY_TOO_LARGH);
			}
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	@RequestMapping(value="/warehouseDeliveryRecord",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getWarehouseDeliveryRecordByTime(@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,
													  @RequestParam(defaultValue="0") Long beginTime,
													  @RequestParam(defaultValue="253370736000000") Long endTime){
		try {
			CommonDTO commonDTO = warehouseDeliveryRecordService.getWarehouseDeliveryRecordByTime(beginTime, endTime, pageNum);
			return commonDTO;
		} catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	@RequestMapping(value="/warehouseDeliveryRecord/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public CommonDTO updateWarehouseDeliveryRecord(@PathVariable Integer id,
												   @RequestBody WarehouseDeliveryRecord warehouseDeliveryRecord){
		try {
//			warehouseDeliveryRecord.setDeliveryNum(deliveryNum);
			warehouseDeliveryRecord.setId(id);
			synchronized (WarehouseDeliveryRecordController.class) {
				CommonDTO result = warehouseDeliveryRecordService.updateWarehouseDeliveryRecord(warehouseDeliveryRecord);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof InventoryShortageException) {
				return new CommonDTO(Result.INVENTORY_SHORTAGE);
			}else if (e instanceof DeliveryQuantityException) {
				return new CommonDTO(Result.DELIVERY_QUANTITY_TOO_LARGH);
			}
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
}
