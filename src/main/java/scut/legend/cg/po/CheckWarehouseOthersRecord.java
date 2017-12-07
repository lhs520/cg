package scut.legend.cg.po;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CheckWarehouseOthersRecord implements Serializable{
	private Integer id;//其他清仓记录id
	private String checkNum;//清仓编号
	//多对一
	private Integer staffId;//登记者id
	private String staffName;//登记者名字
	//多对一
	private ProductModelInfo productModelInfo;//产品型号
	private Long checkDate;//清仓日期
	private Float blankInventory;//胚料
	private Float semifinishedProductInventory;//半成品
	private Float wasteInventory;//废料
	private Float leftoverInventory;//边角料
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
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
	public ProductModelInfo getProductModelInfo() {
		return productModelInfo;
	}
	public void setProductModelInfo(ProductModelInfo productModelInfo) {
		this.productModelInfo = productModelInfo;
	}
	public Long getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Long checkDate) {
		this.checkDate = checkDate;
	}
	public Float getBlankInventory() {
		return blankInventory;
	}
	public void setBlankInventory(Float blankInventory) {
		this.blankInventory = blankInventory;
	}
	public Float getSemifinishedProductInventory() {
		return semifinishedProductInventory;
	}
	public void setSemifinishedProductInventory(Float semifinishedProductInventory) {
		this.semifinishedProductInventory = semifinishedProductInventory;
	}
	public Float getWasteInventory() {
		return wasteInventory;
	}
	public void setWasteInventory(Float wasteInventory) {
		this.wasteInventory = wasteInventory;
	}
	public Float getLeftoverInventory() {
		return leftoverInventory;
	}
	public void setLeftoverInventory(Float leftoverInventory) {
		this.leftoverInventory = leftoverInventory;
	}
	
}
