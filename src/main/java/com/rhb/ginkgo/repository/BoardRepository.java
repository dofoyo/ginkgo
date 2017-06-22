package com.rhb.ginkgo.repository;

import java.util.List;

import com.rhb.ginkgo.repository.entity.BoardEntity;
import com.rhb.ginkgo.repository.entity.StageEntity;
import com.rhb.ginkgo.repository.entity.TaskEntity;

public interface BoardRepository {
	public List<BoardEntity> getBoards();
	public BoardEntity getBoard(String id);
	public List<StageEntity> getStages(String boardId);
	public List<TaskEntity> getTasks(String stageId);
	public void updateTasks(String stageId, List<TaskEntity> tasks);
}
