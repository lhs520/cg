package scut.legend.cg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scut.legend.cg.dao.ProductModelInfoDao;
import scut.legend.cg.po.FactoryOutStorageRecord;
import scut.legend.cg.po.ProductModelInfo;
import scut.legend.cg.po.ProductProduce;
import scut.legend.cg.po.Staff;
import scut.legend.cg.service.ProductModelInfoService;
import scut.legend.cg.service.ProductProduceService;
import scut.legend.cg.util.PageUtils;
import scut.legend.cg.util.StringUtils;
import scut.legend.cg.vo.CommonDTO;
import scut.legend.cg.vo.Result;

@Service("productMedelInfoService")
public class ProductModelInfoServiceImpl implements ProductModelInfoService{
	@Resource
	private ProductModelInfoDao productModelInfoDao;

	@Override
	@Transactional
	public CommonDTO createOneProductModelInfo(ProductModelInfo productModelInfo) {
		Float ZERO=new Float(0.00);
		Float ONEH=new Float(100.00);
		Staff staff=(Staff) SecurityUtils.getSubject().getSession().getAttribute("staff");
		if(productModelInfo==null||StringUtils.isEmpty(productModelInfo.getProductModel())
							 ||productModelInfo.getAddDate()==null
							 ||productModelInfo.getRatioAg().compareTo(ZERO)<0
							 ||productModelInfo.getRatioAg().compareTo(ONEH)>0
							 ||productModelInfo.getRatioCu().compareTo(ZERO)<0
							 ||productModelInfo.getRatioCu().compareTo(ONEH)>0
							 ||productModelInfo.getRatioZn().compareTo(ZERO)<0
							 ||productModelInfo.getRatioZn().compareTo(ONEH)>0
							 ||productModelInfo.getRatioCd().compareTo(ZERO)<0
							 ||productModelInfo.getRatioCd().compareTo(ONEH)>0
							 ||productModelInfo.getRatioSn().compareTo(ZERO)<0
							 ||productModelInfo.getRatioSn().compareTo(ONEH)>0)
		{
			return new CommonDTO(Result.PARAM_ERROR);
		}
		//设置登记人信息，应该从Session中取出
//		productModelInfo.setStaffId(1);
//		productModelInfo.setStaffName("lhs");
		productModelInfo.setStaffId(staff.getStaffId());
		productModelInfo.setStaffName(staff.getStaffName());
		
		productModelInfoDao.createOneProductModelInfo(productModelInfo);
		CommonDTO result=new CommonDTO(Result.CREATE_PRODUCTMODELINFO_SUCCESS);
		return result;
	}

	@Override
	@Transactional
	public CommonDTO updateOneProductModelInfo(ProductModelInfo productModelInfo) {
		Float ZERO=new Float(0.00);
		Float ONEH=new Float(100.00);
		if(productModelInfo==null||StringUtils.isEmpty(productModelInfo.getProductModel())
							 ||productModelInfo.getRatioAg().compareTo(ZERO)<0
							 ||productModelInfo.getRatioAg().compareTo(ONEH)>0
							 ||productModelInfo.getRatioCu().compareTo(ZERO)<0
							 ||productModelInfo.getRatioCu().compareTo(ONEH)>0
							 ||productModelInfo.getRatioZn().compareTo(ZERO)<0
							 ||productModelInfo.getRatioZn().compareTo(ONEH)>0
							 ||productModelInfo.getRatioCd().compareTo(ZERO)<0
							 ||productModelInfo.getRatioCd().compareTo(ONEH)>0
							 ||productModelInfo.getRatioSn().compareTo(ZERO)<0
							 ||productModelInfo.getRatioSn().compareTo(ONEH)>0)
		{
			return new CommonDTO(Result.PARAM_ERROR);
		}
		productModelInfoDao.updateOneProductModelInfo(productModelInfo);
		CommonDTO result=new CommonDTO(Result.UPDATE_PRODUCTMODELINFO_SUCCESS);
		return result;
	}

//	@Override
//	public CommonDTO getAllProductModelInfo() {
//		List<ProductModelInfo> pList=productModelInfoDao.getAllProductModelInfo();
//		CommonDTO result=new CommonDTO(Result.SUCCESS);
//		result.setResult(pList);
//		return result;
//	}

	@Override
	public CommonDTO getProductModelInfoByDate(Long beginTime, Long endTime,Integer pageNum) {
		if(beginTime>endTime||beginTime<0||endTime<0||pageNum<=0){
			return new CommonDTO(Result.PARAM_ERROR);
		}
		List<ProductModelInfo> pList=productModelInfoDao.getProductModelInfoByDate(beginTime, endTime,
				(pageNum-1)*PageUtils.NUMBER_PER_PAGE,PageUtils.NUMBER_PER_PAGE);
		
		if(pageNum==1){
			double count=productModelInfoDao.getCountByTime(beginTime, endTime);
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

	@Override
	public CommonDTO getAllProductModels() {
		List<String> productModels=productModelInfoDao.getAllProductModels();
		CommonDTO result=new CommonDTO(Result.SUCCESS);
		result.setResult(productModels);
		return result;
	}

}
