package scut.legend.cg.service;

import java.util.List;

import scut.legend.cg.po.CheckWarehouseProductRecord;
import scut.legend.cg.vo.CommonDTO;

public interface CheckWarehouseProductRecordService {
	public CommonDTO createMultiCheckWarehouseProductRecord(List<CheckWarehouseProductRecord> list);
	public CommonDTO updateOneCheckWarehouseProductRecord(CheckWarehouseProductRecord checkWarehouseProductRecord);
//	public CommonDTO getAllCheckWarehouseProductRecord();
	public CommonDTO getCheckWarehouseProductRecordByDate(Long beginTime,Long endTime,Integer pageNum);
}
