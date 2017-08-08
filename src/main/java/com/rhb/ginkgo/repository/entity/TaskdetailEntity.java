package com.rhb.ginkgo.repository.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_taskdetail")
public class TaskdetailEntity {
	@Id
	private String id;
	private String empnum;
	private String taskid;
	private String content;
	private Integer type;
	private String attachmentid;
	private Timestamp createtime;
	private String creater;
	private Integer isrecall;
	private String systemid;
	
	public TaskdetailEntity() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmpnum() {
		return empnum;
	}
	public void setEmpnum(String empnum) {
		this.empnum = empnum;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getAttachmentid() {
		return attachmentid;
	}
	public void setAttachmentid(String attachmentid) {
		this.attachmentid = attachmentid;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Integer getIsrecall() {
		return isrecall;
	}
	public void setIsrecall(Integer isrecall) {
		this.isrecall = isrecall;
	}
	public String getSystemid() {
		return systemid;
	}
	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}
	@Override
	public String toString() {
		return "TaskdetailEntity [id=" + id + ", empnum=" + empnum + ", taskid=" + taskid + ", content=" + content
				+ ", type=" + type + ", attachmentid=" + attachmentid + ", createtime=" + createtime + ", creater="
				+ creater + ", isrecall=" + isrecall + ", systemid=" + systemid + "]";
	}
	
	
	
}
