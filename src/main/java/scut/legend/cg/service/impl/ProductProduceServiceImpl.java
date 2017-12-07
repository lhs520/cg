package scut.legend.cg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scut.legend.cg.dao.ProductProduceDao;
import scut.legend.cg.dao.FactoryProductInventoryDao;
import scut.legend.cg.dao.ProductDao;
import scut.legend.cg.po.BlankRecord;
import scut.legend.cg.po.FactoryProductInventory;
import scut.legend.cg.po.Product;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.ProductProduce;
import scut.legend.cg.po.Staff;
import scut.legend.cg.service.ProductProduceService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.StringUtils;
import scut.legend.cg.util.TimeUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service("productProduceService")
public class ProductProduceServiceImpl implements ProductProduceService{

	@Resource
	private ProductProduceDao productProduceDao;
	
	@Resource
	private ProductDao productDao; 
	
	@Resource FactoryProductInventoryDao factoryProductInventoryDao;
	
	@Override
	@Transactional
	public CommonDTO createOneProductProduce(ProductProduce productProduce) {
		Float ZERO=new Float(0.00);
		Staff staff=(Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
		if(productProduce==null||productProduce.getProduceDate()==null
							 ||productProduce.getProduceQuantity().compareTo(ZERO)<0
							 ||productProduce.getProduct()==null
							 ||productProduce.getProduct().getProductModelInfo()==null
							 ||StringUtils.isEmpty(productProduce.getProduct().getProductModelInfo().getProductModel())
							 ||StringUtils.isEmpty(productProduce.getProduct().getProductSize())
							 ||productProduce.getProduct().getProductShape()==null)
		{
			return new CommonDTO(Result.PARAM_ERROR);
		}
		
		//构造工厂出库编号
		StringBuilder sBuilder=new StringBuilder();
		String date=TimeUtils.formatD(productProduce.getProduceDate());
		sBuilder.append("GC");
		sBuilder.append(date);
		Long time=productProduce.getProduceDate();
		int num=productProduceDao.getDeliveryNumSuffix(time)+1;
		if(num>9999){
			return new CommonDTO(Result.CANNOT_CREATE_NUM);//TODO 抛出异常，待处理
		}
		for(int i=0;i<4-new String(num+"").length();i++){
			sBuilder.append("0");
		}
		sBuilder.append(num);
		productProduce.setProduceNum(sBuilder.toString());
//		System.out.println(sBuilder.toString());
		//设置登记人信息，应该从Session中取出
//		productProduce.setStaffId(1);
//		productProduce.setStaffName("lhs");
		productProduce.setStaffId(staff.getStaffId());
		productProduce.setStaffName(staff.getStaffName());
		
		//检查product数据库表中是否有这个产品的存在，没有则新增一个产品
		Product p=productDao.getOneProduct(productProduce.getProduct().getProductModelInfo().getProductModel(),
				productProduce.getProduct().getProductSize(), productProduce.getProduct().getProductShape());
		//没有则新增一个产品
		if(p==null){
			productDao.createOneProduct(productProduce.getProduct());//新增新产品
			productProduceDao.createOneProductProduce(productProduce);//新增产品产出记录

			
			//工厂库存新增一个新产品库存记录
			FactoryProductInventory factoryProductInventory=new FactoryProductInventory();
			factoryProductInventory.setProduct(productProduce.getProduct());
			factoryProductInventory.setProductInventory(productProduce.getProduceQuantity());
			factoryProductInventoryDao.createOneFactoryProductInventory(factoryProductInventory);
		}
		//有则只增加对应产品的工厂库存量(先取得原来的库存，加上增加的库存后得到最新总库存)
		else{
			productProduceDao.createOneProductProduce(productProduce);
			
			FactoryProductInventory fpi=factoryProductInventoryDao.getOneFactoryProductInventoryByProductId(p.getProductId());
			FactoryProductInventory factoryProductInventory=new FactoryProductInventory();
			factoryProductInventory.setId(fpi.getId());
			factoryProductInventory.setProduct(productProduce.getProduct());
			factoryProductInventory.setProductInventory(productProduce.getProduceQuantity()+fpi.getProductInventory());//最新总库存
			factoryProductInventoryDao.updateOneFactoryProductInventory(factoryProductInventory);
		}
		CommonDTO result=new CommonDTO(Result.CREATE_PRODUCTPRODUCE_SUCCESS);
		return result;
	}
	//参数productProduce必须要包含id
	@Override
	@Transactional
	public CommonDTO updateOneProductProduce(ProductProduce productProduce) {
		Float ZERO=new Float(0.00);
		if(productProduce==null||productProduce.getProduceQuantity().compareTo(ZERO)<0
							 ||productProduce.getProduct()==null
							 ||productProduce.getProduct().getProductModelInfo()==null
							 ||StringUtils.isEmpty(productProduce.getProduct().getProductModelInfo().getProductModel())
							 ||StringUtils.isEmpty(productProduce.getProduct().getProductSize())
							 ||productProduce.getProduct().getProductShape()==null)
		{
			return new CommonDTO(Result.PARAM_ERROR);
		}
		//检查product数据库表中是否有这个产品的存在，没有则新增一个产品
		Product p=productDao.getOneProduct(productProduce.getProduct().getProductModelInfo().getProductModel(),
				productProduce.getProduct().getProductSize(), productProduce.getProduct().getProductShape());
		//没有则新增一个产品
		if(p==null){
			productDao.createOneProduct(productProduce.getProduct());//新增新产品
			//根据产品产出Id得到原来的产品产出记录
			ProductProduce oldproductProduce=productProduceDao.getProductProduceById(productProduce.getId());
			Float oldproductQuantity=oldproductProduce.getProduceQuantity();
			//更新到新的产品产出记录
			productProduceDao.updateOneProductProduce(productProduce);

			//工厂库存减少原来产品的库存
			FactoryProductInventory fpi=factoryProductInventoryDao.getOneFactoryProductInventoryByProductId(oldproductProduce.getProduct().getProductId());
			FactoryProductInventory f1=new FactoryProductInventory();
			f1.setId(fpi.getId());
			f1.setProduct(oldproductProduce.getProduct());
			f1.setProductInventory(fpi.getProductInventory()-oldproductQuantity);//最新总库存
			factoryProductInventoryDao.updateOneFactoryProductInventory(f1);
			
			//工厂库存新增新产品的一个记录
			FactoryProductInventory factoryProductInventory=new FactoryProductInventory();
			factoryProductInventory.setProduct(productProduce.getProduct());
			factoryProductInventory.setProductInventory(productProduce.getProduceQuantity());
			factoryProductInventoryDao.createOneFactoryProductInventory(factoryProductInventory);
			
			CommonDTO result=new CommonDTO(Result.UPDATE_PRODUCTPRODUCE_SUCCESS);
			return result;
		}
		//有则只增加对应产品的工厂库存量(先取得原来的库存，加上增加的库存后返回总库存)
		else{
			//根据产品产出Id得到原来的产品产出记录
			ProductProduce oldproductProduce=productProduceDao.getProductProduceById(productProduce.getId());
			Integer oldproductId=oldproductProduce.getProduct().getProductId();
			Float oldproductQuantity=oldproductProduce.getProduceQuantity();
			//更新到新的产品产出记录
			productProduceDao.updateOneProductProduce(productProduce);
			System.out.println(oldproductId.compareTo(p.getProductId())==0);

			//如果新旧产品的productId相同，只需要改变一个产品的库存
			if(oldproductId.compareTo(p.getProductId())==0){
				System.out.println("Yes"+(oldproductId==productProduce.getProduct().getProductId()));
				Float subtractNum=productProduce.getProduceQuantity()-oldproductQuantity;//改变量=新产出重量-旧产出总量
				FactoryProductInventory fpi=factoryProductInventoryDao.getOneFactoryProductInventoryByProductId(oldproductProduce.getProduct().getProductId());
				FactoryProductInventory f1=new FactoryProductInventory();
				f1.setId(fpi.getId());
				f1.setProduct(oldproductProduce.getProduct());
				f1.setProductInventory(fpi.getProductInventory()+subtractNum);//最新总库存
				factoryProductInventoryDao.updateOneFactoryProductInventory(f1);
			}
			//如果新旧产品的productId不同，需要同时改变新产品和旧产品的库存
			else{
				System.out.println("No"+(oldproductId==productProduce.getProduct().getProductId()));
				//减少旧产品的库存
				FactoryProductInventory fpi1=factoryProductInventoryDao.getOneFactoryProductInventoryByProductId(oldproductProduce.getProduct().getProductId());
				FactoryProductInventory f1=new FactoryProductInventory();
				f1.setId(fpi1.getId());
				f1.setProduct(oldproductProduce.getProduct());
				f1.setProductInventory(fpi1.getProductInventory()-oldproductQuantity);//最新总库存
				factoryProductInventoryDao.updateOneFactoryProductInventory(f1);
				
				//增加新产品的库存
				FactoryProductInventory fpi2=factoryProductInventoryDao.getOneFactoryProductInventoryByProductId(p.getProductId());
				FactoryProductInventory f2=new FactoryProductInventory();
				f2.setId(fpi2.getId());
				f2.setProduct(productProduce.getProduct());
				f2.setProductInventory(fpi2.getProductInventory()+productProduce.getProduceQuantity());//最新总库存
				factoryProductInventoryDao.updateOneFactoryProductInventory(f2);
			}
		}
		CommonDTO result=new CommonDTO(Result.UPDATE_PRODUCTPRODUCE_SUCCESS);
		return result;
	}

//	@Override
//	public CommonDTO getAllProductProduce() {
//		List<ProductProduce> pList=productProduceDao.getAllProductProduce();
//		CommonDTO result=new CommonDTO(Result.SUCCESS);
//		result.setResult(pList);
//		return result;
//	}

	@Override
	public CommonDTO getProductProduceByDate(Long beginTime, Long endTime,Integer pageNum) {
		if(beginTime>endTime||beginTime<0||endTime<0||pageNum<=0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		List<ProductProduce> pList=productProduceDao.getProductProduceByDate(beginTime, endTime,
				(pageNum-1)*PageUtils.NUMBER_PER_PAGE,PageUtils.NUMBER_PER_PAGE);
		
		if(pageNum==1){
			double count=productProduceDao.getCountByTime(beginTime, endTime);
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
