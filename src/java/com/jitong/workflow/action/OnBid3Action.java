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
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.RecommendBidder;
import com.opensymphony.xwork2.Preparable;
/**
 * 招标管理部门审批
 * @author stanley.sun
 *
 */
public class OnBid3Action extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemBid bid;
	private ItemApprove itemApprove;
	private List<RecommendBidder> recommendBidderList;
	private List<ItemApprove> zbdeptApproveList;

	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			bid=(ItemBid)service.findBoById(ItemBid.class, item.getId());
		}
		if (itemApprove != null && itemApprove.getId() != null) {
			itemApprove = (ItemApprove) service.findBoById(ItemApprove.class, itemApprove.getId());
		}
	}

	public String input() throws JTException {
		recommendBidderList = (List<RecommendBidder>)service.queryByHql("from RecommendBidder bidder where bidder.itemId='"+item.getId()+"'");
		zbdeptApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ZBDEPT_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		return INPUT;
	}

	public String submit() {
		try {
			User u = this.getLoginUser();
			if (u == null) {
				this.addActionError("当前用户会话过期");
				return INPUT;
			}
				
			//交给纪检委审批
			String idString = request.getParameter("useridStr");
			String[] ids = StringUtil.parseString2Array(idString, ",");
			for (String id : ids) {
				//check existing
				String jjwhql="from ItemApprove approve where approve.type='"+ItemApprove.TYPE_JJW_APPROVE+"' " +
						" and approve.itemId='" + item.getId() + "' " +
						" and approve.status='"+ItemApprove.STATUS_PENDING+"'"+
						" and approve.approverId='" + id + "' ";					
				List approveList =  service.queryByHql(jjwhql);
				ItemApprove itemApprove = null;
				if(approveList.isEmpty()){
					itemApprove = new ItemApprove();
					itemApprove.setItemId(item.getId());
					itemApprove.setApproverId(id);
					itemApprove.setApproverName(SysCache.interpertUserName(id));
					itemApprove.setType(ItemApprove.TYPE_JJW_APPROVE);
					itemApprove.setStatus(ItemApprove.STATUS_PENDING);
					service.createBo(itemApprove);
				}
			}
			item.setStatus(Item.STATUS_9_ON_BID_PENDING_JJW_APPROVE);
			service.updateBo(item);
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemBid getBid() {
		return bid;
	}

	public void setBid(ItemBid bid) {
		this.bid = bid;
	}

	public List<RecommendBidder> getRecommendBidderList() {
		return recommendBidderList;
	}

	public void setRecommendBidderList(List<RecommendBidder> recommendBidderList) {
		this.recommendBidderList = recommendBidderList;
	}

	public ItemApprove getItemApprove() {
		return itemApprove;
	}

	public void setItemApprove(ItemApprove itemApprove) {
		this.itemApprove = itemApprove;
	}

	public List<ItemApprove> getZbdeptApproveList() {
		return zbdeptApproveList;
	}

	public void setZbdeptApproveList(List<ItemApprove> zbdeptApproveList) {
		this.zbdeptApproveList = zbdeptApproveList;
	}

	
}
