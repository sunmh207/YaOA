package com.jitong.workflow.domain;

import java.util.List;
import java.util.Set;

public class FlowNodeInstance{
	private FlowNode flowNode ;
	private String instanceId;
	private String instanceName;
	private String status;
	private String preNodeInstanceId; 
	private String nextNodeInstanceId;
	private Set<NodeAssignee> assingees;
}
