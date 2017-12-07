package scut.legend.cg.po;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductProduce implements Serializable{
	private Integer id;//产品id
	private String produceNum;//产出编号
	//多对一
	private Integer staffId;//登记者id
	private String staffName;//登记者名字
	//多对一
	private Product product;//产品
	private Long produceDate;//产出日期
	private Float produceQuantity;//产出总量
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProduceNum() {
		return produceNum;
	}
	public void setProduceNum(String produceNum) {
		this.produceNum = produceNum;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Long getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Long produceDate) {
		this.produceDate = produceDate;
	}
	public Float getProduceQuantity() {
		return produceQuantity;
	}
	public void setProduceQuantity(Float produceQuantity) {
		this.produceQuantity = produceQuantity;
	}

}
