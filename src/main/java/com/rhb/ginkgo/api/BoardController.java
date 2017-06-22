package com.rhb.ginkgo.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.rhb.ginkgo.api.dto.BoardDTO;

public interface BoardController {
	public BoardDTO getBoard(@RequestParam(value="id", defaultValue="1") String boardid);
	public void updateStage(@RequestParam(value="id") String stageid, @RequestBody String body);
}
