package scut.legend.cg.service;

import java.util.List;

import scut.legend.cg.po.ProductProduce;
import scut.legend.cg.vo.CommonDTO;

public interface ProductProduceService {
	public CommonDTO createOneProductProduce(ProductProduce productProduce);
	public CommonDTO updateOneProductProduce(ProductProduce productProduce);
//	public CommonDTO getAllProductProduce();
	public CommonDTO getProductProduceByDate(Long beginTime,Long endTime,Integer pageNum);
}
