package swust.yang.pojo;

import java.io.Serializable;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)

/** User */
public class User implements Serializable {
	/** 用户id */
	private Integer id;

	/** 用户名 */
	@Size(min = 1, max = 255)
	@Excel(name = "用户名", orderNum = "1")
	@ApiModelProperty(value = "用户名")
	private String username;

	/** 密码 */
	@Size(min = 1, max = 255)
	@Excel(name = "密码", orderNum = "1")
	@ApiModelProperty(value = "密码")
	private String password;

	/** 角色，0代表管理员 */
	private Integer role;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}