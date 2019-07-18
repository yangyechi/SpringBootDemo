package swust.yang.service.Impl;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import swust.yang.pojo.Document;
import swust.yang.service.DocumentService;
import swust.yang.vo.VODocument;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DoucumentServiceImplTest {

	@Autowired
	private DocumentService documentService;
	
	@Test
	public void testSave() {
		Document record = new Document();
		record.setName("插件接口文档");
		record.setPlugId(1);
		record.setDescription("所有插件共同的后台接口代码");
		record.setDownloads("0");
		record.setAuthor("yangyechi");
		record.setLocation("E:\\doc\\IPlug.doc");
		record.setDate(new Date(System.currentTimeMillis()));
		record.setTag("interface");
		int ret = documentService.save(record);
		assertEquals(1,ret);
	}

	@Test
	public void testGetById() {
		
	}

	@Test
	public void testList() {
		List<VODocument> list = documentService.list("interface");
		assertEquals("插件接口文档",list.get(0).getName());
	}

}
