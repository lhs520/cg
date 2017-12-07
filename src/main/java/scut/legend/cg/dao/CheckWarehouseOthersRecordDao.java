package scut.legend.cg.dao;

import java.util.List;

import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.po.CheckWarehouseOthersRecord;
import scut.legend.cg.po.ProductProduce;

public interface CheckWarehouseOthersRecordDao {
	public int getDeliveryNumSuffix(Long time);
	public void createMultiCheckWarehouseOthersRecord(List<CheckWarehouseOthersRecord> list);
	public void updateOneCheckWarehouseOthersRecord(CheckWarehouseOthersRecord checkWarehouseOthersRecord);
//	public List<CheckWarehouseOthersRecord> getAllCheckWarehouseOthersRecord();
	public List<CheckWarehouseOthersRecord> getCheckWarehouseOthersRecordByDate(Long beginTime,Long endTime,Integer offset,Integer numPerPage);
	public Integer getCountByTime(Long beginTime,Long endTime);
}
