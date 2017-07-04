package com.rhb.ginkgo.api;

public class ResponseContent<T> {
	private String code;
	private String msg;
	private T content;
	
	public ResponseContent(String code, String msg, T content){
		this.code = code;
		this.msg = msg;
		this.content = content;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}


	public T getContent() {
		return content;
	}


	public void setContent(T content) {
		this.content = content;
	}


}
