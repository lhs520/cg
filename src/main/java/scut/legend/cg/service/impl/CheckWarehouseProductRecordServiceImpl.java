package scut.legend.cg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scut.legend.cg.dao.CheckWarehouseProductRecordDao;
import scut.legend.cg.exception.NullListException;
import scut.legend.cg.po.CheckWarehouseOthersRecord;
import scut.legend.cg.po.CheckWarehouseProductRecord;
import scut.legend.cg.po.ProductProduce;
import scut.legend.cg.po.Staff;
import scut.legend.cg.service.CheckWarehouseProductRecordService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.StringUtils;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service("checkWarehouseProductRecord")
public class CheckWarehouseProductRecordServiceImpl implements CheckWarehouseProductRecordService{
	@Resource
	private CheckWarehouseProductRecordDao checkWarehouseProductRecordDao;

	@Override
	@Transactional
	public CommonDTO createMultiCheckWarehouseProductRecord(List<CheckWarehouseProductRecord> list) {
		Float ZERO=new Float(0.00);
		int count=0;
		Staff staff=(Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
		if(list!=null&&list.size()>0)
			for (CheckWarehouseProductRecord checkWarehouseProductRecord : list) {
				//TODO 还需要对从Session取得的staffId和StaffName进行内容判断
				if(checkWarehouseProductRecord==null||checkWarehouseProductRecord.getProduct()==null
						  ||checkWarehouseProductRecord.getProduct().getProductModelInfo()==null
						  ||StringUtils.isEmpty(checkWarehouseProductRecord.getProduct().getProductModelInfo().getProductModel())
						  ||StringUtils.isEmpty(checkWarehouseProductRecord.getProduct().getProductSize())
						  ||checkWarehouseProductRecord.getProduct().getProductShape()==null
						  ||checkWarehouseProductRecord.getCheckDate()==null
						  ||checkWarehouseProductRecord.getProductInventory().compareTo(ZERO)<0){
				return new CommonDTO(Result.PARAM_ERROR);
				}
				//构造工厂出库编号
				StringBuilder sBuilder=new StringBuilder();
				String date=TimeUtils.formatD(checkWarehouseProductRecord.getCheckDate());
				sBuilder.append("GK");
				sBuilder.append(date);
				Long time=checkWarehouseProductRecord.getCheckDate();
				int num=checkWarehouseProductRecordDao.getDeliveryNumSuffix(time)+1+count;
				if(num>9999){
					return new CommonDTO(Result.CANNOT_CREATE_NUM);//TODO 抛出异常，待处理
				}
				for(int i=0;i<4-new String(num+"").length();i++){
					sBuilder.append("0");
				}
				sBuilder.append(num);
				checkWarehouseProductRecord.setCheckNum(sBuilder.toString());
				count++;
				
				//设置登记人信息，应该从Session中取出
//				checkWarehouseProductRecord.setStaffId(1);
//				checkWarehouseProductRecord.setStaffName("lhs");
				checkWarehouseProductRecord.setStaffId(staff.getStaffId());
				checkWarehouseProductRecord.setStaffName(staff.getStaffName());
			}
		else{
			throw new NullListException();
		}
		checkWarehouseProductRecordDao.createMultiCheckWarehouseProductRecord(list);
		CommonDTO result=new CommonDTO(Result.CREATE_CHECKWPR_SUCCESS);
		return result;
	}

	@Override
	@Transactional
	public CommonDTO updateOneCheckWarehouseProductRecord(CheckWarehouseProductRecord checkWarehouseProductRecord) {
		Float ZERO=new Float(0.00);
		if(checkWarehouseProductRecord==null||checkWarehouseProductRecord.getProduct()==null
									  ||checkWarehouseProductRecord.getProduct().getProductModelInfo()==null
									  ||StringUtils.isEmpty(checkWarehouseProductRecord.getProduct().getProductModelInfo().getProductModel())
									  ||StringUtils.isEmpty(checkWarehouseProductRecord.getProduct().getProductSize())
									  ||checkWarehouseProductRecord.getProduct().getProductShape()==null
									  ||checkWarehouseProductRecord.getProductInventory().compareTo(ZERO)<0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		checkWarehouseProductRecordDao.updateOneCheckWarehouseProductRecord(checkWarehouseProductRecord);
		CommonDTO result=new CommonDTO(Result.UPDATE_CHECKWPR_SUCCESS);
		return result;
	}

//	@Override
//	public CommonDTO getAllCheckWarehouseProductRecord() {
//		List<CheckWarehouseProductRecord> pList=checkWarehouseProductRecordDao.getAllCheckWarehouseProductRecord();
//		CommonDTO result=new CommonDTO(Result.SUCCESS);
//		result.setResult(pList);
//		return result;
//	}

	@Override
	public CommonDTO getCheckWarehouseProductRecordByDate(Long beginTime, Long endTime,Integer pageNum) {
		if(beginTime>endTime||beginTime<0||endTime<0||pageNum<=0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		List<CheckWarehouseProductRecord> pList=checkWarehouseProductRecordDao.getCheckWarehouseProductRecordByDate(beginTime, endTime,
				(pageNum-1)*PageUtils.NUMBER_PER_PAGE,PageUtils.NUMBER_PER_PAGE);
		
		if(pageNum==1){
			double count=checkWarehouseProductRecordDao.getCountByTime(beginTime, endTime);
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
