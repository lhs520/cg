package scut.legend.cg.po;

public class Material {
	private Integer materialId;// 原料id
	private String materialClass;// 原料种类
	private Float materialInventory;// 原料库存,单位:千克

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

	public Float getMaterialInventory() {
		return materialInventory;
	}

	public void setMaterialInventory(Float materialInventory) {
		this.materialInventory = materialInventory;
	}

}
