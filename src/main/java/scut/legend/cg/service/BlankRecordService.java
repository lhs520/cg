package scut.legend.cg.service;

import java.math.BigInteger;
import java.util.List;

import scut.legend.cg.po.BlankRecord;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.vo.CommonDTO;

public interface BlankRecordService {
	public ProductModelInfo getProductModelInfoByProductModel(String productModel);
	public CommonDTO getMaterialConsumeFromBlowonRecordByProductModelAndBlowonDate(String productModel,Long blowonDate);
	public CommonDTO createMultiBlankRecord(List<BlankRecord> list);
	public CommonDTO updateOneBlankRecord(BlankRecord blankRecord);
//	public CommonDTO getAllBlankRecord();
	public CommonDTO getBlankRecordByDate(Long beginTime,Long endTime,Integer pageNum);
}
