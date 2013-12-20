package com.jitong.workflow.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.RecommendBidder;
import com.opensymphony.xwork2.Preparable;
/**
 * 项目验收
 * @author stanley.sun
 *
 */
public class ItemFinishedAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemBid bid;
	
	private List<RecommendBidder> recommendBidderList;

	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			bid=(ItemBid)service.findBoById(ItemBid.class, item.getId());
		}
	}

	public String view() throws JTException {
		recommendBidderList = (List<RecommendBidder>)service.queryByHql("from RecommendBidder bidder where bidder.itemId='"+item.getId()+"'");
		return INPUT;
	}
	public String downloadReport() {
		session.put("BLOB", item.getSurveyReportBody());
		session.put("EXT", item.getSurveyReportBodyExt());
		return "download";
	}
	public String downloadBidWinInfo() {
		session.put("BLOB", bid.getBidWinInfo());
		session.put("EXT", bid.getBidWinInfoExt());
		return "download";
	}
	public String downloadContract() {
		session.put("BLOB", bid.getContract());
		session.put("EXT", bid.getContractExt());
		return "download";
	}
	public String downloadAcceptInfo() {
		session.put("BLOB", bid.getItemAcceptInfo());
		session.put("EXT", bid.getItemAcceptInfoExt());
		return "download";
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		String hql = "from Item item  where item.requesterId='"+u.getId()+"' and item.status in('" + Item.STATUS_13_CLOSE+"')";
		return hql;
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

}
