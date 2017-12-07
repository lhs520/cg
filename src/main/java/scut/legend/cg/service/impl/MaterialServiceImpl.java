package scut.legend.cg.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.sound.midi.MidiChannel;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scut.legend.cg.dao.MaterialDao;
import scut.legend.cg.exception.MaterialInventoryException;
import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.po.Material;
import scut.legend.cg.po.MaterialPurchaseRecord;
import scut.legend.cg.po.Staff;
import scut.legend.cg.service.MaterialService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.MaterialSum;
import scut.legend.cg.vo.Result;
@Service("materialService")
public class MaterialServiceImpl implements MaterialService{
	@Resource
	private MaterialDao materialDao;
	
	@Override
	@Transactional
	public CommonDTO createMateralPurchaseRecord(MaterialPurchaseRecord record) {
		Staff staff=(Staff)SecurityUtils.getSubject().getSession().getAttribute("staff");
		if(staff==null){
			return new CommonDTO(Result.USER_NOT_LOGIN);
		}
		if(record.getMaterial()==null || record.getMaterial().getMaterialId()==null
				||record.getPurchaseDate()==null || record.getPurchaseQuantity()==null
				||record.getUnitPrice()==null || record.getTotalPrice()==null 
				||record.getPurchaseQuantity()<0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		record.setStaffId(staff.getStaffId());
		record.setStaffName(staff.getStaffName());
		
		StringBuilder purchaseNumString=new StringBuilder("GR");
		String dateString=TimeUtils.formatD(record.getPurchaseDate());
		purchaseNumString.append(dateString);
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(record.getPurchaseDate());;
		calendar.add(Calendar.DATE, 1);
		//根据这一天起始与结束的时间戳获得订单号
		int number=materialDao.getNumberByDate(record.getPurchaseDate(),calendar.getTimeInMillis())+1;
		String numberStr=number+"";
		for(int i=0;i<4-numberStr.length();i++){
			purchaseNumString.append('0');
		}
		purchaseNumString.append(number);
		record.setPurchaseNum(purchaseNumString.toString());
		//此处是一个事务
		//增加库存
		if(materialDao.updateMaterialInventory(record.getMaterial().getMaterialId(),record.getPurchaseQuantity())!=1){
			return new CommonDTO(Result.MATERIAL_NOT_EXISTED);
		}
		//增加购买记录
		materialDao.createMateralPurchaseRecord(record);
		CommonDTO result=new CommonDTO(Result.CREATE_MateralPurchaseRecord_SUCCESS);
		result.setResult(record);
		return result;
	}

	@Override
	@Transactional
	public CommonDTO updateMateralPurchaseRecord(MaterialPurchaseRecord record) {
		if(record.getPurchaseQuantity()!=null && record.getPurchaseQuantity()<0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		MaterialPurchaseRecord originRecord=materialDao.getRecordById(record.getId());
		if(originRecord==null){
			return new CommonDTO(Result.RECORD_NOT_EXISTED);
		}
		if(record.getMaterial()!=null && record.getMaterial().getMaterialId()!=null 
				&& record.getMaterial().getMaterialId()!=originRecord.getMaterial().getMaterialId()){
			//更改原料,恢复原来原料的库存
			materialDao.updateMaterialInventory(originRecord.getMaterial().getMaterialId(), -originRecord.getPurchaseQuantity());
			//修改新原料库存
			Double quantity=record.getPurchaseQuantity()!=null?record.getPurchaseQuantity():originRecord.getPurchaseQuantity();
			materialDao.updateMaterialInventory(record.getMaterial().getMaterialId(), quantity);
		}else if(record.getPurchaseQuantity()!=null && record.getPurchaseQuantity()!=originRecord.getPurchaseQuantity()){
			//修改了采购量,要更新原料的库存
			materialDao.updateMaterialInventory(originRecord.getMaterial().getMaterialId(), record.getPurchaseQuantity()-originRecord.getPurchaseQuantity());
		}
		//更改采购时间TODO
		
		materialDao.updateMateralPurchaseRecord(record);
		
		return new CommonDTO(Result.UPDATE_MateralPurchaseRecord_SUCCESS);
	}

	@Override
	public Float getInventoryByClass(String name) {
		return materialDao.getInventoryByClass(name);
	}

	@Override
	public CommonDTO searchMaterialPurchaseRecords(Integer page, Long startTime, Long endTime) {
		CommonDTO result=new CommonDTO(Result.SUCCESS);
		int number=materialDao.getCountByTime(startTime,endTime);
		int count=number/PageUtils.NUMBER_PER_PAGE+(number%PageUtils.NUMBER_PER_PAGE==0?0:1);
		result.setMsg(count+"");
		List<MaterialPurchaseRecord> purchaseRecords=materialDao.getRecordsByTime(startTime,endTime,(page-1)*PageUtils.NUMBER_PER_PAGE,PageUtils.NUMBER_PER_PAGE);
		result.setResult(purchaseRecords);
		return result;
	}

	@Override
	@Transactional
	public boolean updateInventoryByBlowonRecords(List<BlowonRecord> blowonRecords) {
		float AgNumber=0.0f,CuNumber=0.0f,ZnNumber=0.0f,CdNumber=0.0f,SnNumber=0.0f;
		for(BlowonRecord blowonRecord:blowonRecords){
			AgNumber+=blowonRecord.getConsumeAg();
			CuNumber+=blowonRecord.getConsumeCu();
			ZnNumber+=blowonRecord.getConsumeZn();
			CdNumber+=blowonRecord.getConsumeCd();
			SnNumber+=blowonRecord.getConsumeSn();
		}
		if(materialDao.updateInventoryByClass("银", AgNumber)!=1){
			throw new MaterialInventoryException("银的库存不足");
		}
		if(materialDao.updateInventoryByClass("铜", CuNumber)!=1){
			throw new MaterialInventoryException("铜的库存不足");
		}
		if(materialDao.updateInventoryByClass("锌", ZnNumber)!=1){
			throw new MaterialInventoryException("锌的库存不足");
		}
		if(materialDao.updateInventoryByClass("镉", CdNumber)!=1){
			throw new MaterialInventoryException("镉的库存不足");
		}
		if(materialDao.updateInventoryByClass("锡", SnNumber)!=1){
			throw new MaterialInventoryException("锡的库存不足");
		}
		return true;
	}

	@Override
	public CommonDTO statisticMaterialRecords(Long startTime, Long endTime) {
		CommonDTO result=new CommonDTO(Result.SUCCESS);
		List<MaterialSum> sums=materialDao.statisticMaterialRecords(startTime,endTime);
		result.setResult(sums);
		return result;
	}

	@Override
	public CommonDTO getInventories() {
		List<Material> materials=materialDao.getMaterials();
		CommonDTO result=new CommonDTO(Result.SUCCESS);
		result.setResult(materials);
		return result;
	}

}
