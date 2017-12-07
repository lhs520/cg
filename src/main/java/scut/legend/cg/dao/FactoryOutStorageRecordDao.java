package scut.legend.cg.dao;

import java.util.List;

import scut.legend.cg.po.FactoryOutStorageRecord;
import scut.legend.cg.po.ProductProduce;

public interface FactoryOutStorageRecordDao {
	public int getDeliveryNumSuffix(Long time);
	public FactoryOutStorageRecord getOneFactoryOutStorageRecordById(Integer id);
	public void createOneFactoryOutStorageRecord(FactoryOutStorageRecord factoryOutStorageRecord);
	public void updateOneFactoryOutStorageRecord(FactoryOutStorageRecord factoryOutStorageRecord);
//	public List<FactoryOutStorageRecord> getAllFactoryOutStorageRecord();
	public List<FactoryOutStorageRecord> getFactoryOutStorageRecordByDate(Long beginTime,Long endTime,Integer offset,Integer numPerPage);
	public Integer getCountByTime(Long beginTime,Long endTime);
}
