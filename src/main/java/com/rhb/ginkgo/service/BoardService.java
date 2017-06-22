package com.rhb.ginkgo.service;

import java.util.List;
import java.util.Map;

import com.rhb.ginkgo.api.dto.BoardDTO;
import com.rhb.ginkgo.api.dto.TaskDTO;

public interface BoardService {
	public Map<String,String> getBoards();
	public BoardDTO getBoard(String id);
	
	public void updateStage(String stageid, List<TaskDTO> tasks);
}
