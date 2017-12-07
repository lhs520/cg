package scut.legend.cg.dao;

import java.util.List;

import scut.legend.cg.po.CheckWarehouseOthersRecord;
import scut.legend.cg.po.CheckWarehouseProductRecord;
import scut.legend.cg.po.ProductProduce;

public interface CheckWarehouseProductRecordDao {
	public int getDeliveryNumSuffix(Long time);
	public void createMultiCheckWarehouseProductRecord(List<CheckWarehouseProductRecord> list);
	public void updateOneCheckWarehouseProductRecord(CheckWarehouseProductRecord checkWarehouseProductRecord);
//	public List<CheckWarehouseProductRecord> getAllCheckWarehouseProductRecord();
	public List<CheckWarehouseProductRecord> getCheckWarehouseProductRecordByDate(Long beginTime,Long endTime,Integer offset,Integer numPerPage);
	public Integer getCountByTime(Long beginTime,Long endTime);
}
