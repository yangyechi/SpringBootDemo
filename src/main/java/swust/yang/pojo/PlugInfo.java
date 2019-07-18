package swust.yang.pojo;

import java.io.Serializable;
import java.sql.Date;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)

/** PlugInfo */
public class PlugInfo implements Serializable {
	/** 插件id */
	private Integer id;

	/** 用户id */
	private Integer userId;

	/** 插件名 */
	@Size(min = 1, max = 255)
	@Excel(name = "插件名", orderNum = "1")
	@ApiModelProperty(value = "插件名")
	private String name;

	/** 插件描述 */
	@Size(min = 1, max = 255)
	@Excel(name = "插件描述", orderNum = "1")
	@ApiModelProperty(value = "插件描述")
	private String description;

	/** 插件版本 */
	@Size(min = 1, max = 255)
	@Excel(name = "插件版本", orderNum = "1")
	@ApiModelProperty(value = "插件版本")
	private String version;

	/** 插件维护者 */
	@Size(min = 1, max = 255)
	@Excel(name = "插件维护者", orderNum = "1")
	@ApiModelProperty(value = "插件维护者")
	private String maintainers;

	/** 插件开发者 */
	@Size(min = 1, max = 255)
	@Excel(name = "插件开发者", orderNum = "1")
	@ApiModelProperty(value = "插件开发者")
	private String developers;

	/** 插件上传时间 */
	private Date date;

	/** 插件下载次数 */
	@Size(min = 1, max = 255)
	@Excel(name = "插件下载次数", orderNum = "1")
	@ApiModelProperty(value = "插件下载次数")
	private String downloads;

	/** 插件所在物理位置 */
	@Size(min = 1, max = 255)
	@Excel(name = "插件所在物理位置", orderNum = "1")
	@ApiModelProperty(value = "插件所在物理位置")
	private String location;

	/** 插件所需环境 */
	@Size(min = 1, max = 255)
	@Excel(name = "插件所需环境", orderNum = "1")
	@ApiModelProperty(value = "插件所需环境")
	private String dependency;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
	}

	public String getMaintainers() {
		return maintainers;
	}

	public void setMaintainers(String maintainers) {
		this.maintainers = maintainers == null ? null : maintainers.trim();
	}

	public String getDevelopers() {
		return developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers == null ? null : developers.trim();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDownloads() {
		return downloads;
	}

	public void setDownloads(String downloads) {
		this.downloads = downloads == null ? null : downloads.trim();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location == null ? null : location.trim();
	}

	public String getDependency() {
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency == null ? null : dependency.trim();
	}

}