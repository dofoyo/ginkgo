package com.rhb.ginkgo.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rhb.ginkgo.repository.entity.BoardEntity;
import com.rhb.ginkgo.repository.entity.StageEntity;
import com.rhb.ginkgo.repository.entity.TaskEntity;

@Repository("BoardRepositoryImpl")
public class BoardRepositoryImpl implements BoardRepository{
	private Map<String,BoardEntity> boards = new HashMap<String,BoardEntity>();
	private Map<String,StageEntity> stages = new HashMap<String,StageEntity>();
	private Map<String,TaskEntity> tasks = new HashMap<String,TaskEntity>();

	public BoardRepositoryImpl(){
		//System.out.println("*********** init BoardRepository ********");
		this.init();
	}

	@Override
	public List<BoardEntity> getBoards() {
		return new ArrayList<BoardEntity>(boards.values());
	}

	@Override
	public BoardEntity getBoard(String id) {
		return boards.get(id);
	}

	@Override
	public List<StageEntity> getStages(String boardId) {
		List<StageEntity> l = new ArrayList<StageEntity>();
		for(StageEntity se : stages.values()){
			if(se.getBoardid().equals(boardId)){
				l.add(se);
			}
		}
		
		Collections.sort(l, new Comparator<StageEntity>(){
			public int compare(StageEntity t1, StageEntity t2) {
				return t1.getOrderNo().compareTo(t2.getOrderNo());
			}
			
		});
		
		return l;
	}

	@Override
	public List<TaskEntity> getTasks(String stageId) {
		List<TaskEntity> l = new ArrayList<TaskEntity>();
		for(TaskEntity te : this.tasks.values()){
			if(te.getStageid().equals(stageId)){
				l.add(te);
			}
		}
		
		Collections.sort(l, new Comparator<TaskEntity>(){
			public int compare(TaskEntity t1, TaskEntity t2) {
				return t1.getOrderNo().compareTo(t2.getOrderNo());
			}
			
		});
		
		return l;
	}

	@Override
	public void updateTasks(String stageId, List<TaskEntity> tasklist) {
		for(TaskEntity te : tasklist){
			TaskEntity t = this.tasks.get(te.getTaskid());
			t.setOrderNo(te.getOrderNo());
			t.setStageid(stageId);
			//this.tasks.get(te.getTaskid()).setOrderNo(te.getOrderNo());
			
		}
		//System.out.println("...............updated tasks. ");
	}
	
	private void init(){
		System.out.println("do init............");
		this.boards.put("1",new BoardEntity("1","IT系统建设看板"));
		this.boards.put("2",new BoardEntity("2","TO DO LIST"));
		
		this.stages.put("0",new StageEntity("1","0","1.需求",0));
		this.stages.put("1",new StageEntity("1","1","2.方案",1));
		this.stages.put("2",new StageEntity("1","2","3.设计",2));
		this.stages.put("3",new StageEntity("1","3","4.开发与测试",3));
		this.stages.put("4",new StageEntity("1","4","5.应用",4));
		this.stages.put("5",new StageEntity("1","5","6.验收",5));
		
		this.stages.put("6",new StageEntity("2","6","todo",0));
		this.stages.put("7",new StageEntity("2","7","doing",1));
		this.stages.put("8",new StageEntity("2","8","done",2));
		
		this.tasks.put("0",new TaskEntity("0","0","dog","my best friend in the world",0));
		this.tasks.put("1",new TaskEntity("1","1","cat","it is black",1));
		this.tasks.put("2",new TaskEntity("1","2","hen","give me eggs every day",2));
		this.tasks.put("3",new TaskEntity("0","3","mouse","i hate it",3));
		this.tasks.put("4",new TaskEntity("1","4","bird","she can fly飞翔",4));
		this.tasks.put("5",new TaskEntity("2","5","eargle","please catch the mouse",5));
		this.tasks.put("6",new TaskEntity("0","6","tigger","it is so terrible",6));
		this.tasks.put("7",new TaskEntity("6","7","listen to radio","it is the sound of america",7));
		this.tasks.put("8",new TaskEntity("6","8","cooking","it is for my best friend",8));
		this.tasks.put("9",new TaskEntity("7","9","writing","i am going to be writer.",9));
		this.tasks.put("10",new TaskEntity("7","10","reading","there are so many books i loved",10));
		this.tasks.put("11",new TaskEntity("8","11","runing","i want to fly飞翔",11));
	}
	
}