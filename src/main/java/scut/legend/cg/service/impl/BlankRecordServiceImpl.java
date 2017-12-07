package scut.legend.cg.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scut.legend.cg.dao.BlankRecordDao;
import scut.legend.cg.exception.NullListException;
import scut.legend.cg.po.BlankRecord;
import scut.legend.cg.po.BlowonBlankRecord;
import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.Staff;
import scut.legend.cg.service.BlankRecordService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.StringUtils;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service("blankRecordService")
public class BlankRecordServiceImpl implements BlankRecordService{
	@Resource
	private BlankRecordDao blankRecordDao;

	@Override
	public ProductModelInfo getProductModelInfoByProductModel(String productModel) {
		return blankRecordDao.getProductModelInfoByProductModel(productModel);
	}

	@Override
	public CommonDTO getMaterialConsumeFromBlowonRecordByProductModelAndBlowonDate(String productModel, Long blowonDate) {
		ProductModelInfo productModelInfo=getProductModelInfoByProductModel(productModel);
		Integer productModelInfoId=productModelInfo.getId();
//		System.out.println("Count:"+blankRecordDao.getCountBlankRecordByProductModelInfoIdAndBlowonDate(productModelInfoId,blowonDate));
		//对应的胚料记录不存在，可以获取原料消耗总量，用于登记胚料记录
		if(blankRecordDao.getCountBlankRecordByProductModelInfoIdAndBlowonDate(productModelInfoId,blowonDate)==0){
			BlowonRecord blowonRecord= blankRecordDao.getBlowonRecordByProductModelInfoIdAndBlowonDate(productModelInfoId, blowonDate);
			if(blowonRecord!=null){
				System.out.println(blowonRecord.getMaterialConsume());
				System.out.println(blowonRecord.getWasteConsume());
				System.out.println(blowonRecord.getLeftoverConsume());
				HashMap<String, Float> bHashMap=new HashMap<>();
				bHashMap.put("materialConsume", blowonRecord.getMaterialConsume());
				bHashMap.put("wasteConsume", blowonRecord.getWasteConsume());
				bHashMap.put("leftoverConsume", blowonRecord.getLeftoverConsume());
				CommonDTO result=new CommonDTO(Result.SUCCESS);
				result.setResult(bHashMap);
				return result;
			}
			else{
				CommonDTO result=new CommonDTO(Result.BLOWONRECORD_TODAY_NOT_EXISTED);
				return result;
			}
		}
		
		CommonDTO result=new CommonDTO(Result.BLANKRECORD_TODAY_EXISTED);
		return result;
	}
	
	@Override
	@Transactional
	public CommonDTO createMultiBlankRecord(List<BlankRecord> list) {
		Float ZERO=new Float(0.00);
		int count=0;
		//从Session中取得staff信息
		Staff staff=(Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
		if(list!=null&&list.size()>0)
			for (BlankRecord blankRecord : list) {
				//TODO 还需要对从Session取得的staffId和StaffName进行内容判断
				if(blankRecord==null||blankRecord.getBlowonDate()==null
						 ||blankRecord.getMaterialConsume().compareTo(ZERO)<0
						 ||blankRecord.getBlankProduce().compareTo(ZERO)<0
						 ||blankRecord.getProductModelInfo()==null)
					{
						return new CommonDTO(Result.PARAM_ERROR);
					}
				//构造工厂出库编号
				StringBuilder sBuilder=new StringBuilder();
				String date=TimeUtils.formatD(blankRecord.getBlowonDate());
				sBuilder.append("SH");
				sBuilder.append(date);
				Long time=blankRecord.getBlowonDate();
				int num=blankRecordDao.getDeliveryNumSuffix(time)+1+count;
				if(num>9999){
					return new CommonDTO(Result.CANNOT_CREATE_NUM);//TODO 抛出异常，待处理
				}
				for(int i=0;i<4-new String(num+"").length();i++){
					sBuilder.append("0");
				}
				sBuilder.append(num);
				blankRecord.setConsumeNum(sBuilder.toString());
				count++;
				
				//计算损耗比
				ProductModelInfo productModelInfo=getProductModelInfoByProductModel(blankRecord.getProductModelInfo().getProductModel());
				Integer productModelInfoId=productModelInfo.getId();
//				Float materialConsume= blankRecordDao.getBlowonRecordByProductModelInfoIdAndBlowonDate(productModelInfoId, blowonDate);
				BlowonRecord blowonRecord= blankRecordDao.getBlowonRecordByProductModelInfoIdAndBlowonDate(productModelInfoId, blankRecord.getBlowonDate());
				Float mc=blowonRecord.getMaterialConsume();
				Float wc=blowonRecord.getWasteConsume();
				Float lc=blowonRecord.getLeftoverConsume();
				Float lossRatio=(Float)(((mc-(blankRecord.getBlankProduce()-wc-lc))/mc))*100;//好像没作用
//				System.out.println("lossRatio:"+lossRatio);
				blankRecord.setLossRatio(lossRatio);
				//设置登记人信息，应该从Session中取出
//				blankRecord.setStaffId(1);
//				blankRecord.setStaffName("lhs");
				blankRecord.setStaffId(staff.getStaffId());
				blankRecord.setStaffName(staff.getStaffName());

//				System.out.println(blowonRecord.getMaterialConsume());
//				System.out.println(blowonRecord.getWasteConsume());
//				System.out.println(blowonRecord.getLeftoverConsume());
//				System.out.println(lossRatio);
			}
		else{
			throw new NullListException();
		}
		
		blankRecordDao.createMultiBlankRecord(list);
		CommonDTO result=new CommonDTO(Result.CREATE_BLANKRECORD_SUCCESS);
		return result;
	}

	@Override
	@Transactional
	public CommonDTO updateOneBlankRecord(BlankRecord blankRecord) {
		Float ZERO=new Float(0.00);
		if(blankRecord==null||blankRecord.getMaterialConsume().compareTo(ZERO)<0
							 ||blankRecord.getBlankProduce().compareTo(ZERO)<0
							 ||blankRecord.getLossRatio().compareTo(ZERO)<0
							 ||blankRecord.getProductModelInfo()==null)
		{
			return new CommonDTO(Result.PARAM_ERROR);
		}
		
		blankRecordDao.updateOneBlankRecord(blankRecord);
		CommonDTO result=new CommonDTO(Result.UPDATE_BLANKRECORD_SUCCESS);
		return result;
	}

//	@Override
//	public CommonDTO getAllBlankRecord() {
//		List<BlowonRecord> brList=blankRecordDao.getAllBlowonRecord();
//		List<BlankRecord> bList=blankRecordDao.getAllBlankRecord();
//		List<BlowonBlankRecord> bbList=new ArrayList();
//		
//		for (BlankRecord blankRecord : bList) {
//			for(BlowonRecord blowonRecord:brList){
//				if(blankRecord.getProductModelInfo().getProductModel().equals(blowonRecord.getProductModelInfo().getProductModel())
//						&&blankRecord.getBlowonDate().compareTo(blowonRecord.getBlowonDate())==0
//					){
//					//每次必须新建一个BlowonBlankRecord，因为数组bbList存储的是每个BlowonBlankRecord的引用
//					BlowonBlankRecord blowonBlankRecord=new BlowonBlankRecord();
//					blowonBlankRecord.setBlankId(blankRecord.getId());
//					blowonBlankRecord.setConsumeNum(blankRecord.getConsumeNum());
//					blowonBlankRecord.setStaffId(blankRecord.getStaffId());
//					blowonBlankRecord.setStaffName(blankRecord.getStaffName());
//					blowonBlankRecord.setProductModel(blankRecord.getProductModelInfo().getProductModel());
//					blowonBlankRecord.setBlowonDate(blankRecord.getBlowonDate());
//					blowonBlankRecord.setConsumeAg(blowonRecord.getConsumeAg());
//					blowonBlankRecord.setConsumeCu(blowonRecord.getConsumeCu());
//					blowonBlankRecord.setConsumeZn(blowonRecord.getConsumeZn());
//					blowonBlankRecord.setConsumeCd(blowonRecord.getConsumeCd());
//					blowonBlankRecord.setConsumeSn(blowonRecord.getConsumeSn());
//					blowonBlankRecord.setMaterialConsume(blowonRecord.getMaterialConsume());
//					blowonBlankRecord.setWasteConsume(blowonRecord.getWasteConsume());
//					blowonBlankRecord.setLeftoverConsume(blowonRecord.getLeftoverConsume());
//					blowonBlankRecord.setBlankProduce(blankRecord.getBlankProduce());
//					blowonBlankRecord.setLossRatio(blankRecord.getLossRatio());
//					bbList.add(blowonBlankRecord);
////					System.out.println(blowonBlankRecord.getBlankId()+" "+blowonBlankRecord.getBlowonDate()+" "+blowonBlankRecord.getProductModel());
////					System.out.println(blowonBlankRecord);
//					break;
//				}
//			}
//		}
////		for (BlowonBlankRecord br : bbList) {
////			System.out.println(br.getBlankId()+" "+br.getBlowonDate()+" "+br.getProductModel());
////			System.out.println(br);
////		}
////		System.out.println("count:...................."+bList.size());
////		System.out.println("count:...................."+brList.size());
////		System.out.println("count:...................."+bbList.size());
//		CommonDTO result=new CommonDTO(Result.SUCCESS);
//		result.setResult(bbList);
//		return result;
//	}

	@Override
	public CommonDTO getBlankRecordByDate(Long beginTime, Long endTime,Integer pageNum) {
		if(beginTime>endTime||beginTime<0||endTime<0||pageNum<=0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		
		List<BlowonRecord> brList=blankRecordDao.getAllBlowonRecord();
//		List<BlankRecord> bList=blankRecordDao.getAllBlankRecord();
		//胚料记录存在的，开炉记录中必存在
		List<BlankRecord> bList=blankRecordDao.getBlankRecordByDate(beginTime,endTime,
				(pageNum-1)*PageUtils.NUMBER_PER_PAGE,PageUtils.NUMBER_PER_PAGE);//每页20条记录，可更改
		List<BlowonBlankRecord> bbList=new ArrayList();
		for (BlankRecord blankRecord : bList) {
			for(BlowonRecord blowonRecord:brList){
				if(blankRecord.getProductModelInfo().getProductModel().equals(blowonRecord.getProductModelInfo().getProductModel())
						&&blankRecord.getBlowonDate().compareTo(blowonRecord.getBlowonDate())==0
					){
					//每次必须新建一个BlowonBlankRecord，因为数组bbList存储的是每个BlowonBlankRecord的引用
					BlowonBlankRecord blowonBlankRecord=new BlowonBlankRecord();
					blowonBlankRecord.setId(blankRecord.getId());
					blowonBlankRecord.setConsumeNum(blankRecord.getConsumeNum());
					blowonBlankRecord.setStaffId(blankRecord.getStaffId());
					blowonBlankRecord.setStaffName(blankRecord.getStaffName());
					blowonBlankRecord.setProductModel(blankRecord.getProductModelInfo().getProductModel());
					blowonBlankRecord.setBlowonDate(blankRecord.getBlowonDate());
					blowonBlankRecord.setConsumeAg(blowonRecord.getConsumeAg());
					blowonBlankRecord.setConsumeCu(blowonRecord.getConsumeCu());
					blowonBlankRecord.setConsumeZn(blowonRecord.getConsumeZn());
					blowonBlankRecord.setConsumeCd(blowonRecord.getConsumeCd());
					blowonBlankRecord.setConsumeSn(blowonRecord.getConsumeSn());
					blowonBlankRecord.setMaterialConsume(blowonRecord.getMaterialConsume());
					blowonBlankRecord.setWasteConsume(blowonRecord.getWasteConsume());
					blowonBlankRecord.setLeftoverConsume(blowonRecord.getLeftoverConsume());
					blowonBlankRecord.setBlankProduce(blankRecord.getBlankProduce());
					blowonBlankRecord.setLossRatio(blankRecord.getLossRatio());
					bbList.add(blowonBlankRecord);
//					System.out.println(blowonBlankRecord.getBlankId()+" "+blowonBlankRecord.getBlowonDate()+" "+blowonBlankRecord.getProductModel());
//					System.out.println(blowonBlankRecord.getConsumeAg());
					break;
				}
			}
		}
		
		if(pageNum==1){
			double count=blankRecordDao.getCountByTime(beginTime, endTime);
			int pageCount=(int)Math.ceil(count/PageUtils.NUMBER_PER_PAGE);//向上取整，得出最多需要多少页
//			System.out.println(pageCount);
//			System.out.println(count);
			CommonDTO result=new CommonDTO(Result.SUCCESS);
			result.setMsg(pageCount+"");
			result.setResult(bbList);
			return result;
		}

		CommonDTO result=new CommonDTO(Result.SUCCESS);
		result.setResult(bbList);
		return result;
	}
	
}
