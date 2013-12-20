package com.jitong.workflow.domain;

import java.sql.Blob;

import com.jitong.oa.domain.Item;

public class ItemBid {
	/*private String id;*/
	private String itemId;
	/*private Item item;*/
	
	private String bidItemName;
	private String applyDate;
	private String planAmount;
	private String refNumber;
	private String moneySource;
	private String itemSummary;
	private String planBidDate;
	private String agentPersonPhone;
	private Blob bidNegotiation;
	private String bidNegotiationExt;
	
	private String hostDept;
	private String hostDeptComments;
	private String responsiblePerson;
	
	private String approveStatus;
	private String approverId;
	private String approveRejectDate;

	private String bidDate;
	private String bidType;
	private Blob bidDoc;
	private String bidDocExt;
	private String zhaobiaoDeptAgentPerson;
	private String zhaobiaoDeptComments;
	private String zhaobiaoDeptCommentsHistory;
	private String zhaobiaoDeptResponsiblePerson;
	
	private String jjwDeptApproverId;
	private String jjwDeptComments;
	private String jjwDeptCommentsHistory;
	private String jjwDeptResponsiblePerson;
	
	private String zbdeptMeetingRecorderId;
	private String zbdeptMeetingRecorderName;
	
	
	private Blob bidWinInfo;
	private String bidWinInfoExt;
	private Blob contract;
	private String contractExt;
	
	private Blob itemAcceptInfo;
	private String itemAcceptInfoExt;
	private String payCompany;
	private String payDate;
	private String payAmount;

	/*public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}*/
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	
	public String getBidItemName() {
		return bidItemName;
	}
	public void setBidItemName(String bidItemName) {
		this.bidItemName = bidItemName;
	}
	public String getApplyDate() {
		return applyDate;
	}
	/*public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}*/
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getPlanAmount() {
		return planAmount;
	}
	public void setPlanAmount(String planAmount) {
		this.planAmount = planAmount;
	}
	public String getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}
	public String getMoneySource() {
		return moneySource;
	}
	public void setMoneySource(String moneySource) {
		this.moneySource = moneySource;
	}
	public String getItemSummary() {
		return itemSummary;
	}
	public void setItemSummary(String itemSummary) {
		this.itemSummary = itemSummary;
	}
	public String getPlanBidDate() {
		return planBidDate;
	}
	public void setPlanBidDate(String planBidDate) {
		this.planBidDate = planBidDate;
	}
	public String getAgentPersonPhone() {
		return agentPersonPhone;
	}
	public void setAgentPersonPhone(String agentPersonPhone) {
		this.agentPersonPhone = agentPersonPhone;
	}


	
	public Blob getBidNegotiation() {
		return bidNegotiation;
	}
	public void setBidNegotiation(Blob bidNegotiation) {
		this.bidNegotiation = bidNegotiation;
	}
	public Blob getBidDoc() {
		return bidDoc;
	}
	public void setBidDoc(Blob bidDoc) {
		this.bidDoc = bidDoc;
	}
	public String getHostDeptComments() {
		return hostDeptComments;
	}
	public void setHostDeptComments(String hostDeptComments) {
		this.hostDeptComments = hostDeptComments;
	}
	public String getResponsiblePerson() {
		return responsiblePerson;
	}
	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}
	public String getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public String getApproveRejectDate() {
		return approveRejectDate;
	}
	public void setApproveRejectDate(String approveRejectDate) {
		this.approveRejectDate = approveRejectDate;
	}
	public String getBidDate() {
		return bidDate;
	}
	public void setBidDate(String bidDate) {
		this.bidDate = bidDate;
	}
	public String getBidType() {
		return bidType;
	}
	public void setBidType(String bidType) {
		this.bidType = bidType;
	}
	public String getZhaobiaoDeptAgentPerson() {
		return zhaobiaoDeptAgentPerson;
	}
	public void setZhaobiaoDeptAgentPerson(String zhaobiaoDeptAgentPerson) {
		this.zhaobiaoDeptAgentPerson = zhaobiaoDeptAgentPerson;
	}
	public String getZhaobiaoDeptComments() {
		return zhaobiaoDeptComments;
	}
	public void setZhaobiaoDeptComments(String zhaobiaoDeptComments) {
		this.zhaobiaoDeptComments = zhaobiaoDeptComments;
	}
	public String getZhaobiaoDeptResponsiblePerson() {
		return zhaobiaoDeptResponsiblePerson;
	}
	public void setZhaobiaoDeptResponsiblePerson(String zhaobiaoDeptResponsiblePerson) {
		this.zhaobiaoDeptResponsiblePerson = zhaobiaoDeptResponsiblePerson;
	}
	public String getJjwDeptApproverId() {
		return jjwDeptApproverId;
	}
	public void setJjwDeptApproverId(String jjwDeptApproverId) {
		this.jjwDeptApproverId = jjwDeptApproverId;
	}
	public String getJjwDeptComments() {
		return jjwDeptComments;
	}
	public void setJjwDeptComments(String jjwDeptComments) {
		this.jjwDeptComments = jjwDeptComments;
	}
	public String getJjwDeptResponsiblePerson() {
		return jjwDeptResponsiblePerson;
	}
	public void setJjwDeptResponsiblePerson(String jjwDeptResponsiblePerson) {
		this.jjwDeptResponsiblePerson = jjwDeptResponsiblePerson;
	}
	public String getHostDept() {
		return hostDept;
	}
	public void setHostDept(String hostDept) {
		this.hostDept = hostDept;
	}
	public String getBidNegotiationExt() {
		return bidNegotiationExt;
	}
	public void setBidNegotiationExt(String bidNegotiationExt) {
		this.bidNegotiationExt = bidNegotiationExt;
	}
	public String getBidDocExt() {
		return bidDocExt;
	}
	public void setBidDocExt(String bidDocExt) {
		this.bidDocExt = bidDocExt;
	}
	public Blob getBidWinInfo() {
		return bidWinInfo;
	}
	public void setBidWinInfo(Blob bidWinInfo) {
		this.bidWinInfo = bidWinInfo;
	}
	public Blob getContract() {
		return contract;
	}
	public void setContract(Blob contract) {
		this.contract = contract;
	}
	public String getBidWinInfoExt() {
		return bidWinInfoExt;
	}
	public void setBidWinInfoExt(String bidWinInfoExt) {
		this.bidWinInfoExt = bidWinInfoExt;
	}
	public String getContractExt() {
		return contractExt;
	}
	public void setContractExt(String contractExt) {
		this.contractExt = contractExt;
	}

	public Blob getItemAcceptInfo() {
		return itemAcceptInfo;
	}
	public void setItemAcceptInfo(Blob itemAcceptInfo) {
		this.itemAcceptInfo = itemAcceptInfo;
	}
	public String getItemAcceptInfoExt() {
		return itemAcceptInfoExt;
	}
	public void setItemAcceptInfoExt(String itemAcceptInfoExt) {
		this.itemAcceptInfoExt = itemAcceptInfoExt;
	}
	public String getPayCompany() {
		return payCompany;
	}
	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getZhaobiaoDeptCommentsHistory() {
		return zhaobiaoDeptCommentsHistory;
	}
	public void setZhaobiaoDeptCommentsHistory(String zhaobiaoDeptCommentsHistory) {
		this.zhaobiaoDeptCommentsHistory = zhaobiaoDeptCommentsHistory;
	}
	public String getJjwDeptCommentsHistory() {
		return jjwDeptCommentsHistory;
	}
	public void setJjwDeptCommentsHistory(String jjwDeptCommentsHistory) {
		this.jjwDeptCommentsHistory = jjwDeptCommentsHistory;
	}
	public String getZbdeptMeetingRecorderId() {
		return zbdeptMeetingRecorderId;
	}
	public void setZbdeptMeetingRecorderId(String zbdeptMeetingRecorderId) {
		this.zbdeptMeetingRecorderId = zbdeptMeetingRecorderId;
	}
	public String getZbdeptMeetingRecorderName() {
		return zbdeptMeetingRecorderName;
	}
	public void setZbdeptMeetingRecorderName(String zbdeptMeetingRecorderName) {
		this.zbdeptMeetingRecorderName = zbdeptMeetingRecorderName;
	}

}
