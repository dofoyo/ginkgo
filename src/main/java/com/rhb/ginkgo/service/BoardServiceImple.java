package com.rhb.ginkgo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rhb.ginkgo.api.dto.BoardDTO;
import com.rhb.ginkgo.api.dto.StageDTO;
import com.rhb.ginkgo.api.dto.TaskDTO;
import com.rhb.ginkgo.domain.Board;
import com.rhb.ginkgo.repository.BoardRepository;
import com.rhb.ginkgo.repository.BoardRepositoryImpl;
import com.rhb.ginkgo.repository.entity.BoardEntity;
import com.rhb.ginkgo.repository.entity.StageEntity;
import com.rhb.ginkgo.repository.entity.TaskEntity;

@Service("BoardServiceImple")
public class BoardServiceImple implements BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	/*
	@Autowired 
	public BoardServiceImple(BoardRepository boardRepository){
		this.boardRepository = boardRepository;
	}
	*/
	
	@Override
	public Map<String, String> getBoards() {
		List<BoardEntity> boards = boardRepository.getBoards();
		Map<String,String> bs = new HashMap<String,String>();
		for(BoardEntity b : boards){
			bs.put(b.getBoardid(), b.getBoardname());
		}
		return bs;
	}

	@Override
	public BoardDTO getBoard(String boardid) {
		
		BoardEntity be = boardRepository.getBoard(boardid);
		BoardDTO board = new BoardDTO(be.getBoardid(),be.getBoardname());
		
		List<StageEntity> stages = boardRepository.getStages(boardid);
		
		List<TaskEntity> tasks;
		
		for(StageEntity se : stages){
			StageDTO stage = new StageDTO(se.getStageid(),se.getStagename(),se.getOrderNo());
			tasks = boardRepository.getTasks(se.getStageid());
			for(TaskEntity te : tasks){
				TaskDTO task = new TaskDTO(te.getTaskid(),te.getTaskname(),te.getDescription(),te.getOrderNo());
				stage.getTasks().add(task);
			}
			board.getStages().add(stage);
		}
		
		return board;
	}

	@Override
	public void updateStage(String stageid, List<TaskDTO> tasks) {
		List<TaskEntity> tasklist = new ArrayList<TaskEntity>();
		for(TaskDTO td : tasks){
			TaskEntity task = new TaskEntity(stageid,td.getTaskid(),td.getTaskname(),td.getDescription(),td.getOrderNo());
			tasklist.add(task);
		}
		boardRepository.updateTasks(stageid, tasklist);
	}

}
