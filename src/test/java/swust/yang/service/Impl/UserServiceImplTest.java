package swust.yang.service.Impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import swust.yang.dto.DTOUser;
import swust.yang.pojo.User;
import swust.yang.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	public UserService userService;
	
	@Test
	@Transactional
	public void testSave() {
		User record = new User();
		record.setUsername("admin");
		record.setPassword("admin123");
		record.setRole(0);
		int ret = userService.save(record);
		assertEquals(1,ret);
	}

	@Test
	public void testGetByRecord() {
		DTOUser record = new DTOUser();
		record.setUsername("admin");
		record.setPassword("admin1234");
		User user = userService.getByRecord(record);
		assertNull(user);
	}
	
	@Test
	public void testGetByRecord2() {
		DTOUser record = new DTOUser();
		record.setUsername("admin");
		record.setPassword("admin123");
		User user = userService.getByRecord(record);
		assertEquals("admin",user.getUsername());
		assertEquals("admin123",user.getPassword());
	}

}
