package scut.legend.cg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scut.legend.cg.dao.CheckWarehouseProductRecordDao;
import scut.legend.cg.dao.FactoryOutStorageRecordDao;
import scut.legend.cg.dao.FactoryProductInventoryDao;
import scut.legend.cg.dao.ProductDao;
import scut.legend.cg.po.CheckWarehouseProductRecord;
import scut.legend.cg.po.FactoryOutStorageRecord;
import scut.legend.cg.po.FactoryProductInventory;
import scut.legend.cg.po.Product;
import scut.legend.cg.po.ProductProduce;
import scut.legend.cg.po.Staff;
import scut.legend.cg.service.FactoryOutStorageRecordService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.StringUtils;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service("factoryOutStorageRecordService")
public class FactoryOutStorageRecordServiceImpl implements FactoryOutStorageRecordService{

	@Resource
	private FactoryOutStorageRecordDao factoryOutStorageRecordDao;
	
	@Resource
	private ProductDao productDao; 
	
	@Resource FactoryProductInventoryDao factoryProductInventoryDao;
	
	@Override
	@Transactional
	public CommonDTO createOneFactoryOutStorageRecord(FactoryOutStorageRecord factoryOutStorageRecord) {
		Float ZERO=new Float(0.00);
		Staff staff=(Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
		//TODO 还需要对从Session取得的staffId和StaffName进行内容判断
		if(factoryOutStorageRecord==null||factoryOutStorageRecord.getOutStorageDate()==null
										||factoryOutStorageRecord.getOutStorageQuantity().compareTo(ZERO)<0
										||factoryOutStorageRecord.getProduct()==null
										||factoryOutStorageRecord.getProduct().getProductModelInfo()==null
										||StringUtils.isEmpty(factoryOutStorageRecord.getProduct().getProductModelInfo().getProductModel())
										||StringUtils.isEmpty(factoryOutStorageRecord.getProduct().getProductSize())
										||factoryOutStorageRecord.getProduct().getProductShape()==null){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		//构造工厂出库编号
		StringBuilder sBuilder=new StringBuilder();
		String date=TimeUtils.formatD(factoryOutStorageRecord.getOutStorageDate());
		sBuilder.append("GC");
		sBuilder.append(date);
		Long time=factoryOutStorageRecord.getOutStorageDate();
		int num=factoryOutStorageRecordDao.getDeliveryNumSuffix(time)+1;
		if(num>9999){
			return new CommonDTO(Result.CANNOT_CREATE_NUM);//TODO 抛出异常，待处理
		}
		for(int i=0;i<4-new String(num+"").length();i++){
			sBuilder.append("0");
		}
		sBuilder.append(num);
		factoryOutStorageRecord.setOutStorageNum(sBuilder.toString());
		System.out.println(sBuilder.toString()+" "+num);
		//设置登记人信息，应该从Session中取出
//		factoryOutStorageRecord.setStaffId(1);
//		factoryOutStorageRecord.setStaffName("lhs");
		factoryOutStorageRecord.setStaffId(staff.getStaffId());
		factoryOutStorageRecord.setStaffName(staff.getStaffName());
		System.out.println(factoryOutStorageRecord.getProduct().getProductModelInfo().getProductModel());
		//检查product数据库表中是否有这个产品的存在，没有则返回 产品不存在
		Product p=productDao.getOneProduct(factoryOutStorageRecord.getProduct().getProductModelInfo().getProductModel(),
				factoryOutStorageRecord.getProduct().getProductSize(), factoryOutStorageRecord.getProduct().getProductShape());
		//没有则返回 产品不存在
		if(p==null){
			return new CommonDTO(Result.PRODUCT_NOT_EXIST);
			//throw new ProductNotExistedException();
		}
		//有则只减少对应产品的工厂库存量(先取得原来的库存，减去出库数量后得到最新总库存)
		else{
			//获取产品的原库存
			FactoryProductInventory fpi=factoryProductInventoryDao.getOneFactoryProductInventoryByProductId(p.getProductId());
			Float totalInventory=fpi.getProductInventory();
			//产品库存大于出库数量，可以出库
			if(factoryOutStorageRecord.getOutStorageQuantity().compareTo(totalInventory)<=0){
				factoryOutStorageRecordDao.createOneFactoryOutStorageRecord(factoryOutStorageRecord);//新增产品出库记录
				//减少对应产品的库存 TODO 可能会失败，需要抛出自定义的异常并进行处理，产品产出操作也类似如此
				FactoryProductInventory factoryProductInventory=new FactoryProductInventory();
				factoryProductInventory.setId(fpi.getId());
				factoryProductInventory.setProduct(factoryOutStorageRecord.getProduct());
				factoryProductInventory.setProductInventory(totalInventory-factoryOutStorageRecord.getOutStorageQuantity());//最新总库存
				factoryProductInventoryDao.updateOneFactoryProductInventory(factoryProductInventory);
			}
			//产品库存小于出库数量，不可以出库
			else {
				return new CommonDTO(Result.OUT_STORAGE_TOO_LARGE);
			}

		}
		CommonDTO result=new CommonDTO(Result.CREATE_FACTORYOSR_SUCCESS);
		return result;
	}

	@Override
	@Transactional
	public CommonDTO updateOneFactoryOutStorageRecord(FactoryOutStorageRecord factoryOutStorageRecord) {
		Float ZERO=new Float(0.00);
		if(factoryOutStorageRecord==null||factoryOutStorageRecord.getOutStorageQuantity().compareTo(ZERO)<0
										||factoryOutStorageRecord.getProduct()==null
										||factoryOutStorageRecord.getProduct().getProductModelInfo()==null
										||StringUtils.isEmpty(factoryOutStorageRecord.getProduct().getProductModelInfo().getProductModel())
										||StringUtils.isEmpty(factoryOutStorageRecord.getProduct().getProductSize())
										||factoryOutStorageRecord.getProduct().getProductShape()==null){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		
		//检查product数据库表中是否有这个产品的存在，没有则新增一个产品
		Product p=productDao.getOneProduct(factoryOutStorageRecord.getProduct().getProductModelInfo().getProductModel(),
				factoryOutStorageRecord.getProduct().getProductSize(), factoryOutStorageRecord.getProduct().getProductShape());
		//没有则返回 产品不存在
		if(p==null){
			return new CommonDTO(Result.PRODUCT_NOT_EXIST);
			//throw new ProductNotExistedException();
		}
		//有则只减少对应产品的工厂库存量(先取得原来的库存，减去出库数量后得到最新总库存)
		else{
			//根据工厂出库Id得到旧产品的工厂出库记录
			FactoryOutStorageRecord oldF=factoryOutStorageRecordDao.getOneFactoryOutStorageRecordById(factoryOutStorageRecord.getId());
			Integer oldproductId=oldF.getProduct().getProductId();
			Float oldoutStorageQuantity=oldF.getOutStorageQuantity();
			System.out.println(oldoutStorageQuantity);
			//获取旧产品的库存
			FactoryProductInventory fpi1=factoryProductInventoryDao.getOneFactoryProductInventoryByProductId(oldF.getProduct().getProductId());
			Float oldproductInventory=fpi1.getProductInventory();
			System.out.println(oldproductInventory);
			
			//获取新产品的原库存
			FactoryProductInventory fpi=factoryProductInventoryDao.getOneFactoryProductInventoryByProductId(p.getProductId());
			Float newproductInventory=fpi.getProductInventory();
			System.out.println(newproductInventory);
			System.out.println(factoryOutStorageRecord.getOutStorageQuantity());
			System.out.println(oldproductId.compareTo(p.getProductId())==0);
			
			//如果新旧产品的productId相同，只需要改变一个产品的库存
			if(oldproductId.compareTo(p.getProductId())==0){
				
				//新旧产品出库记录中出库量的差值，即更新记录时工厂库存还需要出库的数量
				Float sNum=factoryOutStorageRecord.getOutStorageQuantity()-oldoutStorageQuantity;
				//产品库存大于还需要出库的数量，可以出库
				if(sNum.compareTo(oldproductInventory)<=0){
					factoryOutStorageRecordDao.updateOneFactoryOutStorageRecord(factoryOutStorageRecord);//更新到新的产品出库记录
					
					//改变对应产品的库存 TODO 可能会失败，需要抛出自定义的异常并进行处理，产品产出操作也类似如此
					Float subtractNum=oldoutStorageQuantity-factoryOutStorageRecord.getOutStorageQuantity();//改变量=旧出库数量-新出库数量
					FactoryProductInventory factoryProductInventory=new FactoryProductInventory();
					factoryProductInventory.setId(fpi1.getId());
					factoryProductInventory.setProduct(oldF.getProduct());
					factoryProductInventory.setProductInventory(oldproductInventory+subtractNum);//最新总库存
					System.out.println(subtractNum+"  "+(oldproductInventory+subtractNum));
					factoryProductInventoryDao.updateOneFactoryProductInventory(factoryProductInventory);
				}
				//产品库存小于出库数量，不可以出库
				else {
					return new CommonDTO(Result.OUT_STORAGE_TOO_LARGE);
				}
			}
			//如果新旧产品的productId不同，需要同时改变新产品和旧产品的库存
			else{
				//新产品的库存大于出库数量，可以出库
				if(factoryOutStorageRecord.getOutStorageQuantity().compareTo(newproductInventory)<=0){
					factoryOutStorageRecordDao.updateOneFactoryOutStorageRecord(factoryOutStorageRecord);//更新到新的产品出库记录
					
					//增加旧产品的库存 TODO 可能会失败，需要抛出自定义的异常并进行处理，产品产出操作也类似如此
					FactoryProductInventory factoryProductInventory=new FactoryProductInventory();
					factoryProductInventory.setId(fpi1.getId());
					factoryProductInventory.setProduct(oldF.getProduct());
					factoryProductInventory.setProductInventory(oldproductInventory+oldF.getOutStorageQuantity());//最新总库存
					factoryProductInventoryDao.updateOneFactoryProductInventory(factoryProductInventory);
					
					//减少新产品的库存
					FactoryProductInventory factoryProductInventory1=new FactoryProductInventory();
					factoryProductInventory1.setId(fpi.getId());
					factoryProductInventory1.setProduct(factoryOutStorageRecord.getProduct());
					factoryProductInventory1.setProductInventory(newproductInventory-factoryOutStorageRecord.getOutStorageQuantity());//最新总库存
					factoryProductInventoryDao.updateOneFactoryProductInventory(factoryProductInventory1);
				}
				//新产品的库存小于出库数量，不可以出库
				else {
					return new CommonDTO(Result.OUT_STORAGE_TOO_LARGE);
				}
			}
		}
		CommonDTO result=new CommonDTO(Result.UPDATE_FACTORYOSR_SUCCESS);
		return result;
	}

//	@Override
//	public CommonDTO getAllFactoryOutStorageRecord() {
//		List<FactoryOutStorageRecord> pList=factoryOutStorageRecordDao.getAllFactoryOutStorageRecord();
//		CommonDTO result=new CommonDTO(Result.SUCCESS);
//		result.setResult(pList);
//		return result;
//	}

	@Override
	public CommonDTO getFactoryOutStorageRecordByDate(Long beginTime, Long endTime,Integer pageNum) {
		if(beginTime>endTime||beginTime<0||endTime<0||pageNum<=0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		List<FactoryOutStorageRecord> pList=factoryOutStorageRecordDao.getFactoryOutStorageRecordByDate(beginTime, endTime,
				(pageNum-1)*PageUtils.NUMBER_PER_PAGE,PageUtils.NUMBER_PER_PAGE);
		
		if(pageNum==1){
			double count=factoryOutStorageRecordDao.getCountByTime(beginTime, endTime);
			int pageCount=(int)Math.ceil(count/PageUtils.NUMBER_PER_PAGE);//参数为double类型，向上取整，得出最多需要多少页
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
