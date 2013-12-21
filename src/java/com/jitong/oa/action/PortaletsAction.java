package com.jitong.oa.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.JTMath;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.ItemFinish;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemApprove;
import com.opensymphony.xwork2.Preparable;

public class PortaletsAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private String status_2_pending_approval="0";
	//private String status_4_pending_survey="0";
	private String status_5_on_survey="0";
	private String status_6_report_pending_approval="0";
	
	private String status_7_setup="0";//立项
	private String status_7_setup_pending_approve="0";//立项待审批
	
	private String status_9_on_bid="0";
	private String status_10_on_accept="0";//验收
	private String status_11_on_finish="0";//结项
	private String status_12_on_pay="0";//付款
	private String status_13_close="0";//结束
	
	/*private List<Item> newItemList;
	private List<TODOItem> todoList;*/
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
	}


	public String list() {
		User u = this.getLoginUser();
		if(u==null){
			this.addActionError("当前用户会话超时");
			return SUCCESS;
		}
		try {
			
			//String hql_2_pending_approval_sql = "select approve from ItemApprove approve, Item item where approve.itemId=item.id and approve.approverId='"+u.getId()+"' and item.status='"+Item.STATUS_2_PENDING_APPROVAL+"' ";
			String hql_2_pending_approval_sql = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
			" item.status in ('" + Item.STATUS_2_PENDING_APPROVAL + "') " +
			" and approve.approverId='" + u.getId() + "' " +
			" and approve.status in ('" + ItemApprove.STATUS_PENDING + "')" +
			" and approve.type='"+ ItemApprove.TYPE_ITEM_APPROVE + "'";
			
			List itemApproveList = service.queryByHql(hql_2_pending_approval_sql);
			if(itemApproveList!=null){
				status_2_pending_approval=String.valueOf(itemApproveList.size());
			}
			/*String status_4_pending_survey_sql = " from Item item where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_5_PENDING_INFO+"','"+Item.STATUS_5_PENDING_MKT_SIGN+"','"+Item.STATUS_5_MKT_SIGNED+"') ";
			List itemPendingSurveyList = service.queryByHql(status_4_pending_survey_sql);
			if(itemPendingSurveyList!=null){
				status_4_pending_survey=String.valueOf(itemPendingSurveyList.size());
			}*/
			String status_5_on_survey_seq = " from Item item where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_5_PENDING_INFO+"','"+Item.STATUS_5_MKT_SIGNED+"','"+Item.STATUS_5_MKT_CONFIRMED+"','"+Item.STATUS_6_MKT_RPT_REJECT+"') ";
			List itemOnSurveyList = service.queryByHql(status_5_on_survey_seq);
			if(itemOnSurveyList!=null){
				status_5_on_survey=String.valueOf(itemOnSurveyList.size());
			}
			String status_5_on_survey_seq2 = "select item from Item item, ItemApprove approve where item.id=approve.itemId and " +
			" item.status in ('" + Item.STATUS_5_PENDING_MKT_SIGN + "','"+Item.STATUS_5_PENDING_MKT_CONFIRM+"') " +
			" and approve.approverId='" + u.getId() + "' " +
			" and approve.status in ('" + ItemApprove.STATUS_PENDING+"')" +
			" and approve.type in('"+ ItemApprove.TYPE_MKT_SIGN + "','"+ItemApprove.TYPE_MKT_CONFIRM+"')";
			List itemOnSurveyList2 = service.queryByHql(status_5_on_survey_seq2);
			if(itemOnSurveyList2!=null){
				status_5_on_survey=JTMath.add(status_5_on_survey, String.valueOf(itemOnSurveyList2.size()));
			}
			
			
			//String status_6_report_pending_approval = " from Item item where item.surveyInfoReceiverIds like '%" + u.getId() + "%' and (item.status='" + Item.STATUS_5_ON_SURVEY + "') ";
			String status_6_report_pending_approval_seq = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and item.status='"+Item.STATUS_6_MKT_RPT_PENDING_APPROVE+"' and approve.approverId='"+u.getId()+"' and approve.status='"+ItemApprove.STATUS_PENDING+"' and approve.type='"+ItemApprove.TYPE_RPT_APPROVE+"'";
			List itemReportPendingList = service.queryByHql(status_6_report_pending_approval_seq);
			if(itemReportPendingList!=null){
				status_6_report_pending_approval=String.valueOf(itemReportPendingList.size());
			}

			String status_7_setup_seq = "from com.jitong.oa.domain.Item me where (me.status='"+Item.STATUS_7_SETUP+"' or  me.status='"+Item.STATUS_7_SETUP_REJECT+"') and me.requesterId='"+u.getId()+"'";
			List setupList = service.queryByHql(status_7_setup_seq);
			if(itemReportPendingList!=null){
				status_7_setup=String.valueOf(setupList.size());
			}
			String status_7_setup_pending_approve_seq = "select item from Item item, ItemApprove approve where item.id=approve.itemId and item.status='" + Item.STATUS_7_SETUP_PENDING_APPROVE
				+ "' and approve.approverId='" + u.getId() + "' and approve.status='" + ItemApprove.STATUS_PENDING + "' and approve.type='"
				+ ItemApprove.TYPE_SETUP_APPROVE + "'";
			List setup_pending_approve_List = service.queryByHql(status_7_setup_pending_approve_seq);
			if(itemReportPendingList!=null){
				status_7_setup_pending_approve=String.valueOf(setup_pending_approve_List.size());
			}
			 
			
			
			String status_9_on_bid_faqiren_seq = "select item from Item item where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_9_ON_BID+"','"+Item.STATUS_9_ON_BID_ZBDEPT_REJECT+"','"+Item.STATUS_9_ON_BID_JJW_REJECT+"','"+Item.STATUS_9_ON_BID_ZBDEPT_APPROVED+"','"+Item.STATUS_9_ON_BID_JJW_APPROVED+"')";
			String status_9_on_bid_pending_zbdept_approve_seq="select item from Item item, ItemApprove approve where item.id=approve.itemId and item.status in ('"+Item.STATUS_9_ON_BID_PENDING_ZBDEPT_APPROVE+"') "
			+ " and approve.approverId='" + u.getId() + "' and approve.status='" + ItemApprove.STATUS_PENDING + "' and approve.type='"+ ItemApprove.TYPE_ZBDEPT_APPROVE + "'";
			//String status_9_on_bid_pending_jjw_approve_seq="select item from Item item, ItemBid bid where item.id=bid.itemId and bid.jjwDeptApproverId='"+u.getId()+"' and item.status in('"+Item.STATUS_9_ON_BID_PENDING_JJW_APPROVE+"')";
			String status_9_on_bid_pending_jjw_approve_seq="select item from Item item, ItemApprove approve where item.id=approve.itemId and item.status in ('"+Item.STATUS_9_ON_BID_PENDING_JJW_APPROVE+"') "
			+ " and approve.approverId='" + u.getId() + "' and approve.status='" + ItemApprove.STATUS_PENDING + "' and approve.type='"+ ItemApprove.TYPE_JJW_APPROVE + "'";
			
			String status_9_on_bid_meetingnote_seq= "select item from Item item,ItemBid bid where item.id=bid.itemId and bid.zbdeptMeetingRecorderId like '%"+u.getId()+"%' and item.status in ('"+Item.STATUS_9_ON_BID_PENDING_ZBDEPT_MEETINGNOTE+"')";
			
			int status_9_on_bid_faqiren=0;
			int status_9_on_bid_pending_zbdept_approve=0;
			int status_9_on_bid_pending_jjw_approve=0;
			int status_9_on_bid_meetingnote=0;
			
			List onBid_faqirenList = service.queryByHql(status_9_on_bid_faqiren_seq);
			if(onBid_faqirenList!=null){
				status_9_on_bid_faqiren=onBid_faqirenList.size();
			}
			List onBid_pendingZBDeptApproveList = service.queryByHql(status_9_on_bid_pending_zbdept_approve_seq);
			if(onBid_pendingZBDeptApproveList!=null){
				status_9_on_bid_pending_zbdept_approve=onBid_pendingZBDeptApproveList.size();
			}
			List onBid_pendingJJWApproveList = service.queryByHql(status_9_on_bid_pending_jjw_approve_seq);
			if(onBid_pendingJJWApproveList!=null){
				status_9_on_bid_pending_jjw_approve=onBid_pendingJJWApproveList.size();
			}
			
			List onBid_meetingnoteList = service.queryByHql(status_9_on_bid_meetingnote_seq);
			if(onBid_meetingnoteList!=null){
				status_9_on_bid_meetingnote=onBid_meetingnoteList.size();
			}
			status_9_on_bid =String.valueOf(status_9_on_bid_faqiren+status_9_on_bid_pending_zbdept_approve+status_9_on_bid_pending_jjw_approve+status_9_on_bid_meetingnote);
			
			
			String status_10_on_accept_seq = " from Item item where item.requesterId = '" + u.getId() + "' and (item.status='" + Item.STATUS_10_ON_ACCEPT + "' or item.status='"+Item.STATUS_12_ON_PAY+"') ";
			List itemOnAcceptList = service.queryByHql(status_10_on_accept_seq);
			if(itemOnAcceptList!=null){
				status_10_on_accept=String.valueOf(itemOnAcceptList.size());
			}
			
			String status_11_on_finish_sponsor_hql = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_11_ON_FINISH+"')";
			List status_11_on_finish_sponsor_list = service.queryByHql(status_11_on_finish_sponsor_hql);
			if(status_11_on_finish_sponsor_list!=null){
				status_11_on_finish=String.valueOf(status_11_on_finish_sponsor_list.size());
			}
			
			String status_11_on_finish_lead_hql="select finish from ItemFinish finish, ItemApprove approve where finish.id=approve.itemId and finish.status in ('"+ItemFinish.STATUS_11_ON_FINISH_PENDING_JJW_APPROVE+"') "
			+ " and approve.approverId='" + u.getId() + "' and approve.status='" + ItemApprove.STATUS_PENDING + "' and approve.type='"+ ItemApprove.TYPE_ONFINISH_LEAD_APPROVE + "'";
			List status_11_on_finish_lead_list = service.queryByHql(status_11_on_finish_lead_hql);
			if(status_11_on_finish_lead_list!=null){
				status_11_on_finish+=String.valueOf(status_11_on_finish_lead_list.size());
			}
			
			String status_11_on_finish_jjw_hql="select finish from ItemFinish finish, ItemApprove approve where finish.id=approve.itemId and finish.status in ('"+ItemFinish.STATUS_11_ON_FINISH_PENDING_JJW_APPROVE+"') "
			+ " and approve.approverId='" + u.getId() + "' and approve.status='" + ItemApprove.STATUS_PENDING + "' and approve.type='"+ ItemApprove.TYPE_ONFINISH_JJW_APPROVE + "'";
			List status_11_on_finish_jjw_list = service.queryByHql(status_11_on_finish_jjw_hql);
			if(status_11_on_finish_jjw_list!=null){
				status_11_on_finish+=String.valueOf(status_11_on_finish_jjw_list.size());
			}
			
			String status_12_on_pay_seq = " from Item item where item.requesterId = '" + u.getId() + "' and item.status in ('"+Item.STATUS_11_ON_FINISH+"','"+Item.STATUS_12_ON_PAY+"') ";
			List itemOnPayList = service.queryByHql(status_12_on_pay_seq);
			if(itemOnPayList!=null){
				status_12_on_pay=String.valueOf(itemOnPayList.size());
			}
			
			String status_13_finished_seq = " from Item item where item.requesterId = '" + u.getId() + "' and (item.status='" + Item.STATUS_13_CLOSE + "') ";
			List itemOnFinishedList = service.queryByHql(status_13_finished_seq);
			if(itemOnFinishedList!=null){
				status_13_close=String.valueOf(itemOnFinishedList.size());
			}
			
		/*	newItemList=service.queryTopItems(5);
			List<Object[]> itemOpList = service.queryOperatorsByUserId(u.getId());
			if(itemOpList!=null){
				todoList = new ArrayList<TODOItem>();
				for(Object[] o:itemOpList){
					Item item = (Item)(o[0]); 
					ItemOperator itemOp=(ItemOperator)o[1];
					TODOItem todo = new TODOItem();
					todo.setName("项目'"+item.getItemName()+"'等待您的处理");
					todo.setURL("/oa/itemdetail_leader1.do?item.id="+item.getId()+"&itemOperator.id="+itemOp.getId());
					todoList.add(todo);
					if(ItemOperator.TYPE_LEADER1.equals(itemOp.getType())){
						TODOItem todo = new TODOItem();
						todo.setName("项目'"+item.getItemName()+"'等待您的审批");
						todo.setURL("/oa/itemview.do?item.id="+item.getId());
						todoList.add(todo);
					}else if(ItemOperator.TYPE_LEADER2.equals(itemOp.getType())){
						TODOItem todo = new TODOItem();
						todo.setName("项目'"+item.getItemName()+"'等待您的审批");
						todo.setURL("/oa/itemview_leader2.do?item.id="+item.getId());
						todoList.add(todo);
					}else if(ItemOperator.TYPE_REQUESTER.equals(itemOp.getType())){
						TODOItem todo = new TODOItem();
						todo.setName("项目'"+item.getItemName()+"'等待您的处理");
						todo.setURL("/oa/itemview_leader2.do?item.id="+item.getId());
						todoList.add(todo);
					}else if(ItemOperator.TYPE_MARKETING.equals(itemOp.getType())){
						TODOItem todo = new TODOItem();
						todo.setName("项目'"+item.getItemName()+"'等待您的处理");
						todo.setURL("/oa/itemview_leader2.do?item.id="+item.getId());
						todoList.add(todo);
					}
				}
			}*/
			
			
		} catch (Exception e) {
			this.addActionError(e.getMessage());
		}
		return SUCCESS;
	}


	public String getStatus_2_pending_approval() {
		return status_2_pending_approval;
	}


	public void setStatus_2_pending_approval(String status_2_pending_approval) {
		this.status_2_pending_approval = status_2_pending_approval;
	}


	/*public String getStatus_4_pending_survey() {
		return status_4_pending_survey;
	}


	public void setStatus_4_pending_survey(String status_4_pending_survey) {
		this.status_4_pending_survey = status_4_pending_survey;
	}*/


	public String getStatus_5_on_survey() {
		return status_5_on_survey;
	}


	public void setStatus_5_on_survey(String status_5_on_survey) {
		this.status_5_on_survey = status_5_on_survey;
	}


	public String getStatus_6_report_pending_approval() {
		return status_6_report_pending_approval;
	}


	public void setStatus_6_report_pending_approval(String status_6_report_pending_approval) {
		this.status_6_report_pending_approval = status_6_report_pending_approval;
	}


	public String getStatus_9_on_bid() {
		return status_9_on_bid;
	}


	public void setStatus_9_on_bid(String status_9_on_bid) {
		this.status_9_on_bid = status_9_on_bid;
	}


	public String getStatus_10_on_accept() {
		return status_10_on_accept;
	}


	public void setStatus_10_on_accept(String status_10_on_accept) {
		this.status_10_on_accept = status_10_on_accept;
	}


	public String getStatus_7_setup() {
		return status_7_setup;
	}


	public void setStatus_7_setup(String status_7_setup) {
		this.status_7_setup = status_7_setup;
	}


	public String getStatus_7_setup_pending_approve() {
		return status_7_setup_pending_approve;
	}


	public void setStatus_7_setup_pending_approve(String status_7_setup_pending_approve) {
		this.status_7_setup_pending_approve = status_7_setup_pending_approve;
	}


	public String getStatus_11_on_finish() {
		return status_11_on_finish;
	}


	public void setStatus_11_on_finish(String status_11_on_finish) {
		this.status_11_on_finish = status_11_on_finish;
	}


	public String getStatus_12_on_pay() {
		return status_12_on_pay;
	}


	public void setStatus_12_on_pay(String status_12_on_pay) {
		this.status_12_on_pay = status_12_on_pay;
	}


	public String getStatus_13_close() {
		return status_13_close;
	}


	public void setStatus_13_close(String status_13_close) {
		this.status_13_close = status_13_close;
	}


	
}
