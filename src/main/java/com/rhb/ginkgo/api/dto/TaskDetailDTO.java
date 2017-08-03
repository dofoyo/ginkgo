package com.rhb.ginkgo.api.dto;

import java.sql.Timestamp;

import com.rhb.ginkgo.util.Convert;

public class TaskDetailDTO {
	private Timestamp dateAndTime;
	private String person;
	private String done;
	
	
	
	public TaskDetailDTO() {
	}
	public String getDateAndTime() {
		return Convert.t2s(dateAndTime, "MM-dd hh:mm:ss");
	}
	
	public Timestamp getDateTime(){
		return this.dateAndTime;
	}
	
	public void setDateAndTime(Timestamp dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getDone() {
		return done;
	}
	public void setDone(String done) {
		this.done = done;
	}
	@Override
	public String toString() {
		return "TaskDetailDTO [dateAndTime=" + dateAndTime + ", person=" + person + ", done=" + done + "]";
	}
	
	
}
