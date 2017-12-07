package scut.legend.cg.po;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 仓库库存
 * @author yaoyou
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WarehouseProductInventory implements Serializable{
	private Integer id;//记录ID
	private Product product;//产品
	private Float productInventory;//产品库存
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getProductInventory() {
		return productInventory;
	}
	public void setProductInventory(Float productInventory) {
		this.productInventory = productInventory;
	}
	
	
}
