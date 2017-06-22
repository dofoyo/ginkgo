package com.rhb.ginkgo.domain;

import java.util.LinkedList;
import java.util.Map;

public class Board {
	private String boardId;
	private String boardName;
	private Map<Integer,Stage> stages; //按map的key进行排序，确定后一般不会改变，只有该看板的管理员可以重新排序
	private LinkedList logs;  //日志，记录看板的全过程，什么时间谁创建了这个看板，什么时间谁创建/修改/删除了stage,什么时间谁创建/修改/删除了任务，什么时间谁发表了评论
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public Map<Integer, Stage> getStages() {
		return stages;
	}
	public void setStages(Map<Integer, Stage> stages) {
		this.stages = stages;
	}
	public LinkedList getLogs() {
		return logs;
	}
	public void setLogs(LinkedList logs) {
		this.logs = logs;
	}
 
	
	
}
