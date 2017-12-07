package scut.legend.cg.service;

import java.util.HashMap;
import java.util.List;

import scut.legend.cg.po.FactoryProductInventory;
import scut.legend.cg.vo.CommonDTO;

public interface FactoryProductInventoryService {
//	public CommonDTO getAllProductInventory();
	public CommonDTO getProductInventory(HashMap<String, String> params);
}
