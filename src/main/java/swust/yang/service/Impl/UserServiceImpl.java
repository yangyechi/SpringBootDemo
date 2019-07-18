package swust.yang.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swust.yang.dao.UserDao;
import swust.yang.dto.DTOUser;
import swust.yang.pojo.User;
import swust.yang.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;

	@Override
	public int save(User record) {
		return userdao.save(record);
	}

	@Override
	public User getById(Integer id) {
		return userdao.getById(id);
	}

	@Override
	public User getByRecord(DTOUser record) {
		return userdao.getByRecord(record);
	}
	
	@Override
	public int editById(User record) {
		return userdao.editById(record);
	}

	@Override
	public List<User> list() {
		return userdao.list();
	}

	@Override
	public List<User> pagelist(int page, int limit) {
		return userdao.pagelist(page, limit);
	}

}
