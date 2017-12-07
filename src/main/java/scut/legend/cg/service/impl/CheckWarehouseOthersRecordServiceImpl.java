package scut.legend.cg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scut.legend.cg.dao.CheckWarehouseOthersRecordDao;
import scut.legend.cg.exception.NullListException;
import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.po.CheckWarehouseOthersRecord;
import scut.legend.cg.po.ProductProduce;
import scut.legend.cg.po.Staff;
import scut.legend.cg.service.CheckWarehouseOthersRecordService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.StringUtils;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service("checkWarehouseOthersRecord")
public class CheckWarehouseOthersRecordServiceImpl implements CheckWarehouseOthersRecordService{
	@Resource
	private CheckWarehouseOthersRecordDao checkWarehouseOthersRecordDao;

	@Override
	@Transactional
	public CommonDTO createMultiCheckWarehouseOthersRecord(List<CheckWarehouseOthersRecord> list) {
		Float ZERO=new Float(0.00);
		int count=0;
		Staff staff=(Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
		if(list!=null&&list.size()>0)
			for (CheckWarehouseOthersRecord checkWarehouseOthersRecord : list) {
				//TODO 还需要对从Session取得的staffId和StaffName进行内容判断
				if(checkWarehouseOthersRecord==null||checkWarehouseOthersRecord.getProductModelInfo()==null
						   ||StringUtils.isEmpty(checkWarehouseOthersRecord.getProductModelInfo().getProductModel())
						   ||checkWarehouseOthersRecord.getCheckDate()==null
						   ||checkWarehouseOthersRecord.getBlankInventory().compareTo(ZERO)<0
						   ||checkWarehouseOthersRecord.getSemifinishedProductInventory().compareTo(ZERO)<0
						   ||checkWarehouseOthersRecord.getWasteInventory().compareTo(ZERO)<0
						   ||checkWarehouseOthersRecord.getLeftoverInventory().compareTo(ZERO)<0){
				return new CommonDTO(Result.PARAM_ERROR);
				}
				//构造工厂出库编号
				StringBuilder sBuilder=new StringBuilder();
				String date=TimeUtils.formatD(checkWarehouseOthersRecord.getCheckDate());
				sBuilder.append("GK");
				sBuilder.append(date);
				Long time=checkWarehouseOthersRecord.getCheckDate();
				int num=checkWarehouseOthersRecordDao.getDeliveryNumSuffix(time)+1+count;
				if(num>9999){
					return new CommonDTO(Result.CANNOT_CREATE_NUM);//TODO 抛出异常，待处理
				}
				for(int i=0;i<4-new String(num+"").length();i++){
					sBuilder.append("0");
				}
				sBuilder.append(num);
				checkWarehouseOthersRecord.setCheckNum(sBuilder.toString());
				count++;
				
				//设置登记人信息，应该从Session中取出
//				checkWarehouseOthersRecord.setStaffId(1);
//				checkWarehouseOthersRecord.setStaffName("lhs");
				checkWarehouseOthersRecord.setStaffId(staff.getStaffId());
				checkWarehouseOthersRecord.setStaffName(staff.getStaffName());
			}
		else{
			throw new NullListException();
		}
		checkWarehouseOthersRecordDao.createMultiCheckWarehouseOthersRecord(list);
		CommonDTO result=new CommonDTO(Result.CREATE_CHECKWOR_SUCCESS);
		return result;
	}

	@Override
	@Transactional
	public CommonDTO updateOneCheckWarehouseOthersRecord(CheckWarehouseOthersRecord checkWarehouseOthersRecord) {
		Float ZERO=new Float(0.00);
		if(checkWarehouseOthersRecord==null||checkWarehouseOthersRecord.getProductModelInfo()==null
										   ||StringUtils.isEmpty(checkWarehouseOthersRecord.getProductModelInfo().getProductModel())
										   ||checkWarehouseOthersRecord.getBlankInventory().compareTo(ZERO)<0
										   ||checkWarehouseOthersRecord.getSemifinishedProductInventory().compareTo(ZERO)<0
										   ||checkWarehouseOthersRecord.getWasteInventory().compareTo(ZERO)<0
										   ||checkWarehouseOthersRecord.getLeftoverInventory().compareTo(ZERO)<0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		checkWarehouseOthersRecordDao.updateOneCheckWarehouseOthersRecord(checkWarehouseOthersRecord);
		CommonDTO result=new CommonDTO(Result.UPDATE_CHECKWOR_SUCCESS);
		return result;
	}

//	@Override
//	public CommonDTO getAllCheckWarehouseOthersRecord() {
//		List<CheckWarehouseOthersRecord> pList=checkWarehouseOthersRecordDao.getAllCheckWarehouseOthersRecord();
//		CommonDTO result=new CommonDTO(Result.SUCCESS);
//		result.setResult(pList);
//		return result;
//	}

	@Override
	public CommonDTO getCheckWarehouseOthersRecordByDate(Long beginTime, Long endTime,Integer pageNum) {
		if(beginTime>endTime||beginTime<0||endTime<0||pageNum<=0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		List<CheckWarehouseOthersRecord> pList=checkWarehouseOthersRecordDao.getCheckWarehouseOthersRecordByDate(beginTime, endTime,
				(pageNum-1)*PageUtils.NUMBER_PER_PAGE,PageUtils.NUMBER_PER_PAGE);
		
		if(pageNum==1){
			double count=checkWarehouseOthersRecordDao.getCountByTime(beginTime, endTime);
			int pageCount=(int)Math.ceil(count/PageUtils.NUMBER_PER_PAGE);//向上取整，得出最多需要多少页
//			System.out.println(pageCount);
//			System.out.println(count);
			CommonDTO result=new CommonDTO(Result.SUCCESS);
			result.setMsg(pageCount+"");
			result.setResult(pList);
			return result;
		}
		
		CommonDTO result=new CommonDTO(Result.SUCCESS);
		result.setResult(pList);
		return result;
	}
}
