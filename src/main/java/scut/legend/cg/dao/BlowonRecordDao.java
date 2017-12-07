package scut.legend.cg.dao;

import java.util.List;

import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.Staff;
import scut.legend.cg.vo.CommonDTO;

public interface BlowonRecordDao {
//	public List<BlowonRecord> getAllBlowonRecord(Integer offset,Integer numPerPage);
	public List<BlowonRecord> getBlowonRecordByDate(Long beginTime,Long endTime,Integer offset,Integer numPerPage);
	public Integer getCountByTime(Long beginTime,Long endTime);
	public int getDeliveryNumSuffix(Long time);
	public void createMultiBlowonRecord(List<BlowonRecord> list);
	public void updateOneBlowonRecord(BlowonRecord blowonRecord);
	public BlowonRecord getOneBlowonRecordById(Integer id);
}
