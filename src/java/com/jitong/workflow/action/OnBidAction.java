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
 *
 */
public class OnBidAction extends JITActionBase implements Preparable {
	private static ItemService service;
	List<Item> pendingBidList;
	
	List<Item> forZBDeptViewList;
	List<Object[]> pendingZBDeptApproveList;
	
	List<Item> onbid3List;
	
	List<Item> forJJWViewList;
	List<Object[]> pendingJJWApproveList;
	
	List<Item> onbid5List;
	List<Item> pendingMeetingRecordList;
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
		String pendingBidHQL = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_9_ON_BID+"','"+Item.STATUS_9_ON_BID_ZBDEPT_REJECT+"','"+Item.STATUS_9_ON_BID_JJW_REJECT+"','"+Item.STATUS_9_ON_BID_PENDING_ZBDEPT_APPROVE+"')";
		pendingBidList = service.queryByHql(pendingBidHQL);
		//---------------
		
		String forZBDeptViewHQL = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_9_ON_BID_ZBDEPT_REJECT+"','"+Item.STATUS_9_ON_BID_ZBDEPT_APPROVED+"')";
		forZBDeptViewList = service.queryByHql(forZBDeptViewHQL);
		String pendingZBDeptApproveHQL = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
		" item.status in ('" + Item.STATUS_9_ON_BID_PENDING_ZBDEPT_APPROVE + "') " +
		" and approve.approverId='" + u.getId() + "' " +
		" and approve.status in ('" + ItemApprove.STATUS_PENDING + "')" +
		" and approve.type='"+ ItemApprove.TYPE_ZBDEPT_APPROVE + "'";
		List tmp_pendingZBDeptApproveList = service.queryByHql(pendingZBDeptApproveHQL);
		Map<String,Object[]> tmp_pendingZBDeptApproveMap= new HashMap<String,Object[]>();
		for(Object o:tmp_pendingZBDeptApproveList){
			Object[] objAry=(Object[])o;
			Item item = (Item)objAry[0];
			ItemApprove itemApprove = (ItemApprove)objAry[1];
			String itemId= item.getId();
			
			Object tmp = tmp_pendingZBDeptApproveMap.get(itemId);
			if(tmp==null){
				tmp_pendingZBDeptApproveMap.put(itemId, objAry);
			}else{
				if(ItemApprove.STATUS_PENDING.equals(itemApprove.getStatus())){
					tmp_pendingZBDeptApproveMap.put(itemId, objAry);
				}
			}
		}
		pendingZBDeptApproveList = new ArrayList<Object[]>();
		pendingZBDeptApproveList.addAll(tmp_pendingZBDeptApproveMap.values());
		
		
		
		
		String onbid3HQL = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_9_ON_BID_ZBDEPT_APPROVED+"','"+Item.STATUS_9_ON_BID_JJW_REJECT+"','"+Item.STATUS_9_ON_BID_PENDING_JJW_APPROVE+"')";
		onbid3List = service.queryByHql(onbid3HQL);
		//-------------
		/*String pendingJJWApproveHQL = "select item from Item item, ItemBid bid  where item.id=bid.itemId and bid.jjwDeptApproverId='"+u.getId()+"' and item.status in('"+Item.STATUS_9_ON_BID_PENDING_JJW_APPROVE+"')";
		pendingJJWApproveList = service.queryByHql(pendingJJWApproveHQL);*/
		
		String forJJWViewHQL = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_9_ON_BID_JJW_REJECT+"','"+Item.STATUS_9_ON_BID_JJW_APPROVED+"')";
		forJJWViewList = service.queryByHql(forJJWViewHQL);
		String pendingJJWApproveHQL = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
		" item.status in ('" + Item.STATUS_9_ON_BID_PENDING_JJW_APPROVE + "') " +
		" and approve.approverId='" + u.getId() + "' " +
		" and approve.status in ('" + ItemApprove.STATUS_PENDING + "')" +
		" and approve.type='"+ ItemApprove.TYPE_JJW_APPROVE + "'";
		List tmp_pendingJJWApproveList = service.queryByHql(pendingJJWApproveHQL);
		Map<String,Object[]> tmp_pendingJJWApproveMap= new HashMap<String,Object[]>();
		for(Object o:tmp_pendingJJWApproveList){
			Object[] objAry=(Object[])o;
			Item item = (Item)objAry[0];
			ItemApprove itemApprove = (ItemApprove)objAry[1];
			String itemId= item.getId();
			
			Object tmp = tmp_pendingJJWApproveMap.get(itemId);
			if(tmp==null){
				tmp_pendingJJWApproveMap.put(itemId, objAry);
			}else{
				if(ItemApprove.STATUS_PENDING.equals(itemApprove.getStatus())){
					tmp_pendingJJWApproveMap.put(itemId, objAry);
				}
			}
		}
		pendingJJWApproveList = new ArrayList<Object[]>();
		pendingJJWApproveList.addAll(tmp_pendingJJWApproveMap.values());
		
		
		
		//---------------
		String onbid5ListHQL = "select item from Item item where item.requesterId='"+u.getId()+"' and item.status in('"+Item.STATUS_9_ON_BID_JJW_APPROVED+"')";
		onbid5List = service.queryByHql(onbid5ListHQL);
		
		String pendingMeetingRecordListHQL = "select item from Item item,ItemBid bid where item.id=bid.itemId and bid.zbdeptMeetingRecorderId like '%"+u.getId()+"%' and item.status in('"+Item.STATUS_9_ON_BID_PENDING_ZBDEPT_MEETINGNOTE+"','"+Item.STATUS_10_ON_ACCEPT+"')";
		pendingMeetingRecordList = service.queryByHql(pendingMeetingRecordListHQL);
		
		return SUCCESS;
	}

	public List<Item> getPendingBidList() {
		return pendingBidList;
	}

	public void setPendingBidList(List<Item> pendingBidList) {
		this.pendingBidList = pendingBidList;
	}

	public List<Item> getForZBDeptViewList() {
		return forZBDeptViewList;
	}

	public void setForZBDeptViewList(List<Item> forZBDeptViewList) {
		this.forZBDeptViewList = forZBDeptViewList;
	}

	public List<Object[]> getPendingZBDeptApproveList() {
		return pendingZBDeptApproveList;
	}

	public void setPendingZBDeptApproveList(List<Object[]> pendingZBDeptApproveList) {
		this.pendingZBDeptApproveList = pendingZBDeptApproveList;
	}

	public List<Object[]> getPendingJJWApproveList() {
		return pendingJJWApproveList;
	}

	public void setPendingJJWApproveList(List<Object[]> pendingJJWApproveList) {
		this.pendingJJWApproveList = pendingJJWApproveList;
	}

	public List<Item> getOnbid3List() {
		return onbid3List;
	}

	public void setOnbid3List(List<Item> onbid3List) {
		this.onbid3List = onbid3List;
	}

	public List<Item> getOnbid5List() {
		return onbid5List;
	}

	public void setOnbid5List(List<Item> onbid5List) {
		this.onbid5List = onbid5List;
	}

	public List<Item> getPendingMeetingRecordList() {
		return pendingMeetingRecordList;
	}

	public void setPendingMeetingRecordList(List<Item> pendingMeetingRecordList) {
		this.pendingMeetingRecordList = pendingMeetingRecordList;
	}

	public List<Item> getForJJWViewList() {
		return forJJWViewList;
	}

	public void setForJJWViewList(List<Item> forJJWViewList) {
		this.forJJWViewList = forJJWViewList;
	}

}