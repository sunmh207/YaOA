package com.jitong.workflow.action;

import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemApprove;
import com.opensymphony.xwork2.Preparable;
/**
 * 提交报告给领导审批
 * @author stanley.sun
 *
 */
public class OnSurvey5_ConfirmedAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	
	private List<ItemApprove> mktSignApproveList;
	private List<ItemApprove> mktConfirmApproveList;
	private List<ItemApprove> rptApproveList;
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			mktSignApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_MKT_SIGN+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
			mktConfirmApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_MKT_CONFIRM+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
			rptApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_RPT_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		}
	}

	public String input() throws JTException {
		return INPUT;
	}

	/**
	 * 发通知
	 * 
	 * @return
	 * @throws Exception
	 */
	public String submit() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			this.addActionError("当前用户会话过期");
			return INPUT;
		}
		
		item.setStatus(Item.STATUS_6_MKT_RPT_PENDING_APPROVE);
		item.setSurveyReportReporterId(u.getId());
		item.setSurveyReportReporterName(u.getName());
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		
		for (String id : ids) {
			//check existing
			String hql="from ItemApprove approve where approve.type='"+ItemApprove.TYPE_RPT_APPROVE+"' " +
					" and approve.itemId='" + item.getId() + "' " +
					" and approve.status='"+ItemApprove.STATUS_PENDING+"'"+
					" and approve.approverId='" + id + "' ";
			
			List approveList =  service.queryByHql(hql);
			ItemApprove itemApprove = null;
			if(approveList.isEmpty()){
				itemApprove = new ItemApprove();
				itemApprove.setItemId(item.getId());
				itemApprove.setApproverId(id);
				itemApprove.setApproverName(SysCache.interpertUserName(id));
				itemApprove.setType(ItemApprove.TYPE_RPT_APPROVE);
				itemApprove.setStatus(ItemApprove.STATUS_PENDING);
				service.createBo(itemApprove);
			}
		}
		

		service.updateItem(item);
		return SUCCESS;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<ItemApprove> getMktConfirmApproveList() {
		return mktConfirmApproveList;
	}

	public void setMktConfirmApproveList(List<ItemApprove> mktConfirmApproveList) {
		this.mktConfirmApproveList = mktConfirmApproveList;
	}

	public List<ItemApprove> getMktSignApproveList() {
		return mktSignApproveList;
	}

	public void setMktSignApproveList(List<ItemApprove> mktSignApproveList) {
		this.mktSignApproveList = mktSignApproveList;
	}

	public List<ItemApprove> getRptApproveList() {
		return rptApproveList;
	}

	public void setRptApproveList(List<ItemApprove> rptApproveList) {
		this.rptApproveList = rptApproveList;
	}
}
