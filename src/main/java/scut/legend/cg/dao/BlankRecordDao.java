package scut.legend.cg.dao;

import java.math.BigInteger;
import java.util.List;

import scut.legend.cg.po.BlankRecord;
import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.po.ProductModelInfo;

public interface BlankRecordDao {
	public ProductModelInfo getProductModelInfoByProductModel(String productMode);
//	public Float getBlowonRecordByProductModelInfoIdAndBlowonDate(Integer productModelInfoId,BigInteger blowonDate);
	public BlowonRecord getBlowonRecordByProductModelInfoIdAndBlowonDate(Integer productModelInfoId,Long blowonDate);
	public int getDeliveryNumSuffix(Long time);
	public BlankRecord getBlankRecordByProductModelInfoIdAndBlowonDate(Integer productModelInfoId,Long blowonDate);
	public void createMultiBlankRecord(List<BlankRecord> list);
	public void updateOneBlankRecord(BlankRecord blankRecord);
//	public List<BlankRecord> getAllBlankRecord();
	public List<BlankRecord> getBlankRecordByDate(Long beginTime,Long endTime,Integer offset,Integer numPerPage);
	public List<BlowonRecord> getAllBlowonRecord();
	public Integer getCountByTime(Long beginTime,Long endTime);
	public Integer getCountBlankRecordByProductModelInfoIdAndBlowonDate(Integer productModelInfoId,Long blowonDate);
}
