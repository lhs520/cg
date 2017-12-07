package scut.legend.cg.po;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//只将BlowonRecord中不为空的属性和属性值序列化并返回
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BlowonRecord implements Serializable{
	private Integer id;//开炉记录id
	private String blowonNum;//开炉编号
	//多对一个登记者
	private Integer staffId;//登记者id
	private String staffName;//登记者名字
	//多对一个产品型号
	private ProductModelInfo productModelInfo;//产品型号
	private Long blowonDate;//开炉日期
	private Float consumeAg;//银
	private Float consumeCu;//铜
	private Float consumeZn;//锌
	private Float consumeCd;//镉
	private Float consumeSn;//锡
	private Float materialConsume;//原料消耗总量
	private Float wasteConsume;//废料消耗量
	private Float leftoverConsume;//边角料消耗量
	private Float blankProduce;//胚料产出总量
	private Float lossRatio;//开炉损耗比
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBlowonNum() {
		return blowonNum;
	}
	public void setBlowonNum(String blowonNum) {
		this.blowonNum = blowonNum;
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
	public Long getBlowonDate() {
		return blowonDate;
	}
	public void setBlowonDate(Long blowonDate) {
		this.blowonDate = blowonDate;
	}
	public Float getConsumeAg() {
		return consumeAg;
	}
	public void setConsumeAg(Float consumeAg) {
		this.consumeAg = consumeAg;
	}
	public Float getConsumeCu() {
		return consumeCu;
	}
	public void setConsumeCu(Float consumeCu) {
		this.consumeCu = consumeCu;
	}
	public Float getConsumeZn() {
		return consumeZn;
	}
	public void setConsumeZn(Float consumeZn) {
		this.consumeZn = consumeZn;
	}
	public Float getConsumeCd() {
		return consumeCd;
	}
	public void setConsumeCd(Float consumeCd) {
		this.consumeCd = consumeCd;
	}
	public Float getConsumeSn() {
		return consumeSn;
	}
	public void setConsumeSn(Float consumeSn) {
		this.consumeSn = consumeSn;
	}
	public Float getMaterialConsume() {
		return materialConsume;
	}
	public void setMaterialConsume(Float materialConsume) {
		this.materialConsume = materialConsume;
	}
	public Float getWasteConsume() {
		return wasteConsume;
	}
	public void setWasteConsume(Float wasteConsume) {
		this.wasteConsume = wasteConsume;
	}
	public Float getLeftoverConsume() {
		return leftoverConsume;
	}
	public void setLeftoverConsume(Float leftoverConsume) {
		this.leftoverConsume = leftoverConsume;
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
