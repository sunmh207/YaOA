package com.jitong.workflow.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemApprove;
import com.opensymphony.xwork2.Preparable;

public class OnSurvey4_PendingConfirmAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemApprove itemApprove;
	private List<ItemApprove> mktConfirmApproveList;
	private String confirmComments;
		
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			mktConfirmApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='" + ItemApprove.TYPE_MKT_CONFIRM
					+ "' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		}
		if (itemApprove != null && itemApprove.getId() != null) {
			itemApprove = (ItemApprove) service.findBoById(ItemApprove.class, itemApprove.getId());
		}
	}

	public String input() {
		return INPUT;
	}

	public String confirm() {
		try {
			User u = this.getLoginUser();
			if (u == null) {
				this.addActionError("当前用户会话过期");
				return INPUT;
			}

			itemApprove.setApproverId(u.getId());
			itemApprove.setApproverName(u.getName());
			itemApprove.setItemId(item.getId());
			itemApprove.setOperationTime(DateUtil.getCurrentTime());
			itemApprove.setType(ItemApprove.TYPE_MKT_CONFIRM);
			itemApprove.setStatus(ItemApprove.STATUS_APPROVED);
			itemApprove.setComments(confirmComments);
			service.updateBo(itemApprove);

			String hql = "select count(*) as cnt from ItemApprove approve where approve.type='" + ItemApprove.TYPE_MKT_CONFIRM + "' and approve.itemId='"
					+ item.getId() + "' and approve.status='" + ItemApprove.STATUS_PENDING + "'";
			long cnt = (java.lang.Long) service.queryByHql(hql).get(0);

			if (cnt == 0) {// 多个审批都通过
				item.setStatus(Item.STATUS_5_MKT_CONFIRMED);
				service.updateItem(item);
			}

		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	
	public String reject() {
		try {
			User u = this.getLoginUser();
			if (u == null) {
				this.addActionError("当前用户会话过期");
				return INPUT;
			}
			item.setStatus(Item.STATUS_5_MKT_SIGNED);
			service.updateItem(item);
			itemApprove.setApproverId(u.getId());
			itemApprove.setApproverName(u.getName());
			itemApprove.setItemId(item.getId());
			itemApprove.setOperationTime(DateUtil.getCurrentTime());
			itemApprove.setStatus(ItemApprove.STATUS_REJECTED);
			itemApprove.setType(ItemApprove.TYPE_MKT_CONFIRM);
			itemApprove.setComments(confirmComments);
			service.updateBo(itemApprove);
			
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	public Item getItem() {
		return item;
	}

	public ItemApprove getItemApprove() {
		return itemApprove;
	}

	public void setItemApprove(ItemApprove itemApprove) {
		this.itemApprove = itemApprove;
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


	public String getConfirmComments() {
		return confirmComments;
	}

	public void setConfirmComments(String confirmComments) {
		this.confirmComments = confirmComments;
	}
}