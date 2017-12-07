package scut.legend.cg.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.mockito.internal.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import scut.legend.cg.dao.BlankRecordDao;
import scut.legend.cg.dao.BlowonRecordDao;
import scut.legend.cg.dao.MaterialDao;
import scut.legend.cg.dao.StaffDao;
import scut.legend.cg.exception.MaterialInventoryException;
import scut.legend.cg.exception.NullListException;
import scut.legend.cg.po.BlankRecord;
import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.Staff;
import scut.legend.cg.service.BlankRecordService;
import scut.legend.cg.service.BlowonRecordService;
import scut.legend.cg.service.MaterialService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.SMSUtils;
import scut.legend.cg.util.StringUtils;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service("blowonRecordService")
public class BlowonRecordServiceImpl implements BlowonRecordService{
	@Resource
	private BlowonRecordDao blowonRecordDao;
	
	@Resource
	private StaffDao staffDao;
	
	@Resource
	private MaterialDao materialDao;
	
	@Resource
	private MaterialService materialService;
	
	@Resource
	private BlankRecordDao blankRecordDao;
	
	@Resource
	private BlankRecordService blankRecordService;

	@Override
	@Transactional
	public CommonDTO createMultiBlowonRecord(List<BlowonRecord> list) {
		if(list==null&&list.size()<=0){
			throw new NullListException();
		}
		//调用接口，先判断原料库存是否足够开炉，足够则修改原料库存；修改成功返回true，失败则抛出异常
		boolean enough;
		try{
			enough=materialService.updateInventoryByBlowonRecords(list);
		}catch(MaterialInventoryException e){
			throw e;
		}catch (Exception e) {
			return new CommonDTO(Result.SYSTEM_EXCEPTION);
		}
		Staff staff=(Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
//		boolean enough=true;//暂时假数据
		//每一种原料的库存都足够
		if(enough){
			Float ZERO=new Float(0.00);
			Float ONEH=new Float(100.00);
			int count=0;
			if(list!=null&&list.size()>0)
				for(BlowonRecord blowonRecord:list)
				{
					//TODO 还需要对从Session取得的staffId和StaffName进行内容判断
					if(blowonRecord==null||blowonRecord.getBlowonDate()==null
							 ||blowonRecord.getConsumeAg().compareTo(ZERO)<0
							 ||blowonRecord.getConsumeCu().compareTo(ZERO)<0
							 ||blowonRecord.getConsumeZn().compareTo(ZERO)<0
							 ||blowonRecord.getConsumeCd().compareTo(ZERO)<0
							 ||blowonRecord.getConsumeSn().compareTo(ZERO)<0
							 ||blowonRecord.getMaterialConsume().compareTo(ZERO)<=0
							 ||blowonRecord.getWasteConsume().compareTo(ZERO)<0
							 ||blowonRecord.getLeftoverConsume().compareTo(ZERO)<0
							 ||blowonRecord.getBlankProduce().compareTo(ZERO)<0
							 ||blowonRecord.getLossRatio().compareTo(ZERO)<0
							 ||blowonRecord.getLossRatio().compareTo(ONEH)>0
							 ||blowonRecord.getProductModelInfo()==null
							 ||StringUtils.isEmpty(blowonRecord.getProductModelInfo().getProductModel()))
					{
						return new CommonDTO(Result.PARAM_ERROR);
					}
					//构造工厂出库编号
					StringBuilder sBuilder=new StringBuilder();
					String date=TimeUtils.formatD(blowonRecord.getBlowonDate());
					sBuilder.append("GK");
					sBuilder.append(date);
					Long time=blowonRecord.getBlowonDate();
					int num=blowonRecordDao.getDeliveryNumSuffix(time)+1+count;
					if(num>9999){
						return new CommonDTO(Result.CANNOT_CREATE_NUM);//TODO 抛出异常，待处理
					}
					for(int i=0;i<4-new String(num+"").length();i++){
						sBuilder.append("0");
					}
					sBuilder.append(num);
					blowonRecord.setBlowonNum(sBuilder.toString());
					count++;
					//System.out.println(sBuilder.toString());
					
					//设置登记人信息，应该从Session中取出
//					blowonRecord.setStaffId(1);
//					blowonRecord.setStaffName("lhs");
					blowonRecord.setStaffId(staff.getStaffId());
					blowonRecord.setStaffName(staff.getStaffName());
				}
			else{
				throw new NullListException();
			}

			blowonRecordDao.createMultiBlowonRecord(list);
//			
//			for (BlowonRecord blowonRecord : list) {
//				
//				//根据产品型号和开炉日期相对应地修改具有相同产品型号和开炉日期的一条胚料记录中损耗比的值
//				ProductModelInfo productModelInfo=blankRecordService.getProductModelInfoByProductModel(blowonRecord.getProductModelInfo().getProductModel());
//				Integer productModelInfoId=productModelInfo.getId();
//				BlankRecord blankRecord=blankRecordDao.getBlankRecordByProductModelInfoIdAndBlowonDate(
//						productModelInfoId, blowonRecord.getBlowonDate());
//				if(blankRecord!=null){
//					//计算损耗比
//					BlowonRecord blowonRecord2= blankRecordDao.getBlowonRecordByProductModelInfoIdAndBlowonDate(
//							productModelInfoId, blowonRecord.getBlowonDate());
//					Float mc=blowonRecord2.getMaterialConsume();
//					Float wc=blowonRecord2.getWasteConsume();
//					Float lc=blowonRecord2.getLeftoverConsume();
//					Float lossRatio=(Float)(((mc-(blankRecord.getBlankProduce()-wc-lc))/mc))*100;//好像没作用
//					System.out.println("lossRatio:"+lossRatio);
//					blankRecord.setLossRatio(lossRatio);//修改损耗比
//					blankRecord.setMaterialConsume(blowonRecord2.getMaterialConsume());//修改原料消耗总量
//					blankRecordDao.updateOneBlankRecord(blankRecord);
//				}
//			}
		}
		//原料最新库存
		Float inventoryAg=materialDao.getInventoryByClass("银");
		Float inventoryCu=materialDao.getInventoryByClass("铜");
		Float inventoryZn=materialDao.getInventoryByClass("锌");
		Float inventoryCd=materialDao.getInventoryByClass("镉");
		Float inventorySn=materialDao.getInventoryByClass("锡");
		Float THOUSAND=new Float(1000.00);
		//原料库存有一种少于1000kg时给一个经理发短信，通知原料库存不足
		if(inventoryAg.compareTo(THOUSAND)<0
							||inventoryCd.compareTo(THOUSAND)<0
							||inventoryCu.compareTo(THOUSAND)<0
							||inventorySn.compareTo(THOUSAND)<0
							||inventoryZn.compareTo(THOUSAND)<0){
			List<Staff> managerList=staffDao.getStaffsByRoleName("经理");
			try {
				SMSUtils.sendSMS(managerList.get(0).getStaffTel(), "原料库存不足，请登录系统及时采购库存");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		CommonDTO result=new CommonDTO(Result.CREATE_BLOWONRECORD_SUCCESS);
		return result;
	}

//	@Override
//	public CommonDTO getAllBlowonRecord() {
//		List<BlowonRecord> brList=blowonRecordDao.getAllBlowonRecord();
//		CommonDTO result=new CommonDTO(Result.SUCCESS);
//		result.setResult(brList);
//		return result;
//	}

	@Override
	@Transactional
	public CommonDTO updateOneBlowonRecord(BlowonRecord blowonRecord) {
		Float ZERO=new Float(0.00);
		Float ONEH=new Float(100.00);
		if(blowonRecord==null||blowonRecord.getConsumeAg().compareTo(ZERO)<0
							 ||blowonRecord.getConsumeCu().compareTo(ZERO)<0
							 ||blowonRecord.getConsumeZn().compareTo(ZERO)<0
							 ||blowonRecord.getConsumeCd().compareTo(ZERO)<0
							 ||blowonRecord.getConsumeSn().compareTo(ZERO)<0
							 ||blowonRecord.getMaterialConsume().compareTo(ZERO)<0
							 ||blowonRecord.getWasteConsume().compareTo(ZERO)<0
							 ||blowonRecord.getLeftoverConsume().compareTo(ZERO)<0
							 ||blowonRecord.getBlankProduce().compareTo(ZERO)<0
							 ||blowonRecord.getLossRatio().compareTo(ZERO)<0
							 ||blowonRecord.getLossRatio().compareTo(ONEH)>0
							 ||blowonRecord.getProductModelInfo()==null
							 ||StringUtils.isEmpty(blowonRecord.getProductModelInfo().getProductModel()))
		{
			return new CommonDTO(Result.PARAM_ERROR);
		}
		
		BlowonRecord oldblowonRecord=blowonRecordDao.getOneBlowonRecordById(blowonRecord.getId());
		//新原料消耗量与旧原料消耗量的差值，即原料还需要消耗的量
		Float sNumAg=blowonRecord.getConsumeAg()-oldblowonRecord.getConsumeAg();
		Float sNumCu=blowonRecord.getConsumeCu()-oldblowonRecord.getConsumeCu();
		Float sNumZn=blowonRecord.getConsumeZn()-oldblowonRecord.getConsumeZn();
		Float sNumCd=blowonRecord.getConsumeCd()-oldblowonRecord.getConsumeCd();
		Float sNumSn=blowonRecord.getConsumeSn()-oldblowonRecord.getConsumeSn();
//		System.out.println(sNumAg+" "+sNumCu+" "+sNumZn+" "+sNumCd+" "+sNumSn);
		//原料的旧库存
		Float inventoryAg=materialDao.getInventoryByClass("银");
		Float inventoryCu=materialDao.getInventoryByClass("铜");
		Float inventoryZn=materialDao.getInventoryByClass("锌");
		Float inventoryCd=materialDao.getInventoryByClass("镉");
		Float inventorySn=materialDao.getInventoryByClass("锡");
//		System.out.println(inventoryAg+" "+inventoryCu+" "+inventoryZn+" "+inventoryCd+" "+inventorySn);

		//原料库存不足，小于还需要消耗的量，无法开炉，更改开炉记录失败
		if(sNumAg.compareTo(inventoryAg)>0
				||sNumCu.compareTo(inventoryCu)>0
				||sNumZn.compareTo(inventoryZn)>0
				||sNumCd.compareTo(inventoryCd)>0
				||sNumSn.compareTo(inventorySn)>0){
			return new CommonDTO(Result.MATERIAL_NOT_ENOUGH);
		}
		//更新原料库存
		materialDao.updateInventoryByClass("银", sNumAg);
		materialDao.updateInventoryByClass("铜", sNumCu);
		materialDao.updateInventoryByClass("锌", sNumZn);
		materialDao.updateInventoryByClass("镉", sNumCd);
		materialDao.updateInventoryByClass("锡", sNumSn);
//		System.out.println((inventoryAg-sNumAg)+" "+(inventoryCu-sNumCu)+" "
//		+(inventoryZn-sNumZn)+" "+(inventoryCd-sNumCd)+" "+(inventorySn-sNumSn));
		
		//先更新开炉记录，后面进行损耗比和原料消耗总量计算和修改
		blowonRecordDao.updateOneBlowonRecord(blowonRecord);
		
//		//根据产品型号和开炉日期相对应地修改具有相同产品型号和开炉日期的一条胚料记录中损耗比的值
//		ProductModelInfo productModelInfo=blankRecordService.getProductModelInfoByProductModel(blowonRecord.getProductModelInfo().getProductModel());
//		Integer productModelInfoId=productModelInfo.getId();
//		BlankRecord blankRecord=blankRecordDao.getBlankRecordByProductModelInfoIdAndBlowonDate(
//				productModelInfoId, oldblowonRecord.getBlowonDate());
//		if(blankRecord!=null){
//			//计算损耗比
//			BlowonRecord blowonRecord2= blankRecordDao.getBlowonRecordByProductModelInfoIdAndBlowonDate(
//					productModelInfoId, oldblowonRecord.getBlowonDate());
//			Float mc=blowonRecord2.getMaterialConsume();
//			Float wc=blowonRecord2.getWasteConsume();
//			Float lc=blowonRecord2.getLeftoverConsume();
//			Float lossRatio=(Float)(((mc-(blankRecord.getBlankProduce()-wc-lc))/mc))*100;//好像没作用
//			System.out.println("lossRatio:"+lossRatio);
//			blankRecord.setLossRatio(lossRatio);//修改损耗比
//			blankRecord.setMaterialConsume(blowonRecord2.getMaterialConsume());//修改原料消耗总量
//			blankRecordDao.updateOneBlankRecord(blankRecord);
//		}
		//原料最新库存
		Float inventoryAg2=materialDao.getInventoryByClass("银");
		Float inventoryCu2=materialDao.getInventoryByClass("铜");
		Float inventoryZn2=materialDao.getInventoryByClass("锌");
		Float inventoryCd2=materialDao.getInventoryByClass("镉");
		Float inventorySn2=materialDao.getInventoryByClass("锡");
		Float THOUSAND=new Float(1000.00);
		//原料库存有一种少于1000kg时给一个经理发短信，通知原料库存不足
		if(inventoryAg2.compareTo(THOUSAND)<0
							||inventoryCd2.compareTo(THOUSAND)<0
							||inventoryCu2.compareTo(THOUSAND)<0
							||inventorySn2.compareTo(THOUSAND)<0
							||inventoryZn2.compareTo(THOUSAND)<0){
			List<Staff> managerList=staffDao.getStaffsByRoleName("经理");
			try {
				SMSUtils.sendSMS(managerList.get(0).getStaffTel(), "原料库存不足，请登录系统及时采购库存");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		CommonDTO result=new CommonDTO(Result.UPDATE_BLOWONRECORD_SUCCESS);
		return result;
	}

	@Override
	public CommonDTO getBlowonRecordByDate(Long beginTime, Long endTime,Integer pageNum) {
		if(beginTime>endTime||beginTime<0||endTime<0||pageNum<=0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		List<BlowonRecord> brList=blowonRecordDao.getBlowonRecordByDate(beginTime,endTime,
				(pageNum-1)*PageUtils.NUMBER_PER_PAGE,PageUtils.NUMBER_PER_PAGE);//每页20条记录，可更改
		
		if(pageNum==1){
			double count=blowonRecordDao.getCountByTime(beginTime, endTime);
			int pageCount=(int)Math.ceil(count/PageUtils.NUMBER_PER_PAGE);//向上取整，得出最多需要多少页
//			System.out.println(pageCount);
//			System.out.println(count);
			CommonDTO result=new CommonDTO(Result.SUCCESS);
			result.setMsg(pageCount+"");
			result.setResult(brList);
			return result;
		}
		
		CommonDTO result=new CommonDTO(Result.SUCCESS);
		result.setResult(brList);
		return result;
	}
	
}
