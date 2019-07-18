package swust.yang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import swust.yang.dto.DTOUser;
import swust.yang.pojo.User;
import swust.yang.service.UserService;
import swust.yang.util.JwtToken;
import swust.yang.util.ResultResponse;
import swust.yang.vo.VOUser;

/*
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
*/

@Api(tags = "User")
@RestController
@Validated
@RequestMapping("/User")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "登录请求")
	@PostMapping(value = "/login")
	public ResultResponse<VOUser> getById(@ApiParam(required = true, value = "账户信息") @Valid @RequestBody DTOUser user) {
		User userInfo = userService.getByRecord(user);
		if(userInfo != null) {
			VOUser data = new VOUser();
			String role = "";
			if(userInfo.getRole() == 0) {
				role = "admin";
			} else {
				role = "normal";
			}
			String token = JwtToken.creatToken(userInfo.getId().toString(), role);
			data.setRole(role);
			data.setToken(token);
			return new ResultResponse<VOUser>(0, "登录成功", data);
		} else {
			return new ResultResponse<VOUser>(1, "登录失败", null);
		}	
	}
	
	
	
/*
	@ApiOperation(value = "新增记录")
	@PostMapping(value = "/add")
	@ApiParam(required = true, value = "添加User")
	public ResultResponse<User> save(@ApiParam(required = true, value = "添加User") @Valid @RequestBody User record) {
		userService.save(record);
		return new ResultResponse<User>(0, "新增成功", record);
	}
	
	
	@ApiParam(required = true, value = "修改内容")
	@PostMapping(value = "/edit")
	public ResultResponse<User> editById(@ApiParam(required = true, value = "修改User") @Valid @RequestBody User record) {

		userService.editById(record);

		return new ResultResponse<User>(0, "修改成功", record);
	}

	@ApiOperation(value = "详细列表查询")
	@GetMapping(value = "/list")
	public ResultResponse list() {
		List<User> list = userService.list();
		return new ResultResponse(0, "查询成功", list);
	}

	@ApiOperation(value = "详细列表查询")
	@GetMapping(value = "/pagelist")
	public ResultResponse pagelist(
			@ApiParam(required = true, value = "查询Demo") @RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "limit", required = false) int limit) {
		List<User> list = userService.pagelist((page - 1) * limit, limit);
		return new ResultResponse(0, "查询成功", list, list.size());
	}
*/
}
