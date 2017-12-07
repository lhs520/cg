package scut.legend.cg.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import scut.legend.cg.dao.ProductDao;
import scut.legend.cg.po.Product;
import scut.legend.cg.service.ProductService;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	@Resource 
	private ProductDao productDao;

	@Override
	public CommonDTO getAllProduct(HashMap<String, String> params) {
		List<Product> list=productDao.getAllProduct(params);
		CommonDTO result=new CommonDTO(Result.SUCCESS);
		result.setResult(list);
		return result;
	}
	
}
