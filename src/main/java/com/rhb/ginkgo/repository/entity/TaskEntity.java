package com.rhb.ginkgo.repository.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="t_task")
public class TaskEntity {
	@Id
	private String id;
	private String subject;
	private String content;
	private Integer status;
	private Integer type;
	private Integer tasklivestatus;
	private Timestamp planfinishtime;
	private Integer important;
	private String attachmentid;
	private String url;
	private String phoneurl;
	private Integer isdel;
	private Timestamp createtime;
	private String creater;
	private Timestamp updatetime;
	private String updater;
	private String subject_md5;
	private String system;
	
	@OneToMany(cascade=CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name="taskid")
	private Set<TaskdetailEntity> taskdetails = new HashSet<TaskdetailEntity>();
	

	@OneToMany(cascade=CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name="taskid")
	@OrderBy("type")
	private Set<TaskuserEntity> taskusers = new HashSet<TaskuserEntity>();

	
	public Set<TaskdetailEntity> getTaskdetails() {
		return taskdetails;
	}

	public void setTaskdetails(Set<TaskdetailEntity> taskdetails) {
		this.taskdetails = taskdetails;
	}

	public TaskEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTasklivestatus() {
		return tasklivestatus;
	}

	public void setTasklivestatus(Integer tasklivestatus) {
		this.tasklivestatus = tasklivestatus;
	}

	public Timestamp getPlanfinishtime() {
		return planfinishtime;
	}

	public void setPlanfinishtime(Timestamp planfinishtime) {
		this.planfinishtime = planfinishtime;
	}

	public Integer getImportant() {
		return important;
	}

	public void setImportant(Integer important) {
		this.important = important;
	}

	public String getAttachmentid() {
		return attachmentid;
	}

	public void setAttachmentid(String attachmentid) {
		this.attachmentid = attachmentid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPhoneurl() {
		return phoneurl;
	}

	public void setPhoneurl(String phoneurl) {
		this.phoneurl = phoneurl;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
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

	public Timestamp getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getSubject_md5() {
		return subject_md5;
	}

	public void setSubject_md5(String subject_md5) {
		this.subject_md5 = subject_md5;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public Set<TaskuserEntity> getTaskusers() {
		return taskusers;
	}

	public void setTaskusers(Set<TaskuserEntity> taskusers) {
		this.taskusers = taskusers;
	}
	
	
	
	
}
