package scut.legend.cg.dao;

import java.util.List;

import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.ProductProduce;
import scut.legend.cg.vo.CommonDTO;

public interface ProductModelInfoDao {
	public void createOneProductModelInfo(ProductModelInfo productModelInfo);
	public void updateOneProductModelInfo(ProductModelInfo productModelInfo);
//	public List<ProductModelInfo> getAllProductModelInfo();
	public List<ProductModelInfo> getProductModelInfoByDate(Long beginTime,Long endTime,Integer offset,Integer numPerPage);
	public Integer getCountByTime(Long beginTime,Long endTime);
	public List<String> getAllProductModels();
}
