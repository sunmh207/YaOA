package com.jitong.workflow.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

public class MyNewItemsAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
    private List<ItemApprove> approveList;
    private List<ItemApprove> itemApproveList;
    
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			if (item != null && item.getId() != null) {
				itemApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ITEM_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
			}
		}
	}


	public String input() {
		try{
			if (item != null && item.getId() != null) {
				approveList = service.queryItemApproves(item.getId());  
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
		}
		return INPUT;
	}

	public String delete() throws JTException {
		service.deleteItem(item.getId());
		return list();
	}


	public String save(){
		try {
			User u = this.getLoginUser();
			if (u==null){
				this.addActionError("当前用户会话过期");
				return INPUT;
			}
			item.setStatus(Item.STATUS_1_NEW);
			// new
			if (item.getId() == null || "".equals(item.getId())) {
				item.setRequesterId(u.getId());
				item.setRequesterName(u.getName());
				item.setApplyDate(DateUtil.getCurrentDate());
				service.insertItem(item);
				// modify
			} else { 
				service.updateItem(item);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	
/**
 * 提交领导审批
 * @return
 * @throws Exception
 */
	public String submit4approve() throws Exception {
		try {
			User u = this.getLoginUser();
			if (u==null){
				this.addActionError("当前用户会话过期");
				return INPUT;
			}
			// new
			if (item.getId() == null || "".equals(item.getId())) {
				item.setRequesterId(u.getId());
				item.setRequesterName(u.getName());
				item.setApplyDate(DateUtil.getCurrentDate());
				item.setStatus(Item.STATUS_2_PENDING_APPROVAL);
				service.insertItem(item);
			} else { 
				item.setStatus(Item.STATUS_2_PENDING_APPROVAL);
				service.updateItem(item);
			}
			
			String idString = request.getParameter("useridStr");
			String[] ids = StringUtil.parseString2Array(idString, ",");
			for (String id : ids) {
				//check existing
				String hql="from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ITEM_APPROVE+"' " +
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
					itemApprove.setType(ItemApprove.TYPE_ITEM_APPROVE);
					itemApprove.setStatus(ItemApprove.STATUS_PENDING);
					service.createBo(itemApprove);
				}
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		User u = this.getLoginUser();
		if(u==null){
			throw new JTException("用户超时",this.getClass());
		}
		String hql = "from com.jitong.oa.domain.Item me where me.status in('"+Item.STATUS_1_NEW+"','"+Item.STATUS_3_REJECT+"','"+Item.STATUS_2_PENDING_APPROVAL+"') and me.requesterId='"+u.getId()+"'";
		return hql;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public List<ItemApprove> getApproveList() {
		return approveList;
	}


	public void setApproveList(List<ItemApprove> approveList) {
		this.approveList = approveList;
	}


	public List<ItemApprove> getItemApproveList() {
		return itemApproveList;
	}


	public void setItemApproveList(List<ItemApprove> itemApproveList) {
		this.itemApproveList = itemApproveList;
	}

}
