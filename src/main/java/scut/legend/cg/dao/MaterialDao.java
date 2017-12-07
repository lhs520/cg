package scut.legend.cg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import scut.legend.cg.po.Material;
import scut.legend.cg.po.MaterialPurchaseRecord;
import scut.legend.cg.vo.MaterialSum;

public interface MaterialDao {

	/**
	 * 根据起始时间和终止时间获取购买记录条数
	 * @param begin 起始时间
	 * @param end	终止时间
	 * @return		记录条数
	 */
	int getNumberByDate(@Param("begin")Long begin,@Param("end")Long end);

	/**
	 * 创建购买记录
	 * @param record
	 */
	void createMateralPurchaseRecord(MaterialPurchaseRecord record);

	/**
	 * 更新原料库存
	 * @param materialId	原料id
	 * @param purchaseQuantity	要增加/减少的数量
	 * @return	受影响的条数
	 */
	int updateMaterialInventory(@Param("materialId")Integer materialId,
								@Param("purchaseQuantity")Double purchaseQuantity);

	/**
	 * 根据记录id获取购买记录
	 * @param id 记录id
	 * @return 相应的购买记录
	 */
	MaterialPurchaseRecord getRecordById(Integer id);

	/**
	 * 更新采购记录
	 * @param record
	 */
	void updateMateralPurchaseRecord(MaterialPurchaseRecord record);

	/**
	 * 根据原料class获取库存
	 * @param cls
	 * @return 库存
	 */
	Float getInventoryByClass(String cls);
	
	/**
	 * 根据原料种类更新库存
	 * @param cls:原料种类
	 * @param number:更新的库存(正数为减少，负数为增加)
	 * @return	更新记录条数：1代表更新成功，0代表更新失败
	 */
	int updateInventoryByClass(@Param("cls")String cls,@Param("number")Float number);

	/**
	 * 根据起始时间和终止时间获得记录条数
	 * @param startTime
	 * @param endTime
	 * @return 记录条数
	 */
	int getCountByTime(@Param("startTime")Long startTime,@Param("endTime") Long endTime);

	/**
	 * 根据起始时间和终止时间获取采购记录列表
	 * @param startTime
	 * @param endTime
	 * @param begin
	 * @param number
	 * @return 记录列表
	 */
	List<MaterialPurchaseRecord> getRecordsByTime(@Param("startTime")Long startTime,@Param("endTime") Long endTime,@Param("begin") Integer begin,@Param("number") Integer number);

	/**
	 * 根据起始时间跟终止时间获取原料统计数据
	 * @param startTime
	 * @param endTime
	 * @return 几种原料的统计信息
	 */
	List<MaterialSum> statisticMaterialRecords(@Param("startTime")Long startTime,@Param("endTime") Long endTime);

	/**
	 * 获取原料列表
	 * @return
	 */
	List<Material> getMaterials();
	
	
}
