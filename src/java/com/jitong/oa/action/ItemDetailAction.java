package com.jitong.oa.action;

import java.util.List;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.BidMeetingRecord;
import com.jitong.workflow.domain.ItemAccept;
import com.jitong.workflow.domain.ItemApprove;
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.ItemFinish;
import com.jitong.workflow.domain.RecommendBidder;
import com.opensymphony.xwork2.Preparable;

public class ItemDetailAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemFinish itemFinish;
	private ItemBid bid;
	private List<ItemApprove> itemApproveList;
	private List<ItemApprove> mktSignApproveList;
	private List<ItemApprove> mktConfirmApproveList;
	private List<ItemApprove> rptApproveList;
	private List<ItemApprove> setupApproveList;
	private List<ItemApprove> zbdeptApproveList;
	private List<ItemApprove> jjwApproveList;
	private List<RecommendBidder> recommendBidderList;
	private List<ItemAccept> itemAcceptList;
	private List<ItemFinish> itemFinishtList;
	private List<BidMeetingRecord> bidMeetingRecordList;
	private List<ItemApprove> onfinishLeadApproveList;
	private List<ItemApprove> onfinishJJWApproveList;
	
	private List<ItemApprove> finish_zbdeptApproveList;
	private List<ItemApprove> finish_jjwApproveList;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			bid = (ItemBid) service.findBoById(ItemBid.class, item.getId());
		}
	}

	public String view() throws JTException {
		itemApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ITEM_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		mktSignApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_MKT_SIGN+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		mktConfirmApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_MKT_CONFIRM+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		rptApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_RPT_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		setupApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_SETUP_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		zbdeptApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ZBDEPT_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		jjwApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_JJW_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		
		recommendBidderList = (List<RecommendBidder>) service.queryByHql("from RecommendBidder bidder where bidder.itemId='" + item.getId() + "'");
		itemAcceptList = (List<ItemAccept>)service.queryByHql("from ItemAccept accept where accept.itemId='"+item.getId()+"'");
		itemFinishtList = (List<ItemFinish>) service.queryByHql("from ItemFinish finish where finish.item.id='" + item.getId() + "'");
		bidMeetingRecordList = (List<BidMeetingRecord>)service.queryByHql("from BidMeetingRecord record where record.itemId='"+item.getId()+"'");
		
		onfinishLeadApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ONFINISH_LEAD_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		onfinishJJWApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ONFINISH_JJW_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		return INPUT;
	}
    //------立项表----------
	public String setupReport() throws JTException {
		recommendBidderList = (List<RecommendBidder>) service.queryByHql("from RecommendBidder bidder where bidder.itemId='" + item.getId() + "'");
		return "setupreport";
	}
	public String finishReportList() throws JTException {
		itemFinishtList = (List<ItemFinish>) service.queryByHql("from ItemFinish finish where finish.item.id='" + item.getId() + "'");
		return "finishreportlist";
	}
	public String finishReport() throws JTException {
		if (itemFinish != null && itemFinish.getId() != null) {
			itemFinish=(ItemFinish)service.findBoById(ItemFinish.class, itemFinish.getId());
			finish_zbdeptApproveList=(List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ONFINISH_LEAD_APPROVE+"' and approve.itemId='" + itemFinish.getId() + "' order by approve.operationTime");
			finish_jjwApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ONFINISH_JJW_APPROVE+"' and approve.itemId='" + itemFinish.getId() + "' order by approve.operationTime");
		}
		return "finishreport";
	}
	
	
	
	//------结项表----------
	
	//-----竞争监督登记表-----
	public String biddersReport() throws JTException {
		recommendBidderList = (List<RecommendBidder>) service.queryByHql("from RecommendBidder bidder where bidder.itemId='" + item.getId() + "'");
		return "biddersreport";
	}
	public String exportBiddersReport() throws JTException {
		recommendBidderList = (List<RecommendBidder>) service.queryByHql("from RecommendBidder bidder where bidder.itemId='" + item.getId() + "'");
		session.put(JitongConstants.SESSION_OBJECT, recommendBidderList);
		return "exportExcel";
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

	public List<ItemAccept> getItemAcceptList() {
		return itemAcceptList;
	}

	public void setItemAcceptList(List<ItemAccept> itemAcceptList) {
		this.itemAcceptList = itemAcceptList;
	}


	public List<ItemFinish> getItemFinishtList() {
		return itemFinishtList;
	}

	public void setItemFinishtList(List<ItemFinish> itemFinishtList) {
		this.itemFinishtList = itemFinishtList;
	}

	public List<ItemApprove> getItemApproveList() {
		return itemApproveList;
	}

	public void setItemApproveList(List<ItemApprove> itemApproveList) {
		this.itemApproveList = itemApproveList;
	}

	public List<ItemApprove> getRptApproveList() {
		return rptApproveList;
	}

	public void setRptApproveList(List<ItemApprove> rptApproveList) {
		this.rptApproveList = rptApproveList;
	}

	public List<ItemApprove> getSetupApproveList() {
		return setupApproveList;
	}

	public void setSetupApproveList(List<ItemApprove> setupApproveList) {
		this.setupApproveList = setupApproveList;
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

	public List<BidMeetingRecord> getBidMeetingRecordList() {
		return bidMeetingRecordList;
	}

	public void setBidMeetingRecordList(List<BidMeetingRecord> bidMeetingRecordList) {
		this.bidMeetingRecordList = bidMeetingRecordList;
	}

	public List<ItemApprove> getMktSignApproveList() {
		return mktSignApproveList;
	}

	public void setMktSignApproveList(List<ItemApprove> mktSignApproveList) {
		this.mktSignApproveList = mktSignApproveList;
	}

	public List<ItemApprove> getMktConfirmApproveList() {
		return mktConfirmApproveList;
	}

	public void setMktConfirmApproveList(List<ItemApprove> mktConfirmApproveList) {
		this.mktConfirmApproveList = mktConfirmApproveList;
	}

	public List<ItemApprove> getOnfinishLeadApproveList() {
		return onfinishLeadApproveList;
	}

	public void setOnfinishLeadApproveList(List<ItemApprove> onfinishLeadApproveList) {
		this.onfinishLeadApproveList = onfinishLeadApproveList;
	}

	public List<ItemApprove> getOnfinishJJWApproveList() {
		return onfinishJJWApproveList;
	}

	public void setOnfinishJJWApproveList(List<ItemApprove> onfinishJJWApproveList) {
		this.onfinishJJWApproveList = onfinishJJWApproveList;
	}

	public ItemFinish getItemFinish() {
		return itemFinish;
	}

	public void setItemFinish(ItemFinish itemFinish) {
		this.itemFinish = itemFinish;
	}

	public List<ItemApprove> getFinish_zbdeptApproveList() {
		return finish_zbdeptApproveList;
	}

	public void setFinish_zbdeptApproveList(List<ItemApprove> finish_zbdeptApproveList) {
		this.finish_zbdeptApproveList = finish_zbdeptApproveList;
	}

	public List<ItemApprove> getFinish_jjwApproveList() {
		return finish_jjwApproveList;
	}

	public void setFinish_jjwApproveList(List<ItemApprove> finish_jjwApproveList) {
		this.finish_jjwApproveList = finish_jjwApproveList;
	}

}
