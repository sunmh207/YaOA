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
import com.jitong.workflow.domain.ItemFinish;
import com.opensymphony.xwork2.Preparable;
/**
 * 招标中
 * @author stanley.sun
 * 发起人（填写结项表）->主管领导审批 ->（同意）->发起人>纪委审批->发起人-才能进行结项。 
 */
public class OnFinishAction extends JITActionBase implements Preparable {
	private static ItemService service;
	List<Item> forSponsorList1;
	List<ItemFinish> forSponsorHQLRejectList;
	List<Object[]> forLeadList;
	List<ItemFinish> forSponsorList2;
	List<Object[]> forJJWList;
	List<ItemFinish> forSponsorList3;
	
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
		//String forSponsorHQL = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_11_ON_FINISH+"','"+Item.STATUS_11_ON_FINISH_LEAD_REJECT+"','"+Item.STATUS_11_ON_FINISH_JJW_REJECT+"','"+Item.STATUS_10_ON_ACCEPT+"')";
		String forSponsorHQL = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_11_ON_FINISH+"','"+Item.STATUS_10_ON_ACCEPT+"')";
		forSponsorList1 = service.queryByHql(forSponsorHQL);
		String forSponsorHQLReject = " from ItemFinish finish where status in ('"+ItemFinish.STATUS_11_ON_FINISH_LEAD_REJECT+"','"+ItemFinish.STATUS_11_ON_FINISH_JJW_REJECT+"')" ;
		forSponsorHQLRejectList = service.queryByHql(forSponsorHQLReject);
				
		String forLeadHQL = "select finish,approve from ItemFinish finish, ItemApprove approve where finish.id=approve.itemId and " +
		" finish.status in ('" + ItemFinish.STATUS_11_ON_FINISH_PENDING_LEAD_APPROVE + "') " +
		" and approve.approverId='" + u.getId() + "' " +
		" and approve.status in ('" + ItemApprove.STATUS_PENDING + "','"+ItemApprove.STATUS_APPROVED+"','"+ItemApprove.STATUS_REJECTED+"')" +
		" and approve.type='"+ ItemApprove.TYPE_ONFINISH_LEAD_APPROVE + "'";		
		List tmp_forLeadList = service.queryByHql(forLeadHQL);
		Map<String,Object[]> tmp_forLeadMap= new HashMap<String,Object[]>();
		for(Object o:tmp_forLeadList){
			Object[] objAry=(Object[])o;
			ItemFinish finish = (ItemFinish)objAry[0];
			ItemApprove itemApprove = (ItemApprove)objAry[1];
			String finishId= finish.getId();
			
			Object tmp = tmp_forLeadMap.get(finishId);
			if(tmp==null){
				tmp_forLeadMap.put(finishId, objAry);
			}else{
				if(ItemApprove.STATUS_PENDING.equals(itemApprove.getStatus())){
					tmp_forLeadMap.put(finishId, objAry);
				}
			}
		}
		forLeadList = new ArrayList<Object[]>();
		forLeadList.addAll(tmp_forLeadMap.values());
		
		
		
		String forSponsorHQL2 = "from ItemFinish itemFinish  where itemFinish.item.requesterId='"+u.getId()+"' and itemFinish.status in ('"+ItemFinish.STATUS_11_ON_FINISH_LEAD_APPROVED+"')";
		forSponsorList2 = service.queryByHql(forSponsorHQL2);
		
		
		String forJJWHQL = "select itemFinish,approve from ItemFinish itemFinish, ItemApprove approve where itemFinish.id=approve.itemId and " +
		" itemFinish.status in ('" + ItemFinish.STATUS_11_ON_FINISH_PENDING_JJW_APPROVE + "') " +
		" and approve.approverId='" + u.getId() + "' " +
		" and approve.status in ('" + ItemApprove.STATUS_PENDING + "','"+ItemApprove.STATUS_APPROVED+"','"+ItemApprove.STATUS_REJECTED+"')" +
		" and approve.type in ('"+ ItemApprove.TYPE_ONFINISH_JJW_APPROVE + "')";
		List tmp_forJJWList = service.queryByHql(forJJWHQL);
		Map<String,Object[]> tmp_forJJWMap= new HashMap<String,Object[]>();
		for(Object o:tmp_forJJWList){
			Object[] objAry=(Object[])o;
			ItemFinish itemFinish = (ItemFinish)objAry[0];
			ItemApprove itemApprove = (ItemApprove)objAry[1];
			String itemFinishId= itemFinish.getId();
			
			Object tmp = tmp_forJJWMap.get(itemFinishId);
			if(tmp==null){
				tmp_forJJWMap.put(itemFinishId, objAry);
			}else{
				if(ItemApprove.STATUS_PENDING.equals(itemApprove.getStatus())){
					tmp_forJJWMap.put(itemFinishId, objAry);
				}
			}
		}
		forJJWList = new ArrayList<Object[]>();
		forJJWList.addAll(tmp_forJJWMap.values());
		
		String forSponsorHQL3 = "from ItemFinish itemFinish  where itemFinish.item.requesterId='"+u.getId()+"' and itemFinish.status in ('"+ItemFinish.STATUS_11_ON_FINISH_JJW_APPROVED+"')";
		forSponsorList3 = service.queryByHql(forSponsorHQL3);
		
		return SUCCESS;
	}

	public List<Item> getForSponsorList1() {
		return forSponsorList1;
	}

	public void setForSponsorList1(List<Item> forSponsorList1) {
		this.forSponsorList1 = forSponsorList1;
	}

	public List<ItemFinish> getForSponsorHQLRejectList() {
		return forSponsorHQLRejectList;
	}

	public void setForSponsorHQLRejectList(List<ItemFinish> forSponsorHQLRejectList) {
		this.forSponsorHQLRejectList = forSponsorHQLRejectList;
	}

	public List<Object[]> getForLeadList() {
		return forLeadList;
	}

	public void setForLeadList(List<Object[]> forLeadList) {
		this.forLeadList = forLeadList;
	}

	public List<ItemFinish> getForSponsorList2() {
		return forSponsorList2;
	}

	public void setForSponsorList2(List<ItemFinish> forSponsorList2) {
		this.forSponsorList2 = forSponsorList2;
	}

	public List<Object[]> getForJJWList() {
		return forJJWList;
	}

	public void setForJJWList(List<Object[]> forJJWList) {
		this.forJJWList = forJJWList;
	}

	public List<ItemFinish> getForSponsorList3() {
		return forSponsorList3;
	}

	public void setForSponsorList3(List<ItemFinish> forSponsorList3) {
		this.forSponsorList3 = forSponsorList3;
	}

}