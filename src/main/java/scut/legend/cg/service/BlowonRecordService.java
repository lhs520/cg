package scut.legend.cg.service;

import java.util.List;

import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.Staff;
import scut.legend.cg.vo.CommonDTO;

public interface BlowonRecordService {
//	public CommonDTO getAllBlowonRecord();
	public CommonDTO getBlowonRecordByDate(Long beginTime,Long endTime,Integer pageNum);
	public CommonDTO createMultiBlowonRecord(List<BlowonRecord> list);
	public CommonDTO updateOneBlowonRecord(BlowonRecord blowonRecord);
}
