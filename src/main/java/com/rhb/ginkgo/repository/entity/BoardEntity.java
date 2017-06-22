package com.rhb.ginkgo.repository.entity;

public class BoardEntity {
	private String boardid;
	private String boardname;
	
	
	public BoardEntity(){
	}
	
	public BoardEntity(String boardid, String boardname){
		super();
		this.boardid = boardid;
		this.boardname = boardname;
	}

	public String getBoardid() {
		return boardid;
	}

	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}

	public String getBoardname() {
		return boardname;
	}

	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}

	@Override
	public String toString() {
		return "BoardEntity [boardid=" + boardid + ", boardname=" + boardname + "]";
	}


}
