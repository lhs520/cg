package scut.legend.cg.dao;

import java.util.HashMap;
import java.util.List;

import scut.legend.cg.po.Product;
import scut.legend.cg.po.WarehouseProductInventory;

/**
 * 
 * @author yaoyou
 *
 */
public interface WarehouseProductInventoryDao {

	/**
	 * 根据产品获取产品库存
	 * @param product 产品
	 * @return
	 */
	WarehouseProductInventory getproductInventoryByProduct(Product product);
	
	/**
	 * 更新仓库库存
	 * @param warehouseProductInventory
	 */
	void updateproductInventory(WarehouseProductInventory warehouseProductInventory);
	
	/**
	 * 创建仓库产品库存
	 * @param warehouseProductInventory
	 */
	void createProductInventory(WarehouseProductInventory warehouseProductInventory);
	
	//返回所有的产品库存
	List<WarehouseProductInventory> getAllProductInventory();
	
	/**
	 * 根据条件获取仓库库存
	 * @param params HashMap参数，可包含产品型号，产品规格，产品形态
	 * @return
	 */
	List<WarehouseProductInventory> getProductInventory(HashMap<String, String> params);

	/**
	 * 获取符合条件的库存记录数
	 * @param params
	 * @return
	 */
	Integer getCount(HashMap<String, String> params);
}
