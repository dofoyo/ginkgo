package com.rhb.ginkgo.api.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectDTO {
	private String projectid;
	private String projectname;
	private String description;
	private Integer orderNo;
	private List<TaskDTO> tasks = new ArrayList<TaskDTO>();
	
	public ProjectDTO(){
		
	}
	
	public ProjectDTO(String projectid, String projectname, String description,Integer orderNo){
		super();
		this.projectid = projectid;
		this.projectname = projectname;
		this.description = description;
		this.orderNo = orderNo;
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

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public List<TaskDTO> getTasks() {
		return tasks;
	}

	public void addTask(TaskDTO taskDTO) {
		tasks.add(taskDTO);
	}

	@Override
	public String toString() {
		return "ProjectDTO [projectid=" + projectid + ", projectname=" + projectname + ", description=" + description
				+ ", orderNo=" + orderNo + ", tasks=" + tasks + "]";
	}
	
}
