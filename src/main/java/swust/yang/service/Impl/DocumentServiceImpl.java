package swust.yang.service.Impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swust.yang.dao.DocumentDao;
import swust.yang.pojo.Document;
import swust.yang.service.DocumentService;
import swust.yang.vo.VODocument;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao doucumentdao;

	@Override
	public int delById(Integer id) {
		return doucumentdao.delById(id);
	}

	@Override
	public int save(Document record) {
		return doucumentdao.save(record);
	}

	@Override
	public Document getById(Integer id) {
		return doucumentdao.getById(id);
	}

	@Override
	public int editDownloadsById(HashMap<String,Object> map) {
		return doucumentdao.editDownloadsById(map);
	}
	
	@Override
	public int editById(Document record) {
		return doucumentdao.editById(record);
	}

	@Override
	public List<VODocument> list(String tag) {
		return doucumentdao.list(tag);
	}

	@Override
	public List<Document> pagelist(int page, int limit) {
		return doucumentdao.pagelist(page, limit);
	}

}
