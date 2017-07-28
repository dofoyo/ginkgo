package com.rhb.ginkgo.api.dto;

import java.util.ArrayList;
import java.util.List;

public class StageDTO {
	private String stageid;
	private String stagename;
	private Integer orderNo;
	private List<ProjectDTO> projects;
	
	public StageDTO(){
		
	}
	
	public StageDTO(String stageid, String stagename,Integer orderNo){
		super();
		this.stageid = stageid;
		this.stagename = stagename;
		this.orderNo = orderNo;
		projects = new ArrayList<ProjectDTO>();
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

	public List<ProjectDTO> getProjects() {
		return projects;
	}

	public void setTasks(List<ProjectDTO> projects) {
		this.projects = projects;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "StageDTO [stageid=" + stageid + ", stagename=" + stagename + ", orderNo=" + orderNo + ", tasks=" + projects
				+ "]";
	}
	
	
	
}
