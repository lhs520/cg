package scut.legend.cg.dao;

import java.util.List;

import scut.legend.cg.po.BlankRecord;
import scut.legend.cg.po.ProductProduce;

public interface ProductProduceDao {
	public int getDeliveryNumSuffix(Long time);
	public void createOneProductProduce(ProductProduce productProduce);
	public void updateOneProductProduce(ProductProduce productProduce);
//	public List<ProductProduce> getAllProductProduce();
	public List<ProductProduce> getProductProduceByDate(Long beginTime,Long endTime,Integer offset,Integer numPerPage);
	public ProductProduce getProductProduceById(Integer id);
	public Integer getCountByTime(Long beginTime,Long endTime);
}
