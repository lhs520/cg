package scut.legend.cg.service;

import java.util.List;

import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.po.CheckWarehouseOthersRecord;
import scut.legend.cg.vo.CommonDTO;

public interface CheckWarehouseOthersRecordService {
	public CommonDTO createMultiCheckWarehouseOthersRecord(List<CheckWarehouseOthersRecord> list);
	public CommonDTO updateOneCheckWarehouseOthersRecord(CheckWarehouseOthersRecord checkWarehouseOthersRecord);
//	public CommonDTO getAllCheckWarehouseOthersRecord();
	public CommonDTO getCheckWarehouseOthersRecordByDate(Long beginTime,Long endTime,Integer pageNum);
}
