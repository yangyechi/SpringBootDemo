package swust.yang.pojo;

import java.io.Serializable;
import java.sql.Date;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(Include.NON_NULL)

/** Doucument */
public class Document implements Serializable {
	/** 文档id */
	private Integer id;

	/** 插件id */
	private Integer plugId;

	/** 文档名 */
	@Size(min = 1, max = 255)
	@Excel(name = "文档名", orderNum = "1")
	@ApiModelProperty(value = "文档名")
	private String name;

	/** 文档描述 */
	@Size(min = 1, max = 255)
	@Excel(name = "文档描述", orderNum = "1")
	@ApiModelProperty(value = "文档描述")
	private String description;

	/** 文档作者 */
	@Size(min = 1, max = 255)
	@Excel(name = "文档作者", orderNum = "1")
	@ApiModelProperty(value = "文档作者")
	private String author;

	/** 文档上传时间 */
	private Date date;

	/** 文档下载次数 */
	@Size(min = 1, max = 255)
	@Excel(name = "文档下载次数", orderNum = "1")
	@ApiModelProperty(value = "文档下载次数")
	private String downloads;

	/** 文档所在的物理位置 */
	@Size(min = 1, max = 255)
	@Excel(name = "文档所在的物理位置", orderNum = "1")
	@ApiModelProperty(value = "文档所在的物理位置")
	private String location;

	/** 文档标签 */
	@Size(min = 1, max = 255)
	@Excel(name = "文档标签", orderNum = "1")
	@ApiModelProperty(value = "文档标签")
	private String tag;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlugId() {
		return plugId;
	}

	public void setPlugId(Integer plugId) {
		this.plugId = plugId;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag == null ? null : tag.trim();
	}

}