package scut.legend.cg.dao;

import java.util.HashMap;
import java.util.List;

import scut.legend.cg.po.Product;
import scut.legend.cg.po.ProductModelInfo;

public interface ProductDao {
	public void createOneProduct(Product product);
	public List<Product> getAllProduct(HashMap<String, String> params);
	public Product getOneProduct(String productModel,String productSize,Character productShape);
}
