package scut.legend.cg.controller;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import scut.legend.cg.exception.InventoryShortageException;
import scut.legend.cg.po.WarehouseInStorageRecord;
import scut.legend.cg.service.WarehouseInStorageRecordService;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class WarehouseInStorageRecordController {
	
	@Resource
	private WarehouseInStorageRecordService warehouseInStorageRecordService;
	
	@RequestMapping(value="/WarehouseInStorageRecord",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO createWarehouseInStorageRecord(@RequestBody WarehouseInStorageRecord warehouseInStorageRecord){
		try{
			CommonDTO commonDTO = warehouseInStorageRecordService
					.createWarehouseInStorageRecord(warehouseInStorageRecord);
			return commonDTO;
		}catch (Exception e) {
			e.printStackTrace();
			if (e instanceof InventoryShortageException) {
				return new CommonDTO(Result.INVENTORY_ERROR);
			}
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	@RequestMapping(value="/WarehouseInStorageRecords",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getWarehouseInstorageRecord(@RequestParam(value="pageNum",defaultValue="1") Integer pageNum,
												 @RequestParam(defaultValue="0") Long beginTime,
												 @RequestParam(defaultValue="253370736000000") Long endTime){
		try{
			CommonDTO result = warehouseInStorageRecordService
									.getWarehouseInStorageRecordByTime(beginTime, endTime,pageNum);
			return result;
		}catch (Exception e) {
			e.printStackTrace();
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
	
	//经理修改入库记录
	@RequestMapping(value="/WarehouseInStorageRecord/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public CommonDTO updateWarehouseInStorageRecord(@PathVariable Integer id,
													@RequestBody WarehouseInStorageRecord warehouseInStorageRecord){
		try {
//			warehouseInStorageRecord.setInStorageNum(inStorageNum);
			warehouseInStorageRecord.setId(id);
			synchronized (WarehouseInStorageRecordController.class) {
				CommonDTO result = warehouseInStorageRecordService.
					updateWarehouseInStorageRecord(warehouseInStorageRecord);
			return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof InventoryShortageException) {
				return new CommonDTO(Result.INVENTORY_ERROR);
			}
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
	}
}
