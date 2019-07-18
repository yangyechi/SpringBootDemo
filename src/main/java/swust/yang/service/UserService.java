package swust.yang.service;

import java.util.List;

import swust.yang.dto.DTOUser;
import swust.yang.pojo.User;

import org.springframework.transaction.annotation.Transactional;

public interface UserService {

	/**
	 * 根据用户信息新增User，id自增，支持回滚，新增成功返回1
	 */
	@Transactional
	int save(User record);

	/**
	 * 根据id查询User
	 */
	User getById(Integer id);

	/**
	 * 根据用户名密码查询User
	 */
	User getByRecord(DTOUser record);
	
	/**
	 * 根据id更新User 支持回滚
	 */
	@Transactional
	int editById(User record);

	/**
	 * 查询所有User
	 */
	List<User> list();

	/**
	 * 分页查询User
	 */
	List<User> pagelist(int page, int limit);
}