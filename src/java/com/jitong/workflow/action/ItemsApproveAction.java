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

public class ItemsApproveAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemApprove itemApprove;
	private String needResearch;
	private List<ItemApprove> itemApproveList;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			itemApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ITEM_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		}
		if (itemApprove != null && itemApprove.getId() != null) {
			itemApprove = (ItemApprove) service.findBoById(ItemApprove.class, itemApprove.getId());
		}
	}

	public String input() {
		return INPUT;
	}

	public String approve() {
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
			itemApprove.setType(ItemApprove.TYPE_ITEM_APPROVE);
			itemApprove.setStatus(ItemApprove.STATUS_APPROVED);
			service.updateBo(itemApprove);
			
			String hql="select count(*) as cnt from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ITEM_APPROVE+"' and approve.itemId='" + item.getId() + "' and approve.status='"+ItemApprove.STATUS_PENDING+"'";
			long cnt = (java.lang.Long) service.queryByHql(hql).get(0);
			
			if(cnt==0){//多个审批都通过
				if("1".equals(needResearch)){
					item.setStatus(Item.STATUS_5_PENDING_INFO);
				}else{
					item.setStatus(Item.STATUS_7_SETUP);
				}
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
			item.setStatus(Item.STATUS_3_REJECT);
			service.updateItem(item);
			itemApprove.setApproverId(u.getId());
			itemApprove.setApproverName(u.getName());
			itemApprove.setItemId(item.getId());
			itemApprove.setOperationTime(DateUtil.getCurrentTime());
			itemApprove.setStatus(ItemApprove.STATUS_REJECTED);
			itemApprove.setType(ItemApprove.TYPE_ITEM_APPROVE);
			service.updateBo(itemApprove);
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	/*public String getListHQL(ArrayList<Object> params) throws JTException {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		String hql = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
				" item.status in ('" + Item.STATUS_2_PENDING_APPROVAL + "','"+Item.STATUS_4_PENDING_SURVEY+"','"+Item.STATUS_3_REJECT+"','"+Item.STATUS_7_SETUP+"') " +
				" and approve.approverId='" + u.getId() + "' " +
				" and approve.status in ('" + ItemApprove.STATUS_PENDING + "','"+ItemApprove.STATUS_APPROVED+"')" +
				" and approve.type='"+ ItemApprove.TYPE_ITEM_APPROVE + "'";
		return hql;
	}*/
	public String list() throws JTException{
		User u = this.getLoginUser();
		if(u==null){
			throw new JTException("用户超时",this.getClass());
		}
		String hql = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
		" item.status in ('" + Item.STATUS_2_PENDING_APPROVAL + "','"+Item.STATUS_5_PENDING_INFO+"','"+Item.STATUS_3_REJECT+"','"+Item.STATUS_7_SETUP+"') " +
		" and approve.approverId='" + u.getId() + "' " +
		" and approve.status in ('" + ItemApprove.STATUS_PENDING + "','"+ItemApprove.STATUS_APPROVED+"')" +
		" and approve.type='"+ ItemApprove.TYPE_ITEM_APPROVE + "'";
		
		List l = service.queryByHql(hql);
		Map<String,Object[]> objMap= new HashMap<String,Object[]>();
		for(Object o:l){
			Object[] objAry=(Object[])o;
			Item item = (Item)objAry[0];
			ItemApprove itemApprove = (ItemApprove)objAry[1];
			String itemId= item.getId();
			
			Object tmp = objMap.get(itemId);
			if(tmp==null){
				objMap.put(itemId, objAry);
			}else{
				if(ItemApprove.STATUS_PENDING.equals(itemApprove.getStatus())){
					objMap.put(itemId, objAry);
				}
			}
		}
		List<Object[]> list = new ArrayList<Object[]>();
		list.addAll(objMap.values());
		request.setAttribute("objectList", list);
		this.setObjectList(list);
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

	public String getNeedResearch() {
		return needResearch;
	}

	public void setNeedResearch(String needResearch) {
		this.needResearch = needResearch;
	}

	public List<ItemApprove> getItemApproveList() {
		return itemApproveList;
	}

	public void setItemApproveList(List<ItemApprove> itemApproveList) {
		this.itemApproveList = itemApproveList;
	}

}