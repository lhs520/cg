package scut.legend.cg.po;

public class MaterialPurchaseRecord {
	private Integer id;// 原料采购记录id
	private Material material;// 原料
	private Integer staffId;// 登记者id
	private String staffName;// 登记者名字
	private String purchaseNum;// 采购编号
	private Long purchaseDate;// 采购日期
	private Double purchaseQuantity;// 采购量
	private Double unitPrice;// 单价
	private Double totalPrice;// 总价

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
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

	public String getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public Long getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Long purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(Double purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
