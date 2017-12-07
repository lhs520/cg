package scut.legend.cg.po;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FactoryProductInventory implements Serializable{
	private Integer id;//工厂产品库存id
	//一对一
	private Product product;//产品
	private Float productInventory;//库存总量
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Float getProductInventory() {
		return productInventory;
	}
	public void setProductInventory(Float productInventory) {
		this.productInventory = productInventory;
	}
}
