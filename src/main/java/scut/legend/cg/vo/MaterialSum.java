package scut.legend.cg.vo;

public class MaterialSum {
	private Integer materialId;
	private String materialClass;
	private Double purchaseQuantitySum;
	private Double totalPriceSum;

	
	
	
	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public String getMaterialClass() {
		return materialClass;
	}

	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
	}

	public Double getPurchaseQuantitySum() {
		return purchaseQuantitySum;
	}

	public void setPurchaseQuantitySum(Double purchaseQuantitySum) {
		this.purchaseQuantitySum = purchaseQuantitySum;
	}

	public Double getTotalPriceSum() {
		return totalPriceSum;
	}

	public void setTotalPriceSum(Double totalPriceSum) {
		this.totalPriceSum = totalPriceSum;
	}

}
