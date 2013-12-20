package com.jitong.workflow.domain;

import java.util.List;
import java.util.Set;

public class FlowNode {
	private String id;//主键
	private String name;//节点名称，如领导审批
	private List<NodeInfo> nodeInfoList;//要展示的信息内容
	private String preNodeId; 
	private String nextNodeId;
	
	private String[] actionButtons;//html 文本
}
