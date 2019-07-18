package swust.yang.service.Impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import swust.yang.pojo.PlugInfo;
import swust.yang.service.PlugInfoService;
import swust.yang.vo.VOPlugInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlugInfoServiceImplTest {

	@Autowired
	private PlugInfoService plugInfoService;
	
	@Test
	public void testSave() {
		PlugInfo record = new PlugInfo();
		record.setUserId(1);
		record.setDate(new Date(System.currentTimeMillis()));
		record.setName("执行检查插件");
		record.setDescription("对代码进行正确性验证");
		record.setVersion("1.0.1");
		record.setDevelopers("yangyechi");
		record.setMaintainers("yangyechi");
		record.setDownloads("0");
		record.setLocation("E:\\Plug\\perform.zip");
		int ret = plugInfoService.save(record);
		assertEquals(1,ret);
	}

	@Test
	public void testSearchPlugs() {
		List<VOPlugInfo> list = plugInfoService.searchPlugs("执行检查插件");
		for(VOPlugInfo item : list) {
			System.out.println(item.getName());
			System.out.println(item.getDescription());
			System.out.println(item.getDevelopers());
			System.out.println(item.getMaintainers());
			System.out.println(item.getVersion());
			System.out.println(item.getDate());
			System.out.println(item.getDownloads());
		}
	}

	@Test
	public void testList() {
		List<VOPlugInfo> list = plugInfoService.list();
		for(VOPlugInfo item : list) {
			System.out.println(item.getName());
			System.out.println(item.getDescription());
			System.out.println(item.getDevelopers());
			System.out.println(item.getMaintainers());
			System.out.println(item.getVersion());
			System.out.println(item.getDate());
			System.out.println(item.getDownloads());
		}
	}

}
