package scut.legend.cg.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import scut.legend.cg.po.BlowonRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class MaterialServiceImplTest {
	@Resource
	private MaterialServiceImpl materialService;
	@Test
	public void testUpdateInventoryByBlowonRecords(){
		List<BlowonRecord> blowonRecords=new ArrayList<>();
		BlowonRecord record1=new BlowonRecord();
		record1.setConsumeAg(1.0f);
		record1.setConsumeCd(1.0f);
		record1.setConsumeCu(0.0f);
		record1.setConsumeSn(0.0f);
		record1.setConsumeZn(0.0f);
		BlowonRecord record2=new BlowonRecord();
		record2.setConsumeAg(1.0f);
		record2.setConsumeCd(1.0f);
		record2.setConsumeCu(0.0f);
		record2.setConsumeSn(0.0f);
		record2.setConsumeZn(0.0f);
		blowonRecords.add(record1);
		blowonRecords.add(record2);
		materialService.updateInventoryByBlowonRecords(blowonRecords);
	}
}
