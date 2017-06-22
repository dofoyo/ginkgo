package com.rhb.ginkgo.api.dto;

public class TaskDTO {
	private String taskid;
	private String taskname;
	private String description;
	private Integer orderNo;
	
	public TaskDTO(){
		
	}
	
	public TaskDTO(String taskid, String taskname, String description,Integer orderNo){
		super();
		this.taskid = taskid;
		this.taskname = taskname;
		this.description = description;
		this.orderNo = orderNo;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
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

	@Override
	public String toString() {
		return "TaskDTO [taskid=" + taskid + ", taskname=" + taskname + ", description=" + description + ", orderNo="
				+ orderNo + "]";
	}


	
}
