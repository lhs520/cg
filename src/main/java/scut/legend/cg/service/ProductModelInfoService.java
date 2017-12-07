package scut.legend.cg.service;

import java.util.List;

import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.vo.CommonDTO;

public interface ProductModelInfoService {
	public CommonDTO createOneProductModelInfo(ProductModelInfo productModelInfo);
	public CommonDTO updateOneProductModelInfo(ProductModelInfo productModelInfo);
//	public CommonDTO getAllProductModelInfo();
	public CommonDTO getProductModelInfoByDate(Long beginTime,Long endTime,Integer pageNum);
	public CommonDTO getAllProductModels();
}
