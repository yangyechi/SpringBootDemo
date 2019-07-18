package swust.yang.service;

import java.util.HashMap;
import java.util.List;

import swust.yang.pojo.PlugInfo;
import swust.yang.vo.VOPlugIdAndName;
import swust.yang.vo.VOPlugInfo;

import org.springframework.transaction.annotation.Transactional;

public interface PlugInfoService {

	/**
	 * 根据id删除PlugInfo 支持回滚
	 */
	@Transactional
	int delById(Integer id);

	/**
	 * 根据新增PlugInfo，id自增 支持回滚
	 */
	@Transactional
	int save(PlugInfo record);

	/**
	 * 根据id查询PlugInfo
	 */
	PlugInfo getById(Integer id);

	/**
	 * 根据id更新PlugInfo 支持回滚
	 */
	@Transactional
	int editById(PlugInfo record);

	/**
	 * 根据id更新下载次数 支持回滚
	 */
	@Transactional
	int editDownloadsById(HashMap<String,Object> map);
	
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
	 * 查询所有PlugInfo
	 */
	List<VOPlugIdAndName> listIdAndName();

	/**
	 * 分页查询PlugInfo
	 */
	List<PlugInfo> pagelist(int page, int limit);
}