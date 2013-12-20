package com.jitong.oa.domain;

public class ItemOperator {
	public static String TYPE_REQUESTER="requester";
	public static String TYPE_LEADER1="leader1";
	public static String TYPE_MARKETING="marketing";
	public static String TYPE_LEADER2="leader2";
	
	public static String ISDONE_NO="0";
	public static String ISDONE_YES="1";

	public static String APPROVAL_PENDING="PENDING";
	public static String APPROVAL_NO="NO";
	public static String APPROVAL_YES="YES";
	
	private String id;
	private String itemId;
	private String itemStatus;
	private String type;//requester,lead,marketing
	private String operatorId;
	private String operatorName;
	private String operatorDept;
	private String isDone;
	private String doneTime;
	private String approval;//0 no, 1 yes.
	private String approvalMsg;
	private String createTime;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperatorDept() {
		return operatorDept;
	}
	public void setOperatorDept(String operatorDept) {
		this.operatorDept = operatorDept;
	}
	public String getIsDone() {
		return isDone;
	}
	public void setIsDone(String isDone) {
		this.isDone = isDone;
	}
	public String getDoneTime() {
		return doneTime;
	}
	public void setDoneTime(String doneTime) {
		this.doneTime = doneTime;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String getApprovalMsg() {
		return approvalMsg;
	}
	public void setApprovalMsg(String approvalMsg) {
		this.approvalMsg = approvalMsg;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
}
