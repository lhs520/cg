package scut.legend.cg.service;

import java.util.HashMap;
import java.util.List;

import scut.legend.cg.po.Product;
import scut.legend.cg.po.WarehouseProductInventory;
import scut.legend.cg.vo.CommonDTO;

/**
 * 
 * @author yaoyou
 *
 */
public interface WarehouseProductInventoryService {
	
	WarehouseProductInventory getproductInventoryByProduct(Product product);
	
	/**
	 * 更新产品库存
	 * @param warehouseProductInventory
	 * @return
	 */
	CommonDTO updateproductInventory(WarehouseProductInventory warehouseProductInventory);
		
	/**
	 * 根据条件筛选产品库存
	 * @param params
	 * @return
	 */
	CommonDTO getProductInventory(HashMap<String, String> params);
}
