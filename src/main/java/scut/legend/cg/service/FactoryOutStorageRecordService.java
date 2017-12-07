package scut.legend.cg.service;

import java.util.List;

import scut.legend.cg.po.FactoryOutStorageRecord;
import scut.legend.cg.vo.CommonDTO;

public interface FactoryOutStorageRecordService {
	public CommonDTO createOneFactoryOutStorageRecord(FactoryOutStorageRecord factoryOutStorageRecord);
	public CommonDTO updateOneFactoryOutStorageRecord(FactoryOutStorageRecord factoryOutStorageRecord);
//	public CommonDTO getAllFactoryOutStorageRecord();
	public CommonDTO getFactoryOutStorageRecordByDate(Long beginTime,Long endTime,Integer pageNum);
}
