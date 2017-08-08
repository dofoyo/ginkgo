package com.rhb.ginkgo.api;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.rhb.ginkgo.api.dto.BoardDTO;
import com.rhb.ginkgo.api.dto.ProjectDTO;

public interface BoardController{
    public ResponseContent<BoardDTO> getBoard(@RequestParam(value="id", defaultValue="1") String boardid);
    public ResponseContent<BoardDTO> getBoardAfterRefreshed(@RequestParam(value="id", defaultValue="1") String boardid); 
    public void updateStage(@RequestParam(value="id") String stageid, @RequestBody String body);
    public ResponseContent<ProjectDTO> getProject(@RequestParam(value="id") String projectid);
    public ResponseContent<ProjectDTO> getProjectAfterRefreshed(@RequestParam(value="id") String projectid);
}
