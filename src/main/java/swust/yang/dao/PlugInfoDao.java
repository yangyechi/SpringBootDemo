package swust.yang.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import swust.yang.pojo.PlugInfo;
import swust.yang.vo.VOPlugIdAndName;
import swust.yang.vo.VOPlugInfo;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlugInfoDao {

	/**
	 * 根据id删除PlugInfo
	 */
	int delById(Integer id);

	/**
	 * 
	 * 根据新增PlugInfo,id自增
	 */
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int save(PlugInfo record);

	/**
	 * 根据id查询PlugInfo
	 */
	PlugInfo getById(Integer id);
	
	/**
	 * 
	 * 根据id更新下载次数
	 */
	int editDownloadsById(HashMap<String,Object> map);

	/**
	 * 根据id更新PlugInfo
	 */
	int editById(PlugInfo record);

	/**
	 * 
	 * 根据搜索内容查询符合条件的插件
	 */
	List<VOPlugInfo> searchPlugs(String content);
	
	/**
	 * 查询所有PlugInfo
	 */
	List<VOPlugInfo> list();

	/**
	 * 查询所有PlugInfo的ID和Name
	 */
	List<VOPlugIdAndName> listIdAndName();
	
	/**
	 * 分页查询查询所有PlugInfo
	 */
	List<PlugInfo> pagelist(@Param("page") int page, @Param("limit") int limit);

}