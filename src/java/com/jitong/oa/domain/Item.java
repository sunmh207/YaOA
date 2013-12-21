package com.jitong.oa.domain;

import java.sql.Blob;

/**
 * 项目
 * @author stanley.sun
 *
 */
public class Item {
	public static String STATUS_1_NEW="1_NEW";//项目申请
	public static String STATUS_2_PENDING_APPROVAL="2_PENDING_APPROVAL";//提交，待审批
	public static String STATUS_3_REJECT="3_REJECT";//审批未通过
	//public static String STATUS_4_PENDING_SURVEY="4_PENDING_SURVEY";//审批通过
	
	//发起人(通知共同参与调研的人)->参与调研的人员(签收调研通知)->发起人(发调研报告)->参与调研人员(确认,填写意见)->领导审批（参与调研的人员签收后）
	public static String STATUS_5_PENDING_INFO="5_PENDING_INFO";//待发调研通知
	public static String STATUS_5_PENDING_MKT_SIGN="5_PENDING_MKT_SIGN";//待市场部签收
	public static String STATUS_5_MKT_SIGNED="5_MKT_SIGNED";//市场部已签收
	public static String STATUS_5_PENDING_MKT_CONFIRM="5_PENDING_MKT_CONFIRM";//发起人提交报告后等待市场部确认
	public static String STATUS_5_MKT_CONFIRMED="5_MKT_CONFIRMED";//市场部已确认
	
	public static String STATUS_6_MKT_RPT_PENDING_APPROVE="6_MKT_RPT_PENDING_APPROVE";//市场调研提交后，待审批
	public static String STATUS_6_MKT_RPT_REJECT="6_MKT_RPT_REJECT";//市场调研提交后，审批未通过

	
	//<!--以下内容暂时不需要-->
	//需要调研：市场调研审批通过后-->活动立项阶段；不需要调研：STATUS_4_PENDING_SURVEY后，发起人点击‘开始立项’进入立项阶段
	public static String STATUS_7_SETUP="7_SETUP";//活动立项
	public static String STATUS_7_SETUP_PENDING_APPROVE="7_SETUP_PENDING_APPROVE";//活动立项待审批
	//public static String STATUS_7_SETUP_APPROVED="7_SETUP_APPROVED=";//审批通过-->STATUS_9_ON_BID
	public static String STATUS_7_SETUP_REJECT="7_SETUP_REJECT";//审批未通过
	
	
	//招标流程中，发起人->招标管理部门->发起人->纪委审批->发起人->招标管理部门->开标会（添写标前记录，参与招标的公司）->添写标后记录(中标单位，中标价等)。
	
	//发起人看到的状态
	public static String STATUS_9_ON_BID="9_ON_BID";//审批通过，进入招标程序
	public static String STATUS_9_ON_BID_PENDING_ZBDEPT_APPROVE="9_ON_BID_PENDING_ZBDEPT_APPROVE";//招标文件提交给招标管理部门审核
	public static String STATUS_9_ON_BID_ZBDEPT_APPROVED="9_ON_BID_ZBDEPT_APPROVED";//招标管理部门审核通过
	public static String STATUS_9_ON_BID_ZBDEPT_REJECT="9_ON_BID_ZBDEPT_REJECT";//招标管理部门审核不通过
	
	
	public static String STATUS_9_ON_BID_PENDING_JJW_APPROVE="9_ON_BID_PENDING_JJW_APPROVE";//纪检委审核
	public static String STATUS_9_ON_BID_JJW_REJECT="9_ON_BID_JJW_REJECT";//纪检委审核不通过
	public static String STATUS_9_ON_BID_JJW_APPROVED="9_ON_BID_JJW_APPROVED";//纪检委审核通过
	
	public static String STATUS_9_ON_BID_PENDING_ZBDEPT_MEETINGNOTE="9_ON_BID_PENDING_ZBDEPT_MEETINGNOTE";//待招标管理部门输入开标会记录
	
	
	public static String STATUS_10_ON_ACCEPT="10_ON_ACCEPT";//验收中
	
	//发起人（填写结项表）->主管领导审批 ->（同意）->发起人>纪委审批->发起人-才能进行结项。
	public static String STATUS_11_ON_FINISH="11_ON_FINISH";//结项中
	/*public static String STATUS_11_ON_FINISH_PENDING_LEAD_APPROVE="11_ON_FINISH_PENDING_LEAD_APPROVE";//待领导审批
	public static String STATUS_11_ON_FINISH_LEAD_APPROVED="11_ON_FINISH_LEAD_APPROVED";//领导审批通过
	public static String STATUS_11_ON_FINISH_LEAD_REJECT="11_ON_FINISH_LEAD_REJECT";//领导审批不通过
	public static String STATUS_11_ON_FINISH_PENDING_JJW_APPROVE="11_ON_FINISH_PENDING_JJW_APPROVE";//待纪检委审批
	public static String STATUS_11_ON_FINISH_JJW_REJECT="11_ON_FINISH_JJW_REJECT";//纪检委审批不通过
*/	
	public static String STATUS_12_ON_PAY="12_ON_PAY";//纪检委审批通过，进入‘付款中’
	public static String STATUS_13_CLOSE="13_CLOSE";//项目结束
	
	
	private String id;
	private String itemName;
	private String itemType;
	
	private String description;
	
	private String requesterId;
	private String requesterName;
	private String applyDate;//申请日期
	private String status;

	//Survey Info
	private String surveyInfoTitle;//调研通知title
	private String surveyInfoDate;
	/*private String surveyInfoReceiverNames;
	private String surveyInfoReceiverIds;*/
	private String surveyInfoBody;
	
	private String surveyReportDate;
	private String surveyReportSummary;//摘要
	private Blob surveyReportBody;
	private String surveyReportBodyExt;//调研报告的扩展名
	private String surveyReportReporterId;
	private String surveyReportReporterName;
	
	//---------结项内容-------------------
	/*private String finishItemName;//结项名称
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
*/	
	/*private ItemBid bid;*/
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getRequesterId() {
		return requesterId;
	}
	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}
	public String getRequesterName() {
		return requesterName;
	}
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusTXT() {
		return status2Txt(status);
	}
	
	public String getSurveyInfoTitle() {
		return surveyInfoTitle;
	}
	public void setSurveyInfoTitle(String surveyInfoTitle) {
		this.surveyInfoTitle = surveyInfoTitle;
	}
	public String getSurveyInfoDate() {
		return surveyInfoDate;
	}
	public void setSurveyInfoDate(String surveyInfoDate) {
		this.surveyInfoDate = surveyInfoDate;
	}
	/*public String getSurveyInfoReceiverNames() {
		return surveyInfoReceiverNames;
	}
	public void setSurveyInfoReceiverNames(String surveyInfoReceiverNames) {
		this.surveyInfoReceiverNames = surveyInfoReceiverNames;
	}
	*/
	


	public String getSurveyReportSummary() {
		return surveyReportSummary;
	}
	public void setSurveyReportSummary(String surveyReportSummary) {
		this.surveyReportSummary = surveyReportSummary;
	}
	public String getSurveyInfoBody() {
		return surveyInfoBody;
	}
	public void setSurveyInfoBody(String surveyInfoBody) {
		this.surveyInfoBody = surveyInfoBody;
	}
	public String getSurveyReportDate() {
		return surveyReportDate;
	}
	public void setSurveyReportDate(String surveyReportDate) {
		this.surveyReportDate = surveyReportDate;
	}


	public Blob getSurveyReportBody() {
		return surveyReportBody;
	}
	public void setSurveyReportBody(Blob surveyReportBody) {
		this.surveyReportBody = surveyReportBody;
	}
	public String getSurveyReportReporterId() {
		return surveyReportReporterId;
	}
	public void setSurveyReportReporterId(String surveyReportReporterId) {
		this.surveyReportReporterId = surveyReportReporterId;
	}
	
	/*public String getSurveyInfoReceiverIds() {
		return surveyInfoReceiverIds;
	}
	public void setSurveyInfoReceiverIds(String surveyInfoReceiverIds) {
		this.surveyInfoReceiverIds = surveyInfoReceiverIds;
	}*/
	public String getSurveyReportReporterName() {
		return surveyReportReporterName;
	}
	public void setSurveyReportReporterName(String surveyReportReporterName) {
		this.surveyReportReporterName = surveyReportReporterName;
	}
	
	
	public String getSurveyReportBodyExt() {
		return surveyReportBodyExt;
	}
	public void setSurveyReportBodyExt(String surveyReportBodyExt) {
		this.surveyReportBodyExt = surveyReportBodyExt;
	}


	/*public String getFinishItemName() {
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
	}*/
	public static String status2Txt(String st){
		if(STATUS_1_NEW.equals(st)){
			return "1.新申请";
		}else if(STATUS_2_PENDING_APPROVAL.equals(st)){
			return "2.待审批";
		}else if(STATUS_3_REJECT.equals(st)){
			return "3.审批未通过";
		/*}else if(STATUS_4_PENDING_SURVEY.equals(st)){
			return "4.审批通过";*/
		}else if(STATUS_5_PENDING_INFO.equals(st)){
			return "5.等发调研通知";
		}else if(STATUS_5_PENDING_MKT_SIGN.equals(st)){
			return "5.调研通知待签收";
		}else if(STATUS_5_MKT_SIGNED.equals(st)){
			return "5.调研通知已签收";
		}else if(STATUS_5_PENDING_MKT_CONFIRM.equals(st)){
			return "5.调研报告待确认";
		}else if(STATUS_5_MKT_CONFIRMED.equals(st)){
			return "5.调研报告已确认";
		}		else if(STATUS_6_MKT_RPT_PENDING_APPROVE.equals(st)){
			return "6.已提交调研报告，待审批";
		}else if(STATUS_6_MKT_RPT_REJECT.equals(st)){
			return "6.调研报告未通过审批";
		}else if(STATUS_7_SETUP.equals(st)){
			return "7.活动立项";
		}else if(STATUS_7_SETUP_PENDING_APPROVE.equals(st)){
			return "7.1活动立项等待审批";
		}else if(STATUS_7_SETUP_REJECT.equals(st)){
			return "7.2活动立项未通过审批";
		}else if(STATUS_9_ON_BID.equals(st)){
				return "9.1进入招标程序";
		}else if(STATUS_9_ON_BID_PENDING_ZBDEPT_APPROVE.equals(st)){
			return "9.2等待招标管理部门审核";
		}else if(STATUS_9_ON_BID_ZBDEPT_APPROVED.equals(st)){
			return "9.3已通过招标管理部门审核";
		}else if(STATUS_9_ON_BID_ZBDEPT_REJECT.equals(st)){
			return "9.4未通过招标管理部门审核";
		}else if(STATUS_9_ON_BID_PENDING_JJW_APPROVE.equals(st)){
			return "9.5待纪检委审核";
		}else if(STATUS_9_ON_BID_JJW_REJECT.equals(st)){
			return "9.6未通过纪检委审核";
		}else if(STATUS_9_ON_BID_JJW_APPROVED.equals(st)){
			return "9.7已通过纪检委审核";
		}else if(STATUS_9_ON_BID_PENDING_ZBDEPT_MEETINGNOTE.equals(st)){
			return "9.8待招标部门录入开标会记录";
		}else if(STATUS_10_ON_ACCEPT.equals(st)){
			return "10.验收中";
		}else if(STATUS_11_ON_FINISH.equals(st)){
			return "11.结项中";
		/*}else if(STATUS_11_ON_FINISH_PENDING_LEAD_APPROVE.equals(st)){
			return "11.待领导审批";
		}else if(STATUS_11_ON_FINISH_LEAD_APPROVED.equals(st)){
			return "11.领导审批通过";
		}else if(STATUS_11_ON_FINISH_LEAD_REJECT.equals(st)){
			return "11.未通过领导审批";
		}else if(STATUS_11_ON_FINISH_PENDING_JJW_APPROVE.equals(st)){
			return "11.待纪检委审批";
		}else if(STATUS_11_ON_FINISH_JJW_REJECT.equals(st)){
			return "11.未通过纪检委审批";*/
		}else if(STATUS_12_ON_PAY.equals(st)){
			return "11.付款中";
		}else if(STATUS_13_CLOSE.equals(st)){
			return "12.项目结束";
		}
		return st;
		
	}
	
	
}
