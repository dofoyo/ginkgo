package com.rhb.ginkgo.repository;

import java.util.List;

import com.rhb.ginkgo.repository.entity.BoardEntity;
import com.rhb.ginkgo.repository.entity.StageEntity;
import com.rhb.ginkgo.repository.entity.ProjectEntity;

public interface BoardRepository {
	public List<BoardEntity> getBoards();
	public BoardEntity getBoard(String id);
	public List<StageEntity> getStages(String boardId);
	public List<ProjectEntity> getProjects(String stageId);
	
	public ProjectEntity getProject(String projectid);
	
	public void updateProjectStageidAndOrder(String stageId, List<ProjectEntity> projects);
	public void updateProjectidAndTaskid(String projectid, String taskid);
	public void removeTaskidFromProject(String projectid, String taskid);
	public void deleteProject(String projectid);
	public void saveProject(ProjectEntity projectEntity);
	
}
