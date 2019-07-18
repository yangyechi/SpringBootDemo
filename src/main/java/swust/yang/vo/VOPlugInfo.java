package swust.yang.vo;

import java.sql.Date;

public class VOPlugInfo {
	/** 插件id */
	private Integer id;
	
	/** 插件名 */
	private String name;

	/** 插件描述 */
	private String description;

	/** 插件版本 */
	private String version;

	/** 插件维护者 */
	private String maintainers;

	/** 插件开发者 */
	private String developers;

	/** 插件上传时间 */
	private Date date;

	/** 插件下载次数 */
	private String downloads;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMaintainers() {
		return maintainers;
	}

	public void setMaintainers(String maintainers) {
		this.maintainers = maintainers;
	}

	public String getDevelopers() {
		return developers;
	}

	public void setDevelopers(String developers) {
		this.developers = developers;
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
		this.downloads = downloads;
	}
}
