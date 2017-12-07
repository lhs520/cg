package scut.legend.cg.po;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 仓库订单
 * @author yaoyou
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(value={"staff"})
public class WarehouseOrder implements Serializable{
	private Integer id;//仓库订单id
	private String orderNum;//订单编号
	private Staff staff;//登记者
	private Integer staffId;
	private String staffName;//登记者名字
	private Product product;//产品
	private Customer customer;//客户
	private Long orderDate;//订单日期
	
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public Integer getWarehouseManagerId() {
		return warehouseManagerId;
	}
	public void setWarehouseManagerId(Integer warehouseManagerId) {
		this.warehouseManagerId = warehouseManagerId;
	}
	public String getWarehouseManagerName() {
		return warehouseManagerName;
	}
	public void setWarehouseManagerName(String warehouseManagerName) {
		this.warehouseManagerName = warehouseManagerName;
	}
	private Float deliveryQuantityTotal;//总量
	private Float deliveryQuantityNeed;//需配送量
	private Float unitPrice;//单价
	private Float totalPrice;//总价
	private String orderStatus;//订单状态
	private Long deliveryDate;//送达日期
	private Integer warehouseManagerId;//仓库管理员Id
	private String warehouseManagerName;//仓库管理员名字
	
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
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Long getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Long orderDate) {
		this.orderDate = orderDate;
	}
	public Float getDeliveryQuantityTotal() {
		return deliveryQuantityTotal;
	}
	public void setDeliveryQuantityTotal(Float deliveryQuantityTotal) {
		this.deliveryQuantityTotal = deliveryQuantityTotal;
	}
	public Float getDeliveryQuantityNeed() {
		return deliveryQuantityNeed;
	}
	public void setDeliveryQuantityNeed(Float deliveryQuantityNeed) {
		this.deliveryQuantityNeed = deliveryQuantityNeed;
	}
	public Float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Long getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Long deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	
}
