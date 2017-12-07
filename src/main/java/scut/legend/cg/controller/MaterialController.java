package scut.legend.cg.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import scut.legend.cg.po.MaterialPurchaseRecord;
import scut.legend.cg.service.MaterialService;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Controller
public class MaterialController {
	@Resource
	private MaterialService materialService;
	
	@RequestMapping(value="/materialPurchaseRecord",method=RequestMethod.POST)
	@ResponseBody
	public CommonDTO createMateralPurchaseRecord(@RequestBody MaterialPurchaseRecord record){
		CommonDTO result=null;
		try{
			result=materialService.createMateralPurchaseRecord(record);
		}catch(Exception e){
			e.printStackTrace();
			result=new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		return result;
	}
	
	@RequestMapping(value="/materialPurchaseRecord/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public CommonDTO updateMateralPurchaseRecord(@PathVariable("id")Integer id,
			@RequestBody MaterialPurchaseRecord record){
		CommonDTO result=null;
		try{
			record.setId(id);
			result=materialService.updateMateralPurchaseRecord(record);
		}catch(Exception e){
			e.printStackTrace();
			result=new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		return result;
	}
	
	
	@RequestMapping(value="/materialPurchaseRecords/searchResult")
	@ResponseBody
	public CommonDTO searchMaterialPurchaseRecords(@RequestParam(value="pageNum",defaultValue="1")Integer page,
			@RequestParam(value="beginTime",required=false)Long startTime,
			@RequestParam(value="endTime",required=false)Long endTime){
		CommonDTO result=null;
		try{
			result=materialService.searchMaterialPurchaseRecords(page,startTime,endTime);
		}catch(Exception e){
			e.printStackTrace();
			result=new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		return result;
	}
	
	@RequestMapping(value="/statisticMaterialRecords",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO statisticMaterialRecords(@RequestParam("beginTime")Long startTime,
			@RequestParam("endTime")Long endTime){
		CommonDTO result=null;
		try{
			result=materialService.statisticMaterialRecords(startTime,endTime);
		}catch(Exception e){
			result=new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		return result;
	}
	
	@RequestMapping(value="/material/inventories",method=RequestMethod.GET)
	@ResponseBody
	public CommonDTO getMaterailInventories(){
		CommonDTO result=null;
		try{
			result=materialService.getInventories();
		}catch(Exception e){
			e.printStackTrace();
			result=new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		return result;
	}
	
}
