package scut.legend.cg.po;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FactoryStatistics implements Serializable{
	private Product product;//产品
	private Float allProduceQuantity;//产出总重量
	private Float allOutStorageQuantity;//出库总重量
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Float getAllProduceQuantity() {
		return allProduceQuantity;
	}
	public void setAllProduceQuantity(Float allProduceQuantity) {
		this.allProduceQuantity = allProduceQuantity;
	}
	public Float getAllOutStorageQuantity() {
		return allOutStorageQuantity;
	}
	public void setAllOutStorageQuantity(Float allOutStorageQuantity) {
		this.allOutStorageQuantity = allOutStorageQuantity;
	}
}
