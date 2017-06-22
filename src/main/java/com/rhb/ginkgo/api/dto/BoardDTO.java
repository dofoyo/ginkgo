package com.rhb.ginkgo.api.dto;

import java.util.ArrayList;
import java.util.List;

public class BoardDTO {
	private String boardid;
	private String boardname;
	private List<StageDTO> stages;
	
	public BoardDTO(){
		
	}
	
	public BoardDTO(String boardid, String boardname){
		super();
		this.boardid = boardid;
		this.boardname = boardname;
		stages = new ArrayList<StageDTO>();
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

	public List<StageDTO> getStages() {
		return stages;
	}

	public void setStages(List<StageDTO> stages) {
		this.stages = stages;
	}

	@Override
	public String toString() {
		return "BoardDTO [boardid=" + boardid + ", boardname=" + boardname + ", stages=" + stages + "]";
	}
	
	
}
