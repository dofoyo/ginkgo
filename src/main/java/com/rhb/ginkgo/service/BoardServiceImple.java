package com.rhb.ginkgo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rhb.ginkgo.api.dto.BoardDTO;
import com.rhb.ginkgo.api.dto.StageDTO;
import com.rhb.ginkgo.api.dto.ProjectDTO;
import com.rhb.ginkgo.domain.Board;
import com.rhb.ginkgo.repository.BoardRepository;
import com.rhb.ginkgo.repository.BoardRepositoryImpl;
import com.rhb.ginkgo.repository.entity.BoardEntity;
import com.rhb.ginkgo.repository.entity.StageEntity;
import com.rhb.ginkgo.repository.entity.ProjectEntity;

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
		
		List<ProjectEntity> projects;
		
		for(StageEntity se : stages){
			StageDTO stage = new StageDTO(se.getStageid(),se.getStagename(),se.getOrderNo());
			projects = boardRepository.getProjects(se.getStageid());
			for(ProjectEntity te : projects){
				ProjectDTO project = new ProjectDTO(te.getProjectid(),te.getProjectname(),te.getDescription(),te.getOrderNo());
				stage.getProjects().add(project);
			}
			board.getStages().add(stage);
		}
		
		return board;
	}

	@Override
	public void updateStage(String stageid, List<ProjectDTO> projects) {
		List<ProjectEntity> projectlist = new ArrayList<ProjectEntity>();
		for(ProjectDTO td : projects){
			ProjectEntity project = new ProjectEntity(stageid,td.getProjectid(),td.getProjectname(),td.getDescription(),td.getOrderNo());
			projectlist.add(project);
		}
		boardRepository.updateProjects(stageid, projectlist);
	}

	@Override
	public void createProject(ProjectDTO projectDTO) {
		//String stageid,String projectid, String projectname, String description,Integer orderNo
		ProjectEntity projectEntity = new ProjectEntity("0",UUID.randomUUID().toString(),projectDTO.getProjectname(),projectDTO.getDescription(),0);
		boardRepository.saveProject(projectEntity);
	}

}
