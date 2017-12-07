package scut.legend.cg.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import scut.legend.cg.dao.FactoryProductInventoryDao;
import scut.legend.cg.po.FactoryProductInventory;
import scut.legend.cg.po.WarehouseProductInventory;
import scut.legend.cg.service.FactoryProductInventoryService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service("factoryProductInventory")
public class FactoryProductInventoryImpl implements FactoryProductInventoryService{
	@Resource
	private FactoryProductInventoryDao factoryProductInventoryDao;

//	@Override
//	public CommonDTO getAllProductInventory() {
//		List<FactoryProductInventory> fpiList=factoryProductInventoryDao.getAllProductInventory();
//		CommonDTO result=new CommonDTO(Result.SUCCESS);
//		result.setResult(fpiList);
//		return result;
//	}

	@Override
	public CommonDTO getProductInventory(HashMap<String, String> params) {
		List<FactoryProductInventory> fpiList=factoryProductInventoryDao.getProductInventory(params);
		// 如果是请求的第一页，则在返回结果的commDTO的msg信息中设置全部的页数
		if (params.get("page").equals("1")) {
			double count = factoryProductInventoryDao.getCount(params);
			int pageCount = (int) Math.ceil(count / PageUtils.NUMBER_PER_PAGE);
			
			CommonDTO result=new CommonDTO(Result.SUCCESS);
			result.setMsg(pageCount+"");
			result.setResult(fpiList);
			return result;
		}

		CommonDTO result=new CommonDTO(Result.SUCCESS);
		result.setResult(fpiList);
		return result;
	}
}
