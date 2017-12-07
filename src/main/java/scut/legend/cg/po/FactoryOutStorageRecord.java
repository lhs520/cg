package scut.legend.cg.po;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FactoryOutStorageRecord implements Serializable{
	private Integer id;//工厂出库记录id
	private String outStorageNum;//出库编号
	//多对一
	private Integer staffId;//登记者id
	private String staffName;//登记者名字
	//多对一
	private Product product;//产品
	private Long outStorageDate;//出库日期
	private Float outStorageQuantity;//出库总量
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOutStorageNum() {
		return outStorageNum;
	}
	public void setOutStorageNum(String outStorageNum) {
		this.outStorageNum = outStorageNum;
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
	public Long getOutStorageDate() {
		return outStorageDate;
	}
	public void setOutStorageDate(Long outStorageDate) {
		this.outStorageDate = outStorageDate;
	}
	public Float getOutStorageQuantity() {
		return outStorageQuantity;
	}
	public void setOutStorageQuantity(Float outStorageQuantity) {
		this.outStorageQuantity = outStorageQuantity;
	}
}
