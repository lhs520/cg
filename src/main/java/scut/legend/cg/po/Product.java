package scut.legend.cg.po;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Product implements Serializable{
	private Integer productId;//产品id
	//多对一
	private ProductModelInfo productModelInfo;//产品型号
	private String productSize;//产品规格：长*宽*厚
	private Character productShape;//产品形态：直/弯
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public ProductModelInfo getProductModelInfo() {
		return productModelInfo;
	}
	public void setProductModelInfo(ProductModelInfo productModelInfo) {
		this.productModelInfo = productModelInfo;
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
}
