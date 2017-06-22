package com.rhb.ginkgo.repository.entity;

public class StageEntity {
	private String boardid;
	private String stageid;
	private String stagename;
	private Integer orderNo;
	
	
	public StageEntity(){
		
	}
	
	public StageEntity(String boardid,String stageid, String stagename,Integer orderNo){
		super();
		this.boardid = boardid;
		this.stageid = stageid;
		this.stagename = stagename;
		this.orderNo = orderNo;
		
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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

	public String getBoardid() {
		return boardid;
	}

	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}

	@Override
	public String toString() {
		return "StageEntity [boardid=" + boardid + ", stageid=" + stageid + ", stagename=" + stagename + ", orderNo="
				+ orderNo + "]";
	}
	
	
	
}
