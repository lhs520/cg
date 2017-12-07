package scut.legend.cg.po;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WarehouseStatistics {
	private Integer productId;
	private String productModel;
	private String productSize;
	private Character productShape;
	private Float allInStorageQuantity;
	private Float allDeliveryQuantity;
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public String getProductSize() {
		return productSize;
	}
	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}
	public Character getProductShape() {
		return productShape;
	}
	public void setProductShape(Character productShape) {
		this.productShape = productShape;
	}
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Float getAllInStorageQuantity() {
		return allInStorageQuantity;
	}
	public void setAllInStorageQuantity(Float allInStorageQuantity) {
		this.allInStorageQuantity = allInStorageQuantity;
	}
	public Float getAllDeliveryQuantity() {
		return allDeliveryQuantity;
	}
	public void setAllDeliveryQuantity(Float allDeliveryQuantity) {
		this.allDeliveryQuantity = allDeliveryQuantity;
	}
}
