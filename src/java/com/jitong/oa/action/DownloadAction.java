package com.jitong.oa.action;

import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.BidMeetingRecord;
import com.jitong.workflow.domain.ItemAccept;
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.RecommendBidder;
import com.opensymphony.xwork2.Preparable;

public class DownloadAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemBid bid;
	private ItemAccept itemAccept;
	private RecommendBidder recommendBidder;
	private BidMeetingRecord bidMeetingRecord;

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
		if (itemAccept != null && itemAccept.getId() != null) {
			itemAccept = (ItemAccept) service.findBoById(ItemAccept.class, itemAccept.getId());
		}
		if (recommendBidder != null && recommendBidder.getId() != null) {
			recommendBidder = (RecommendBidder) service.findBoById(RecommendBidder.class, recommendBidder.getId());
		}
		if (bidMeetingRecord != null && bidMeetingRecord.getId() != null) {
			bidMeetingRecord = (BidMeetingRecord) service.findBoById(BidMeetingRecord.class, bidMeetingRecord.getId());
		}
	}

	/**
	 * 下载调研报告
	 * 
	 * @return
	 */
	public String downloadReport() {
		session.put("BLOB", item.getSurveyReportBody());
		session.put("EXT", item.getSurveyReportBodyExt());
		return "download";
	}

	/**
	 * 下载验收文档
	 * 
	 * @return
	 */
	public String downloadItemAcceptInfo() {
		session.put("BLOB", itemAccept.getAcceptInfo());
		session.put("EXT", itemAccept.getAcceptInfoExt());
		return "download";
	}

	/**
	 * 下载招标文件
	 * 
	 * @return
	 */
	public String downloadBidDoc() {
		session.put("BLOB", bid.getBidDoc());
		session.put("EXT", bid.getBidDocExt());
		return "download";
	}
	/**
	 * 下载议标说明
	 * @return
	 */
	public String downloadBidNegotiation() {
		session.put("BLOB", bid.getBidNegotiation());
		session.put("EXT", bid.getBidNegotiationExt());
		return "download";
	}

	
	public String downloadBidWinInfo() {
		session.put("BLOB", bid.getBidWinInfo());
		session.put("EXT", bid.getBidWinInfoExt());
		return "download";
	}

	public String downloadBidContract() {
		session.put("BLOB", bid.getContract());
		session.put("EXT", bid.getContractExt());
		return "download";
	}

	/**
	 * 下载推荐投标单位的文档
	 * 
	 * @return
	 */
	public String downloadRecommendBidderDoc() {
		session.put("BLOB", recommendBidder.getDoc());
		session.put("EXT", recommendBidder.getDocExt());
		return "download";
	}
	public String downloadBidMeetingRecordNote() {
		session.put("BLOB", bidMeetingRecord.getMeetingNote());
		session.put("EXT", bidMeetingRecord.getMeetingNoteExt());
		return "download";
	}
	public String downloadMeetingRecord_JudgeSummary() {
		session.put("BLOB", bidMeetingRecord.getJudgeSummary());
		session.put("EXT", bidMeetingRecord.getJudgeSummaryExt());
		return "download";
	}
	public String downloadMeetingRecord_JudgeChengnuoshu() {
		session.put("BLOB", bidMeetingRecord.getJudgeChengnuoshu());
		session.put("EXT", bidMeetingRecord.getJudgeChengnuoshuExt());
		return "download";
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

	public ItemAccept getItemAccept() {
		return itemAccept;
	}

	public void setItemAccept(ItemAccept itemAccept) {
		this.itemAccept = itemAccept;
	}

	public RecommendBidder getRecommendBidder() {
		return recommendBidder;
	}

	public void setRecommendBidder(RecommendBidder recommendBidder) {
		this.recommendBidder = recommendBidder;
	}

	public BidMeetingRecord getBidMeetingRecord() {
		return bidMeetingRecord;
	}

	public void setBidMeetingRecord(BidMeetingRecord bidMeetingRecord) {
		this.bidMeetingRecord = bidMeetingRecord;
	}

}