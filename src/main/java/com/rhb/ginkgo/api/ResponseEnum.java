package com.rhb.ginkgo.api;

public enum ResponseEnum {
	ERROR("1","ERROR"),
	SUCCESS("100","OK");
	
	private String msg;
	private String code;
	private ResponseEnum(String msg, String code) {
		this.msg = msg;
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public String getCode() {
		return code;
	}
	
	
}
