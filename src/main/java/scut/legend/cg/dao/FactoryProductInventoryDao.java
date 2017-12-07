package scut.legend.cg.dao;

import java.util.HashMap;
import java.util.List;

import scut.legend.cg.po.FactoryProductInventory;
import scut.legend.cg.po.Product;

public interface FactoryProductInventoryDao {
//	public List<FactoryProductInventory> getAllProductInventory();
	public List<FactoryProductInventory> getProductInventory(HashMap<String, String> params);
	public void createOneFactoryProductInventory(FactoryProductInventory factoryProductInventory);
	public FactoryProductInventory getOneFactoryProductInventoryByProductId(Integer productId);
	public void updateOneFactoryProductInventory(FactoryProductInventory factoryProductInventory);
	public Integer getCount(HashMap<String, String> params);
}
