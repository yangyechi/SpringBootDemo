package swust.yang.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import swust.yang.dto.DTOUser;
import swust.yang.pojo.User;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

	/**
	 * 
	 * 根据新增User,id自增
	 */
	@Options(useGeneratedKeys = true, keyProperty = "id")
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
	 * 根据id更新User
	 */
	int editById(User record);

	/**
	 * 查询所有User
	 */
	List<User> list();

	/**
	 * 分页查询查询所有User
	 */
	List<User> pagelist(@Param("page") int page, @Param("limit") int limit);

}