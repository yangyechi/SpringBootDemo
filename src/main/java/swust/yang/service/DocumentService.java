package swust.yang.service;

import java.util.HashMap;
import java.util.List;

import swust.yang.pojo.Document;
import swust.yang.vo.VODocument;

import org.springframework.transaction.annotation.Transactional;

public interface DocumentService {

	/**
	 * 根据id删除Doucument 支持回滚
	 */
	@Transactional
	int delById(Integer id);

	/**
	 * 根据新增Doucument，id自增 支持回滚
	 */
	@Transactional
	int save(Document record);

	/**
	 * 根据id查询Doucument
	 */
	Document getById(Integer id);

	/**
	 * 根据id更新下载次数 支持回滚
	 */
	int editDownloadsById(HashMap<String,Object> map);
	
	/**
	 * 根据id更新Doucument 支持回滚
	 */
	@Transactional
	int editById(Document record);

	/**
	 * 查询所有Doucument
	 */
	List<VODocument> list(String tag);

	/**
	 * 分页查询Doucument
	 */
	List<Document> pagelist(int page, int limit);
}