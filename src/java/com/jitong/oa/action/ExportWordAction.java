package com.jitong.oa.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.BidMeetingRecord;
import com.jitong.workflow.domain.ItemAccept;
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.RecommendBidder;
import com.opensymphony.xwork2.Preparable;

public class ExportWordAction extends JITActionBase implements Preparable {
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
			bid = (ItemBid) service.findBoById(ItemBid.class, item.getId());
		}
		if (bid != null && bid.getItemId() != null) {
			bid = (ItemBid) service.findBoById(ItemBid.class, bid.getItemId());
		}
		
		
	}

	/**
	 * 导出立项表
	 * 
	 * @return
	 */
	public String exportSetup() throws JTException{
		recommendBidderList = (List<RecommendBidder>) service.queryByHql("from RecommendBidder bidder where bidder.itemId='" + item.getId() + "'");
		
		Map dataMap = new HashMap();
		String applyDate="";
		try{
			applyDate=DateUtil.convertDateFormat(bid.getApplyDate(), "yyyy-MM-dd", "yyyy年MM月d日");
		}catch(Exception e){
			e.printStackTrace();
		}
		dataMap.put("hostDept", bid.getHostDept());
		dataMap.put("applyDate", applyDate);
		dataMap.put("planAmount", bid.getPlanAmount());
		dataMap.put("bidItemName", bid.getBidItemName());
		dataMap.put("refNumber", bid.getRefNumber());
		dataMap.put("moneySource", bid.getMoneySource());
		dataMap.put("itemSummary", bid.getItemSummary());
		dataMap.put("planBidDate", bid.getPlanBidDate());
		dataMap.put("agentPersonPhone", bid.getAgentPersonPhone());
		
		String recommendBidders="";
		if(recommendBidderList!=null){
			for(RecommendBidder rb:recommendBidderList){
				recommendBidders+=rb.getName()+"<br>";
			}
		}
		dataMap.put("recommendBidderList", recommendBidders);
		
		dataMap.put("hostDeptComments", bid.getHostDeptComments());
		dataMap.put("responsiblePerson", bid.getResponsiblePerson());
		dataMap.put("bidDate", bid.getBidDate());
		dataMap.put("bidType", bid.getBidType());
		dataMap.put("zhaobiaoDeptAgentPerson", bid.getZhaobiaoDeptAgentPerson());
		dataMap.put("zhaobiaoDeptComments", bid.getZhaobiaoDeptComments());
		dataMap.put("zhaobiaoDeptResponsiblePerson", bid.getZhaobiaoDeptResponsiblePerson());
		dataMap.put("jjwDeptComments", bid.getJjwDeptComments());
		dataMap.put("jjwDeptResponsiblePerson", bid.getJjwDeptResponsiblePerson());
		
		session.put("dataMap", dataMap);
		session.put("template", "file"+File.separator + "setup_template.mht");
		return "export";
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



}