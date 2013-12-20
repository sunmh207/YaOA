package com.jitong.workflow.domain;


/**
 * 项目审批，和报告审批
 * @author stanley.sun
 *
 */
public class ItemApprove {
	public static final String TYPE_ITEM_APPROVE="ITEM_APPROVE";
	public static final String TYPE_MKT_SIGN="MKT_SIGN";
	public static final String TYPE_MKT_CONFIRM="MKT_CONFIRM";
	
	public static final String TYPE_RPT_APPROVE="RPT_APPROVE";
	public static final String TYPE_SETUP_APPROVE="SETUP_APPROVE";
	public static final String TYPE_ZBDEPT_APPROVE="ZBDEPT_APPROVE";
	public static final String TYPE_JJW_APPROVE="JJW_APPROVE";
	
	public static final String TYPE_ONFINISH_LEAD_APPROVE="ONFINISH_LEAD_APPROVE";
	public static final String TYPE_ONFINISH_JJW_APPROVE="ONFINISH_JJW_APPROVE";
	
	public static final String STATUS_PENDING="PENDING";
	public static final String STATUS_APPROVED="APPROVED";
	public static final String STATUS_REJECTED="REJECTED";
	
	private String itemId;
	private String id;
	private String approverId;
	private String approverName;
	private String status;
	private String operationTime;
	private String comments;
	private String type;
	

	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public String getStatus() {
		return status;
	}
	public String getStatusTXT() {
		if(STATUS_PENDING.equals(status)){
			return "待审批";
		}else if(STATUS_APPROVED.equals(status)){
			return "审批通过";
		}else if(STATUS_REJECTED.equals(status)){
			return "审批未通过";
		}else{
			return status;
		}
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getApproverName() {
		return approverName;
	}
	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	

}
