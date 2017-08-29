package com.rhb.ginkgo.api.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.rhb.ginkgo.repository.entity.StageEntity;

public class TaskDTO {
	private String taskid;
	private String subject;
	private List<TaskDetailDTO> taskDetail = new ArrayList<TaskDetailDTO>();
	private List<String> actors = new ArrayList<String>();
	private List<String> others = new ArrayList<String>();
	
	
	
	public TaskDTO() {
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public List<TaskDetailDTO> getTaskDetail() {
		Collections.sort(taskDetail, new Comparator<TaskDetailDTO>(){
			public int compare(TaskDetailDTO t1, TaskDetailDTO t2) {
				return t2.getDateTime().compareTo(t1.getDateTime()); //时间倒序
			}
			
		});
		
		return taskDetail;
	}
	public void addTaskDetail(TaskDetailDTO taskDetail) {
		this.taskDetail.add(taskDetail);
	}
	
	public void addActor(String personname){
		this.actors.add(personname);
	}

	public void addOther(String personname){
		this.others.add(personname);
	}

	
	
	
	public List<String> getActors() {
		return actors;
	}
	public void setActors(List<String> actors) {
		this.actors = actors;
	}
	public List<String> getOthers() {
		return others;
	}
	public void setOthers(List<String> others) {
		this.others = others;
	}
	public void setTaskDetail(List<TaskDetailDTO> taskDetail) {
		this.taskDetail = taskDetail;
	}
	
	
	
	
}
