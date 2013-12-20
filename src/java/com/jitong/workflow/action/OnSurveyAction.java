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
public class OnSurveyAction extends JITActionBase implements Preparable {
	private static ItemService service;
	List<Item> pendingInfoList;
	List<Object[]> pendingMKTSignList;
	List<Item> pendingMKTSignedList;
	List<Object[]> pendingMKTConfirmList;
	
	List<Item> mktConfirmedList;
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
		String pendingInfoHQL = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_5_PENDING_INFO+"','"+Item.STATUS_5_PENDING_MKT_SIGN+"')";
		pendingInfoList = service.queryByHql(pendingInfoHQL);
		
		
		String pendingMKTSignHQL = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
		" item.status in ('" + Item.STATUS_5_PENDING_MKT_SIGN + "') " +
		" and approve.approverId='" + u.getId() + "' " +
		" and approve.status in ('" + ItemApprove.STATUS_PENDING + "','"+ItemApprove.STATUS_APPROVED+"','"+ItemApprove.STATUS_REJECTED+"')" +
		" and approve.type='"+ ItemApprove.TYPE_MKT_SIGN + "'";
		List tmp_pendingMKTSignList = service.queryByHql(pendingMKTSignHQL);
		Map<String,Object[]> tmp_pendingMKTSignMap= new HashMap<String,Object[]>();
		for(Object o:tmp_pendingMKTSignList){
			Object[] objAry=(Object[])o;
			Item item = (Item)objAry[0];
			ItemApprove itemApprove = (ItemApprove)objAry[1];
			String itemId= item.getId();
			
			Object tmp = tmp_pendingMKTSignMap.get(itemId);
			if(tmp==null){
				tmp_pendingMKTSignMap.put(itemId, objAry);
			}else{
				if(ItemApprove.STATUS_PENDING.equals(itemApprove.getStatus())){
					tmp_pendingMKTSignMap.put(itemId, objAry);
				}
			}
		}
		pendingMKTSignList = new ArrayList<Object[]>();
		pendingMKTSignList.addAll(tmp_pendingMKTSignMap.values());
		
		
		String pendingMKTSignedHQL = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_5_MKT_SIGNED+"')";
		pendingMKTSignedList = service.queryByHql(pendingMKTSignedHQL);
		
		
		String pendingMKTConfirmHQL = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
		" item.status in ('" + Item.STATUS_5_PENDING_MKT_CONFIRM + "') " +
		" and approve.approverId='" + u.getId() + "' " +
		" and approve.status in ('" + ItemApprove.STATUS_PENDING + "','"+ItemApprove.STATUS_APPROVED+"','"+ItemApprove.STATUS_REJECTED+"')" +
		" and approve.type in ('"+ ItemApprove.TYPE_MKT_CONFIRM + "')";
		List tmp_pendingMKTConfirmList = service.queryByHql(pendingMKTConfirmHQL);
		Map<String,Object[]> tmp_pendingMKTConfirmMap= new HashMap<String,Object[]>();
		for(Object o:tmp_pendingMKTConfirmList){
			Object[] objAry=(Object[])o;
			Item item = (Item)objAry[0];
			ItemApprove itemApprove = (ItemApprove)objAry[1];
			String itemId= item.getId();
			
			Object tmp = tmp_pendingMKTConfirmMap.get(itemId);
			if(tmp==null){
				tmp_pendingMKTConfirmMap.put(itemId, objAry);
			}else{
				if(ItemApprove.STATUS_PENDING.equals(itemApprove.getStatus())){
					tmp_pendingMKTConfirmMap.put(itemId, objAry);
				}
			}
		}
		pendingMKTConfirmList = new ArrayList<Object[]>();
		pendingMKTConfirmList.addAll(tmp_pendingMKTConfirmMap.values());
		
		
		//---------------
		String mktConfirmedHQL = "select item from Item item where item.requesterId='"+u.getId()+"' and item.status in('"+Item.STATUS_5_MKT_CONFIRMED+"','"+Item.STATUS_6_MKT_RPT_REJECT+"','"+Item.STATUS_6_MKT_RPT_PENDING_APPROVE+"')";
		mktConfirmedList = service.queryByHql(mktConfirmedHQL);
		
	/*	String rejectHQL = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
		" item.status in (''"+Item.STATUS_6_MKT_RPT_REJECT+"') " +
		" and approve.approverId='" + u.getId() + "' " +
		" and approve.status in ('" + ItemApprove.STATUS_PENDING + "','"+ItemApprove.STATUS_APPROVED+"','"+ItemApprove.STATUS_REJECTED+"')" +
		" and approve.type in ('"+ItemApprove.TYPE_RPT_APPROVE+"')";
		List rejectList = service.queryByHql(rejectHQL);
		Map<String,Object[]> tmp_rejectListMap= new HashMap<String,Object[]>();
		for(Object o:rejectList){
			Object[] objAry=(Object[])o;
			Item item = (Item)objAry[0];
			ItemApprove itemApprove = (ItemApprove)objAry[1];
			String itemId= item.getId();
			
			Object tmp = tmp_rejectListMap.get(itemId);
			if(tmp==null){
				tmp_rejectListMap.put(itemId, objAry);
			}else{
				if(ItemApprove.STATUS_PENDING.equals(itemApprove.getStatus())){
					tmp_rejectListMap.put(itemId, objAry);
				}
			}
		}
		mktConfirmedList.addAll(tmp_rejectListMap.values());*/
		
		return SUCCESS;
	}

	public List<Item> getPendingInfoList() {
		return pendingInfoList;
	}

	public void setPendingInfoList(List<Item> pendingInfoList) {
		this.pendingInfoList = pendingInfoList;
	}

	public List<Object[]> getPendingMKTSignList() {
		return pendingMKTSignList;
	}

	public void setPendingMKTSignList(List<Object[]> pendingMKTSignList) {
		this.pendingMKTSignList = pendingMKTSignList;
	}

	public List<Item> getPendingMKTSignedList() {
		return pendingMKTSignedList;
	}

	public void setPendingMKTSignedList(List<Item> pendingMKTSignedList) {
		this.pendingMKTSignedList = pendingMKTSignedList;
	}

	public List<Object[]> getPendingMKTConfirmList() {
		return pendingMKTConfirmList;
	}

	public void setPendingMKTConfirmList(List<Object[]> pendingMKTConfirmList) {
		this.pendingMKTConfirmList = pendingMKTConfirmList;
	}

	public List<Item> getMktConfirmedList() {
		return mktConfirmedList;
	}

	public void setMktConfirmedList(List<Item> mktConfirmedList) {
		this.mktConfirmedList = mktConfirmedList;
	}

	
}