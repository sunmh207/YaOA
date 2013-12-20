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
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.RecommendBidder;
import com.opensymphony.xwork2.Preparable;
/**
 * 招标管理部门审批
 * @author stanley.sun
 *
 */
public class OnBid5Action extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemBid bid;
	private List<RecommendBidder> recommendBidderList;
	private List<ItemApprove> zbdeptApproveList;
	private List<ItemApprove> jjwApproveList;

	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			bid=(ItemBid)service.findBoById(ItemBid.class, item.getId());
		}
	}

	public String input() throws JTException {
		recommendBidderList = (List<RecommendBidder>)service.queryByHql("from RecommendBidder bidder where bidder.itemId='"+item.getId()+"'");
		zbdeptApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ZBDEPT_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		jjwApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_JJW_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		return INPUT;
	}

	public String submit() {
		try {
			User u = this.getLoginUser();
			if (u == null) {
				this.addActionError("当前用户会话过期");
				return INPUT;
			}
				
			//交给招标部门填写会议记录
			String idString = request.getParameter("useridStr");
			User user =SysCache.interpertUser(idString);
			String[] ids = StringUtil.parseString2Array(idString, ",");
			String names="";
			for (String id : ids) {
				names+=""+SysCache.interpertUserName(id);
			}
			bid.setZbdeptMeetingRecorderId(idString);
			bid.setZbdeptMeetingRecorderName(names);
			service.updateBo(bid);
			item.setStatus(Item.STATUS_9_ON_BID_PENDING_ZBDEPT_MEETINGNOTE);
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


	public List<ItemApprove> getZbdeptApproveList() {
		return zbdeptApproveList;
	}

	public void setZbdeptApproveList(List<ItemApprove> zbdeptApproveList) {
		this.zbdeptApproveList = zbdeptApproveList;
	}

	public List<ItemApprove> getJjwApproveList() {
		return jjwApproveList;
	}

	public void setJjwApproveList(List<ItemApprove> jjwApproveList) {
		this.jjwApproveList = jjwApproveList;
	}

	
}
