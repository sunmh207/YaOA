package com.jitong.workflow.action;

import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemApprove;
import com.jitong.workflow.domain.ItemFinish;
import com.opensymphony.xwork2.Preparable;
/**
 * 提交纪检委审批
 * @author stanley.sun
 *
 */
public class OnFinish3Action extends JITActionBase implements Preparable {
	private static ItemService service;
	private ItemFinish itemFinish;
	
	private List<ItemApprove> leadApproveList;
	private List<ItemApprove> jjwApproveList;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (itemFinish != null && itemFinish.getId() != null) {
			itemFinish = (ItemFinish)service.findBoById(ItemFinish.class,itemFinish.getId());
		}
	}

	public String input() throws JTException {
		if (itemFinish != null && itemFinish.getId() != null) {
			leadApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ONFINISH_LEAD_APPROVE+"' and approve.itemId='" + itemFinish.getId() + "' order by approve.operationTime");
			jjwApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ONFINISH_JJW_APPROVE+"' and approve.itemId='" + itemFinish.getId() + "' order by approve.operationTime");
		}
		return INPUT;
	}
	

	public String submit() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		
		itemFinish.setStatus(ItemFinish.STATUS_11_ON_FINISH_PENDING_JJW_APPROVE);
		service.updateBo(itemFinish);
		
		//Add Approve record
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (String id : ids) {
			//check existing
			String hql="from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ONFINISH_JJW_APPROVE+"' " +
					" and approve.itemId='" + itemFinish.getId() + "' " +
					" and approve.status='"+ItemApprove.STATUS_PENDING+"'"+
					" and approve.approverId='" + id + "' ";
			
			List approveList =  service.queryByHql(hql);
			ItemApprove itemApprove = null;
			if(approveList.isEmpty()){
				itemApprove = new ItemApprove();
				itemApprove.setItemId(itemFinish.getId());
				itemApprove.setApproverId(id);
				itemApprove.setApproverName(SysCache.interpertUserName(id));
				itemApprove.setType(ItemApprove.TYPE_ONFINISH_JJW_APPROVE);
				itemApprove.setStatus(ItemApprove.STATUS_PENDING);
				service.createBo(itemApprove);
			}
		}
		
		return SUCCESS;
	}



	public ItemFinish getItemFinish() {
		return itemFinish;
	}

	public void setItemFinish(ItemFinish itemFinish) {
		this.itemFinish = itemFinish;
	}

	public List<ItemApprove> getLeadApproveList() {
		return leadApproveList;
	}

	public void setLeadApproveList(List<ItemApprove> leadApproveList) {
		this.leadApproveList = leadApproveList;
	}

	public List<ItemApprove> getJjwApproveList() {
		return jjwApproveList;
	}

	public void setJjwApproveList(List<ItemApprove> jjwApproveList) {
		this.jjwApproveList = jjwApproveList;
	}
	
}
