package com.rhb.ginkgo.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_attachment")
public class AttachmentEntity {
	@Id
	private String id;
	private String filename;
	private String url;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getHtml(){
		String str = "<a href='http://m.hqhop.com:10086"+url+"'>"+filename+"</a>";
		return str;
	}
	@Override
	public String toString() {
		return "AttachmentEntity [id=" + id + ", filename=" + filename + ", url=" + url + "]";
	}
	
	
	

}
