package scut.legend.cg.po;

import java.io.Serializable;
import java.math.BigInteger;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BlankRecord implements Serializable{
	private Integer id;//胚料记录id
	private String consumeNum;//损耗编号
	//多对一
	private ProductModelInfo productModelInfo;//产品型号
	//多对一
	private Integer staffId;//登记者id
	private String staffName;//登记者名字
	private Long blowonDate;//开炉日期
	private Float materialConsume;//原料消耗总量
	private Float blankProduce;//胚料产出总量
	private Float lossRatio;//开炉损耗比
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getConsumeNum() {
		return consumeNum;
	}
	public void setConsumeNum(String consumeNum) {
		this.consumeNum = consumeNum;
	}
	public ProductModelInfo getProductModelInfo() {
		return productModelInfo;
	}
	public void setProductModelInfo(ProductModelInfo productModelInfo) {
		this.productModelInfo = productModelInfo;
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
	public Long getBlowonDate() {
		return blowonDate;
	}
	public void setBlowonDate(Long blowonDate) {
		this.blowonDate = blowonDate;
	}
	public Float getMaterialConsume() {
		return materialConsume;
	}
	public void setMaterialConsume(Float materialConsume) {
		this.materialConsume = materialConsume;
	}
	public Float getBlankProduce() {
		return blankProduce;
	}
	public void setBlankProduce(Float blankProduce) {
		this.blankProduce = blankProduce;
	}
	public Float getLossRatio() {
		return lossRatio;
	}
	public void setLossRatio(Float lossRatio) {
		this.lossRatio = lossRatio;
	}
}
