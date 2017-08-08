package com.rhb.ginkgo.repository.entity;

import java.util.UUID;

public class ProjectEntity {
	private String stageid = "";
	private String projectid;
	private String projectname ;
	private String description = "";
	private Integer orderNo = 0;
	private String taskids = "";

	public ProjectEntity(){
		super();
		this.stageid = "0";
		this.projectid = UUID.randomUUID().toString();
		this.projectname = "";
		this.description = "";
		this.orderNo = 0;
		this.taskids = "";
	}
	
	public ProjectEntity(String stageid,String projectid, String projectname, String description,Integer orderNo){
		super();
		this.stageid = stageid;
		this.projectid = projectid;
		this.projectname = projectname;
		this.description = description;
		this.orderNo = orderNo;
		this.taskids = "";
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStageid() {
		return stageid;
	}

	public void setStageid(String stageid) {
		this.stageid = stageid;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	
	
	public String getTaskids() {
		return taskids;
	}

	public void setTaskids(String taskids) {
		this.taskids = taskids;
	}

	@Override
	public String toString() {
		return "ProjectEntity [stageid=" + stageid + ", projectid=" + projectid + ", projectname=" + projectname
				+ ", description=" + description + ", orderNo=" + orderNo + ", taskids=" + taskids + "]";
	}

	
	
}
