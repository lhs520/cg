package scut.legend.cg.service;

import java.util.List;

import scut.legend.cg.po.BlowonRecord;
import scut.legend.cg.po.MaterialPurchaseRecord;
import scut.legend.cg.vo.CommonDTO;

public interface MaterialService {

	CommonDTO createMateralPurchaseRecord(MaterialPurchaseRecord record);

	CommonDTO updateMateralPurchaseRecord(MaterialPurchaseRecord record);

	Float getInventoryByClass(String name);

	CommonDTO searchMaterialPurchaseRecords(Integer page, Long startTime, Long endTime);

	boolean updateInventoryByBlowonRecords(List<BlowonRecord> blowonRecords);

	CommonDTO statisticMaterialRecords(Long startTime, Long endTime);

	CommonDTO getInventories();
}
