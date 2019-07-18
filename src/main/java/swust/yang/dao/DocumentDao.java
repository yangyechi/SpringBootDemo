package swust.yang.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import swust.yang.pojo.Document;
import swust.yang.vo.VODocument;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface DocumentDao {

	/**
	 * 根据id删除Doucument
	 */
	int delById(Integer id);

	/**
	 * 
	 * 根据新增Doucument,id自增
	 */
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int save(Document record);

	/**
	 * 根据id查询Doucument
	 */
	Document getById(Integer id);

	/**
	 * 根据id更新Doucument
	 */
	int editById(Document record);

	/**
	 * 根据id更新下载次数
	 */
	int editDownloadsById(HashMap<String,Object> map);
	
	/**
	 * 查询所有Doucument
	 */
	List<VODocument> list(String tag);

	/**
	 * 分页查询查询所有Doucument
	 */
	List<Document> pagelist(@Param("page") int page, @Param("limit") int limit);

}