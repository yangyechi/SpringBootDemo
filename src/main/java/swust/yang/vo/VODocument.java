package swust.yang.vo;

import java.sql.Date;

public class VODocument {
	/** 文档id */
	private Integer id;
	
	/** 文档名 */
	private String name;

	/** 文档描述 */
	private String description;

	/** 文档作者 */
	private String author;

	/** 文档上传时间 */
	private Date date;

	/** 文档下载次数 */
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
