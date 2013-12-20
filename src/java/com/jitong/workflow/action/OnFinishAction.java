package com.jitong.workflow.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemApprove;
import com.opensymphony.xwork2.Preparable;
/**
 * 招标中
 * @author stanley.sun
 * 发起人（填写结项表）->主管领导审批 ->（同意）->发起人>纪委审批->发起人-才能进行结项。 
 */
public class OnFinishAction extends JITActionBase implements Preparable {
	private static ItemService service;
	List<Item> forSponsorList1;
	List<Object[]> forLeadList;
	List<Item> forSponsorList2;
	List<Object[]> forJJWList;
	List<Item> forSponsorList3;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
	}

	public String list() throws JTException {
		User u = this.getLoginUser();
		if (u == null) {
			this.addActionError("当前用户会话过期");
			return SUCCESS;
		}
		String forSponsorHQL = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_11_ON_FINISH+"','"+Item.STATUS_11_ON_FINISH_LEAD_REJECT+"','"+Item.STATUS_11_ON_FINISH_JJW_REJECT+"','"+Item.STATUS_10_ON_ACCEPT+"')";
		forSponsorList1 = service.queryByHql(forSponsorHQL);
		
		
		String forLeadHQL = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
		" item.status in ('" + Item.STATUS_11_ON_FINISH_PENDING_LEAD_APPROVE + "') " +
		" and approve.approverId='" + u.getId() + "' " +
		" and approve.status in ('" + ItemApprove.STATUS_PENDING + "','"+ItemApprove.STATUS_APPROVED+"','"+ItemApprove.STATUS_REJECTED+"')" +
		" and approve.type='"+ ItemApprove.TYPE_ONFINISH_LEAD_APPROVE + "'";
		List tmp_forLeadList = service.queryByHql(forLeadHQL);
		Map<String,Object[]> tmp_forLeadMap= new HashMap<String,Object[]>();
		for(Object o:tmp_forLeadList){
			Object[] objAry=(Object[])o;
			Item item = (Item)objAry[0];
			ItemApprove itemApprove = (ItemApprove)objAry[1];
			String itemId= item.getId();
			
			Object tmp = tmp_forLeadMap.get(itemId);
			if(tmp==null){
				tmp_forLeadMap.put(itemId, objAry);
			}else{
				if(ItemApprove.STATUS_PENDING.equals(itemApprove.getStatus())){
					tmp_forLeadMap.put(itemId, objAry);
				}
			}
		}
		forLeadList = new ArrayList<Object[]>();
		forLeadList.addAll(tmp_forLeadMap.values());
		
		
		String forSponsorHQL2 = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_11_ON_FINISH_LEAD_APPROVED+"')";
		forSponsorList2 = service.queryByHql(forSponsorHQL2);
		
		
		String forJJWHQL = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
		" item.status in ('" + Item.STATUS_11_ON_FINISH_PENDING_JJW_APPROVE + "') " +
		" and approve.approverId='" + u.getId() + "' " +
		" and approve.status in ('" + ItemApprove.STATUS_PENDING + "','"+ItemApprove.STATUS_APPROVED+"','"+ItemApprove.STATUS_REJECTED+"')" +
		" and approve.type in ('"+ ItemApprove.TYPE_ONFINISH_JJW_APPROVE + "')";
		List tmp_forJJWList = service.queryByHql(forJJWHQL);
		Map<String,Object[]> tmp_forJJWMap= new HashMap<String,Object[]>();
		for(Object o:tmp_forJJWList){
			Object[] objAry=(Object[])o;
			Item item = (Item)objAry[0];
			ItemApprove itemApprove = (ItemApprove)objAry[1];
			String itemId= item.getId();
			
			Object tmp = tmp_forJJWMap.get(itemId);
			if(tmp==null){
				tmp_forJJWMap.put(itemId, objAry);
			}else{
				if(ItemApprove.STATUS_PENDING.equals(itemApprove.getStatus())){
					tmp_forJJWMap.put(itemId, objAry);
				}
			}
		}
		forJJWList = new ArrayList<Object[]>();
		forJJWList.addAll(tmp_forJJWMap.values());
		
		String forSponsorHQL3 = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_12_ON_PAY+"')";
		forSponsorList3 = service.queryByHql(forSponsorHQL3);
		
		return SUCCESS;
	}

	public List<Item> getForSponsorList1() {
		return forSponsorList1;
	}

	public void setForSponsorList1(List<Item> forSponsorList1) {
		this.forSponsorList1 = forSponsorList1;
	}

	public List<Object[]> getForLeadList() {
		return forLeadList;
	}

	public void setForLeadList(List<Object[]> forLeadList) {
		this.forLeadList = forLeadList;
	}

	public List<Item> getForSponsorList2() {
		return forSponsorList2;
	}

	public void setForSponsorList2(List<Item> forSponsorList2) {
		this.forSponsorList2 = forSponsorList2;
	}

	public List<Object[]> getForJJWList() {
		return forJJWList;
	}

	public void setForJJWList(List<Object[]> forJJWList) {
		this.forJJWList = forJJWList;
	}

	public List<Item> getForSponsorList3() {
		return forSponsorList3;
	}

	public void setForSponsorList3(List<Item> forSponsorList3) {
		this.forSponsorList3 = forSponsorList3;
	}

	

	
}