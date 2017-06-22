package com.rhb.ginkgo.domain;

import java.util.Map;

public class Stage {
	private String id;
	private String stageName;
	private Map<Integer,Task> tasks; //因为顺序可以随意调整，

}
