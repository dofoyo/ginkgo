package com.rhb.ginkgo.api.dto;

import java.util.ArrayList;
import java.util.List;

public class StageDTO {
	private String stageid;
	private String stagename;
	private Integer orderNo;
	private List<TaskDTO> tasks;
	
	public StageDTO(){
		
	}
	
	public StageDTO(String stageid, String stagename,Integer orderNo){
		super();
		this.stageid = stageid;
		this.stagename = stagename;
		this.orderNo = orderNo;
		tasks = new ArrayList<TaskDTO>();
	}

	public String getStageid() {
		return stageid;
	}

	public void setStageid(String stageid) {
		this.stageid = stageid;
	}

	public String getStagename() {
		return stagename;
	}

	public void setStagename(String stagename) {
		this.stagename = stagename;
	}

	public List<TaskDTO> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskDTO> tasks) {
		this.tasks = tasks;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "StageDTO [stageid=" + stageid + ", stagename=" + stagename + ", orderNo=" + orderNo + ", tasks=" + tasks
				+ "]";
	}
	
	
	
}
