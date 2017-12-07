package scut.legend.cg.po;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 仓库入仓记录
 * @author yaoyou
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(value={"staff"})
public class WarehouseInStorageRecord implements Serializable{
	private Integer id;//仓库入仓记录id
	private String inStorageNum;//入库编号
	private Staff staff;//登记者
	private Integer staffId;
	private String staffName;//登记者名字
	private Product product;//产品
	private Long inStorageDate;//入库日期
	private Float inStorageQuantity;//入库总量
	
	
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
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
	public String getInStorageNum() {
		return inStorageNum;
	}
	public void setInStorageNum(String inStorageNum) {
		this.inStorageNum = inStorageNum;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Long getInStorageDate() {
		return inStorageDate;
	}
	public void setInStorageDate(Long inStorageDate) {
		this.inStorageDate = inStorageDate;
	}
	public Float getInStorageQuantity() {
		return inStorageQuantity;
	}
	public void setInStorageQuantity(Float inStorageQuantity) {
		this.inStorageQuantity = inStorageQuantity;
	}
	
	
}
