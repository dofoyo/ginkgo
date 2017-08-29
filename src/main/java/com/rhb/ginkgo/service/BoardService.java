package com.rhb.ginkgo.service;

import java.util.List;
import java.util.Map;

import com.rhb.ginkgo.api.dto.BoardDTO;
import com.rhb.ginkgo.api.dto.ProjectDTO;

public interface BoardService {
	public Map<String,String> getBoards();
	
	public BoardDTO getBoard(String id, boolean refresh);
	
	public void updateProjectStageidAndOrder(String stageid, List<ProjectDTO> projects);
	
	public void updateProjectType(String projectid);
	
	public ProjectDTO getProject(String projectid, boolean refresh);
	
	public void refreshProjects();
	public void refreshProjectsTaskids(String projectid);
	
}
