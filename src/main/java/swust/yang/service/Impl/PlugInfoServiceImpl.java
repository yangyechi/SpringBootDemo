package swust.yang.service.Impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swust.yang.dao.PlugInfoDao;
import swust.yang.pojo.PlugInfo;
import swust.yang.service.PlugInfoService;
import swust.yang.vo.VOPlugIdAndName;
import swust.yang.vo.VOPlugInfo;

@Service
public class PlugInfoServiceImpl implements PlugInfoService {

	@Autowired
	private PlugInfoDao plugInfodao;

	@Override
	public int delById(Integer id) {
		return plugInfodao.delById(id);
	}

	@Override
	public int save(PlugInfo record) {
		return plugInfodao.save(record);
	}

	@Override
	public PlugInfo getById(Integer id) {
		return plugInfodao.getById(id);
	}

	@Override
	public int editById(PlugInfo record) {
		return plugInfodao.editById(record);
	}

	@Override
	public int editDownloadsById(HashMap<String,Object> map) {
		return plugInfodao.editDownloadsById(map);
	}
	
	@Override
	public List<VOPlugInfo> searchPlugs(String content){
		return plugInfodao.searchPlugs(content);
	}
	
	@Override
	public List<VOPlugInfo> list() {
		return plugInfodao.list();
	}
	
	@Override
	public List<VOPlugIdAndName> listIdAndName(){
		return plugInfodao.listIdAndName();
	}
	
	@Override
	public List<PlugInfo> pagelist(int page, int limit) {
		return plugInfodao.pagelist(page, limit);
	}

}
