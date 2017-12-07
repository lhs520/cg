package scut.legend.cg.po;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 仓库配送记录
 * @author yaoyou
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(value={"staff"})
public class WarehouseDeliveryRecord implements Serializable{
	private Integer id;//仓库配送记录id
	private String deliveryNum;//配送编号
	private Staff staff;//登记者
	private Integer staffId;
	private String staffName;//登记者名字
	private WarehouseOrder warehouseOrder;//订单
	private Long deliveryDate;//配送日期
	private Float deliveryQuantity;//配送量
	
	
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
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
	public WarehouseOrder getWarehouseOrder() {
		return warehouseOrder;
	}
	public void setWarehouseOrder(WarehouseOrder warehouseOrder) {
		this.warehouseOrder = warehouseOrder;
	}
	public Long getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Float getDeliveryQuantity() {
		return deliveryQuantity;
	}
	public void setDeliveryQuantity(Float deliveryQuantity) {
		this.deliveryQuantity = deliveryQuantity;
	}
	
}
