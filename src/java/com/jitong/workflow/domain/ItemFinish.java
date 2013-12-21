package com.jitong.workflow.domain;

import com.jitong.oa.domain.Item;

public class ItemFinish {
	public static String STATUS_11_ON_FINISH_PENDING_LEAD_APPROVE="11_ON_FINISH_PENDING_LEAD_APPROVE";//待领导审批
	public static String STATUS_11_ON_FINISH_LEAD_APPROVED="11_ON_FINISH_LEAD_APPROVED";//领导审批通过
	public static String STATUS_11_ON_FINISH_LEAD_REJECT="11_ON_FINISH_LEAD_REJECT";//领导审批不通过
	public static String STATUS_11_ON_FINISH_PENDING_JJW_APPROVE="11_ON_FINISH_PENDING_JJW_APPROVE";//待纪检委审批
	public static String STATUS_11_ON_FINISH_JJW_REJECT="11_ON_FINISH_JJW_REJECT";//纪检委审批不通过
	public static String STATUS_11_ON_FINISH_JJW_APPROVED="11_ON_FINISH_JJW_APPROVED";//纪检委审批通过
	public static String STATUS_11_PAID="11_PAID";//已付款
	
	private String id;
	private Item item;
	private String finishItemName;//结项名称
	private String investAmount;//投资金额
	private String biaodiAmount;//标底
	private String zhongbiaoPrice;//中标价;
	private String zhongbiaoCompany;//中标单位
	private String jingbiaoDate;//竞标时间
	private String bidParticipants;//参加招标人员
	private String finishDate;//完成工期
	private String contractSignDate;//合同签订日期
	private String sponsorDeptRespPers;//主办部门负责人
	private String jingbanPers;//经办人
	private String finishSummary;//结项主要内容
	private String leadComments;//主管领导意见
	private String jjwComments;//纪检委意见
	private String finishNote;//结项备注
	private String status;
	
	private String payCompany;
	private String payDate;
	private String payAmount;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getFinishItemName() {
		return finishItemName;
	}
	public void setFinishItemName(String finishItemName) {
		this.finishItemName = finishItemName;
	}
	public String getInvestAmount() {
		return investAmount;
	}
	public void setInvestAmount(String investAmount) {
		this.investAmount = investAmount;
	}
	public String getBiaodiAmount() {
		return biaodiAmount;
	}
	public void setBiaodiAmount(String biaodiAmount) {
		this.biaodiAmount = biaodiAmount;
	}
	public String getZhongbiaoPrice() {
		return zhongbiaoPrice;
	}
	public void setZhongbiaoPrice(String zhongbiaoPrice) {
		this.zhongbiaoPrice = zhongbiaoPrice;
	}
	public String getZhongbiaoCompany() {
		return zhongbiaoCompany;
	}
	public void setZhongbiaoCompany(String zhongbiaoCompany) {
		this.zhongbiaoCompany = zhongbiaoCompany;
	}
	public String getJingbiaoDate() {
		return jingbiaoDate;
	}
	public void setJingbiaoDate(String jingbiaoDate) {
		this.jingbiaoDate = jingbiaoDate;
	}
	public String getBidParticipants() {
		return bidParticipants;
	}
	public void setBidParticipants(String bidParticipants) {
		this.bidParticipants = bidParticipants;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getContractSignDate() {
		return contractSignDate;
	}
	public void setContractSignDate(String contractSignDate) {
		this.contractSignDate = contractSignDate;
	}
	public String getSponsorDeptRespPers() {
		return sponsorDeptRespPers;
	}
	public void setSponsorDeptRespPers(String sponsorDeptRespPers) {
		this.sponsorDeptRespPers = sponsorDeptRespPers;
	}
	public String getJingbanPers() {
		return jingbanPers;
	}
	public void setJingbanPers(String jingbanPers) {
		this.jingbanPers = jingbanPers;
	}
	public String getFinishSummary() {
		return finishSummary;
	}
	public void setFinishSummary(String finishSummary) {
		this.finishSummary = finishSummary;
	}
	public String getLeadComments() {
		return leadComments;
	}
	public void setLeadComments(String leadComments) {
		this.leadComments = leadComments;
	}
	public String getJjwComments() {
		return jjwComments;
	}
	public void setJjwComments(String jjwComments) {
		this.jjwComments = jjwComments;
	}
	public String getFinishNote() {
		return finishNote;
	}
	public void setFinishNote(String finishNote) {
		this.finishNote = finishNote;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	public String getStatusTXT(){
		if(STATUS_11_ON_FINISH_PENDING_LEAD_APPROVE.equals(status)){
			return "11.待领导审批";
		}else if(STATUS_11_ON_FINISH_LEAD_APPROVED.equals(status)){
			return "11.领导审批通过";
		}else if(STATUS_11_ON_FINISH_LEAD_REJECT.equals(status)){
			return "11.未通过领导审批";
		}else if(STATUS_11_ON_FINISH_PENDING_JJW_APPROVE.equals(status)){
			return "11.待纪检委审批";
		}else if(STATUS_11_ON_FINISH_JJW_REJECT.equals(status)){
			return "11.未通过纪检委审批";
		}else if(STATUS_11_ON_FINISH_JJW_APPROVED.equals(status)){
			return "11.通过纪检委审批";
		}else if(STATUS_11_PAID.equals(status)){
			return "11.已付款";
		}else{
			return status;
		}
	}
}
