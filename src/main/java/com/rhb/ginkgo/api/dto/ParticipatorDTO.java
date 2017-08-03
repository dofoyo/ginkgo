package com.rhb.ginkgo.api.dto;

public class ParticipatorDTO {
	private String id;
	private String fullname;
	private String role;
	
	public ParticipatorDTO(String id, String fullname, String role) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
