package com.rhb.ginkgo.domain;

import java.security.Timestamp;
import java.util.List;

public class Task {
	private String id;
	private String title;
	private String description;
	private List<Comment> comments;  //按时间排序，顺序不能调整
	
}
