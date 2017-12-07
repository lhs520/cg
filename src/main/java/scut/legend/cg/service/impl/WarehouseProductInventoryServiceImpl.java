package scut.legend.cg.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scut.legend.cg.dao.WarehouseProductInventoryDao;
import scut.legend.cg.exception.InventoryShortageException;
import scut.legend.cg.po.Product;
import scut.legend.cg.po.WarehouseProductInventory;
import scut.legend.cg.service.WarehouseProductInventoryService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service
public class WarehouseProductInventoryServiceImpl implements WarehouseProductInventoryService {

	@Resource
	private WarehouseProductInventoryDao warehouseProductInventoryDao;

	@Override
	@Transactional
	public WarehouseProductInventory getproductInventoryByProduct(Product product) {
		WarehouseProductInventory warehouseProductInventory = warehouseProductInventoryDao
				.getproductInventoryByProduct(product);
		// 如果没有则创建
		if (warehouseProductInventory == null) {
			warehouseProductInventory = new WarehouseProductInventory();
			warehouseProductInventory.setProduct(product);
			warehouseProductInventory.setProductInventory((float) 0.00);
			warehouseProductInventoryDao.createProductInventory(warehouseProductInventory);
		}
		return warehouseProductInventory;
	}

	@Override
	@Transactional
	public CommonDTO updateproductInventory(WarehouseProductInventory warehouseProductInventory) {
		if (warehouseProductInventory.getProductInventory() < 0) {
			throw new InventoryShortageException();
		}
		warehouseProductInventoryDao.updateproductInventory(warehouseProductInventory);
		CommonDTO commonDTO = new CommonDTO(Result.SUCCESS);
		return commonDTO;
	}

	@Override
	public CommonDTO getProductInventory(HashMap<String, String> params) {
		List<WarehouseProductInventory> results = warehouseProductInventoryDao.getProductInventory(params);
		// 如果是请求的第一页，则在返回结果的commDTO的msg信息中设置全部的页数
		if (params.get("page").equals("1")) {
			double count = warehouseProductInventoryDao.getCount(params);
			CommonDTO commonDTO = new CommonDTO();
			int page = (int) Math.ceil(count / PageUtils.NUMBER_PER_PAGE);
			commonDTO.setMsg("" + page);
			commonDTO.setResult(results);
			return commonDTO;
		}
		CommonDTO commonDTO = new CommonDTO(Result.SUCCESS);
		commonDTO.setResult(results);
		return commonDTO;
	}

}
