package scut.legend.cg.po;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductModelInfo implements Serializable{
	private Integer id;//产品型号信息id
	//多对一
	private Integer staffId;//登记者id
	private String staffName;//登记者名字
	private String productModel;//产品型号
	private Long addDate;//添加日期
	private Float ratioAg;//银占比
	private Float ratioCu;//铜占比
	private Float ratioZn;//锌占比
	private Float ratioCd;//镉占比
	private Float ratioSn;//锡占比
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public Long getAddDate() {
		return addDate;
	}
	public void setAddDate(Long addDate) {
		this.addDate = addDate;
	}
	public Float getRatioAg() {
		return ratioAg;
	}
	public void setRatioAg(Float ratioAg) {
		this.ratioAg = ratioAg;
	}
	public Float getRatioCu() {
		return ratioCu;
	}
	public void setRatioCu(Float ratioCu) {
		this.ratioCu = ratioCu;
	}
	public Float getRatioZn() {
		return ratioZn;
	}
	public void setRatioZn(Float ratioZn) {
		this.ratioZn = ratioZn;
	}
	public Float getRatioCd() {
		return ratioCd;
	}
	public void setRatioCd(Float ratioCd) {
		this.ratioCd = ratioCd;
	}
	public Float getRatioSn() {
		return ratioSn;
	}
	public void setRatioSn(Float ratioSn) {
		this.ratioSn = ratioSn;
	}

	
}
