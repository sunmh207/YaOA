package com.jitong.workflow.action;

import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemApprove;
import com.jitong.workflow.domain.ItemFinish;
import com.opensymphony.xwork2.Preparable;
/**
 * 招标管理部门审批
 * @author stanley.sun
 *
 */
public class OnFinish4_JJWApproveAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private ItemFinish itemFinish;
	private ItemApprove itemApprove;
	private List<ItemApprove> leadApproveList;
	private List<ItemApprove> jjwApproveList;

	
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (itemFinish != null && itemFinish.getId() != null) {
			itemFinish = (ItemFinish)service.findBoById(ItemFinish.class,itemFinish.getId());
		}
		if (itemApprove != null && itemApprove.getId() != null) {
			itemApprove = (ItemApprove) service.findBoById(ItemApprove.class, itemApprove.getId());
		}
	}

	public String input() throws JTException {
		if (itemFinish != null && itemFinish.getId() != null) {
			leadApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ONFINISH_LEAD_APPROVE+"' and approve.itemId='" + itemFinish.getId() + "' order by approve.operationTime");
			jjwApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ONFINISH_JJW_APPROVE+"' and approve.itemId='" + itemFinish.getId() + "' order by approve.operationTime");
		}
		return INPUT;
	}

	public String approve() {
		try {
			User u = this.getLoginUser();
			if (u == null) {
				this.addActionError("当前用户会话过期");
				return INPUT;
			}
			if(StringUtil.isEmpty(itemFinish.getJjwComments())){
				itemFinish.setJjwComments("同意");
			}
			
			itemApprove.setApproverId(u.getId());
			itemApprove.setApproverName(u.getName());
			itemApprove.setItemId(itemFinish.getId());
			itemApprove.setOperationTime(DateUtil.getCurrentTime());
			itemApprove.setType(ItemApprove.TYPE_ONFINISH_JJW_APPROVE);
			itemApprove.setStatus(ItemApprove.STATUS_APPROVED);
			itemApprove.setComments(itemFinish.getJjwComments());
			service.updateBo(itemApprove);
			
			String hql="select count(*) as cnt from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ONFINISH_JJW_APPROVE+"' and approve.itemId='" + itemFinish.getId() + "' and approve.status='"+ItemApprove.STATUS_PENDING+"'";
			long cnt = (java.lang.Long) service.queryByHql(hql).get(0);
			
			if(cnt==0){//多个审批都通过才算审批通过
				itemFinish.setStatus(ItemFinish.STATUS_11_ON_FINISH_JJW_APPROVED);
				itemFinish.setJjw(u.getName());
				service.updateBo(itemFinish);
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
			if(StringUtil.isEmpty(itemFinish.getJjwComments())){
				itemFinish.setJjwComments("不同意");
			}
			
			itemFinish.setStatus(ItemFinish.STATUS_11_ON_FINISH_JJW_REJECT);
			itemFinish.setJjw(u.getName());
			service.updateBo(itemFinish);
			
			itemApprove.setApproverId(u.getId());
			itemApprove.setApproverName(u.getName());
			itemApprove.setItemId(itemFinish.getId());
			itemApprove.setOperationTime(DateUtil.getCurrentTime());
			itemApprove.setStatus(ItemApprove.STATUS_REJECTED);
			itemApprove.setType(ItemApprove.TYPE_ONFINISH_JJW_APPROVE);
			itemApprove.setComments(itemFinish.getLeadComments());
			service.updateBo(itemApprove);
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	

	public ItemFinish getItemFinish() {
		return itemFinish;
	}

	public void setItemFinish(ItemFinish itemFinish) {
		this.itemFinish = itemFinish;
	}

	public ItemApprove getItemApprove() {
		return itemApprove;
	}

	public void setItemApprove(ItemApprove itemApprove) {
		this.itemApprove = itemApprove;
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
