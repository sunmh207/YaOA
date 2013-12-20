package com.jitong.workflow.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.hibernate.Hibernate;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.BidMeetingRecord;
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.RecommendBidder;
import com.opensymphony.xwork2.Preparable;

public class OnBid6ZBDEPTMeetingRecordAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemBid bid;
	
	private File  bidWinInfo;//议标说明
	private String  bidWinInfoFileName;
	private File  contract;//招标文件
	private String  contractFileName;
	
	private List<RecommendBidder> recommendBidderList;
	private List<BidMeetingRecord> bidMeetingRecordList;
	
	///////////recommendBidder edit//////////////
	private RecommendBidder recommendBidder;
	private String recommendBidderDoc;
	private String recommendBidderDocFileName;
	
	///////////BidMeetingRecord edit////////////////////
	private BidMeetingRecord bidMeetingRecord;
	private String judgeSummary;
	private String judgeSummaryFileName;
	private String judgeChengnuoshu;
	private String judgeChengnuoshuFileName;
	private String meetingDoc;
	private String meetingDocFileName;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			bid=(ItemBid)service.findBoById(ItemBid.class, item.getId());
			bidMeetingRecordList = (List<BidMeetingRecord>)service.queryByHql("from BidMeetingRecord record where record.itemId='"+item.getId()+"'");
			recommendBidderList = (List<RecommendBidder>)service.queryByHql("from RecommendBidder bidder where bidder.itemId='"+item.getId()+"'");
			
		}
		if (recommendBidder != null && recommendBidder.getId() != null) {
			recommendBidder = (RecommendBidder)service.findBoById(RecommendBidder.class,recommendBidder.getId());
		}
	}

	public String input() throws JTException {
		//recommendBidderList = (List<RecommendBidder>)service.queryByHql("from RecommendBidder bidder where bidder.itemId='"+item.getId()+"'");
		//bidMeetingRecordList = (List<BidMeetingRecord>)service.queryByHql("from BidMeetingRecord record where record.itemId='"+item.getId()+"'");
		return INPUT;
	}
	
	public String submitBid() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		
		if(bidWinInfo!=null){
			String ext = FileTypeUtil.getExtensionByName(bidWinInfoFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return INPUT;
			}
			FileInputStream bidNegotiationIS = new FileInputStream(bidWinInfo);
			bid.setBidWinInfo(Hibernate.createBlob(bidNegotiationIS));
			bid.setBidWinInfoExt(ext);
		}
		
		if(contract!=null){
			String ext = FileTypeUtil.getExtensionByName(contractFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return INPUT;
			}
			FileInputStream bidDocIS = new FileInputStream(contract);
			bid.setContract(Hibernate.createBlob(bidDocIS));
			bid.setContractExt(ext);
		}
		service.updateBo(bid);
		
		this.addActionMessage("保存成功");
		return INPUT;
	}
	
	public String finishBid() throws Exception {
		item.setStatus(Item.STATUS_10_ON_ACCEPT);
		service.updateItem(item);
		return SUCCESS;
	}
	
	
	public String recommendBidderInput(){
		return "recommendbidderinput";
	}
	public String recommendBidderSave()throws Exception{
			
		if(!StringUtil.isEmpty(recommendBidderDoc)){
			if(StringUtil.isEmpty(recommendBidderDocFileName)){
				this.addActionError("上传文件不能为空");
				return "recommendbidderinput";				
			}
			String ext = FileTypeUtil.getExtensionByName(recommendBidderDocFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return "recommendbidderinput";
			}
			
			FileInputStream is = new FileInputStream(recommendBidderDoc);
			recommendBidder.setDoc(Hibernate.createBlob(is));
			recommendBidder.setItemId(item.getId());
			recommendBidder.setDocExt(ext);
		
		}
		service.updateBo(recommendBidder);
		return input();
	}
	
	
	public String editBidMeetingRecord()throws Exception{
		if (bidMeetingRecord != null && bidMeetingRecord.getId() != null) {
			bidMeetingRecord = (BidMeetingRecord)service.findBoById(BidMeetingRecord.class,bidMeetingRecord.getId());
		}
		return "bidmeetingrecordinput";
	}
	public String saveBidMeetingRecord()throws Exception{
		if(!StringUtil.isEmpty(judgeSummary)){
			if(StringUtil.isEmpty(judgeSummaryFileName)){
				this.addActionError("上传文件不能为空");
				return "bidmeetingrecordinput";				
			}
			String ext = FileTypeUtil.getExtensionByName(judgeSummaryFileName);
			if(ext!=null&&FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return "bidmeetingrecordinput";
			}
			
			FileInputStream is = new FileInputStream(judgeSummary);
			bidMeetingRecord.setJudgeSummary(Hibernate.createBlob(is));
			bidMeetingRecord.setJudgeSummaryExt(ext);
		}
		if(!StringUtil.isEmpty(judgeChengnuoshu)){
			if(StringUtil.isEmpty(judgeChengnuoshuFileName)){
				this.addActionError("上传文件不能为空");
				return "bidmeetingrecordinput";				
			}
			String ext = FileTypeUtil.getExtensionByName(judgeChengnuoshuFileName);
			if(ext!=null&&FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return "bidmeetingrecordinput";
			}
			
			FileInputStream is = new FileInputStream(judgeChengnuoshu);
			bidMeetingRecord.setJudgeChengnuoshu(Hibernate.createBlob(is));
			bidMeetingRecord.setJudgeChengnuoshuExt(ext);
		}
		if(!StringUtil.isEmpty(meetingDoc)){
			if(StringUtil.isEmpty(meetingDocFileName)){
				this.addActionError("上传文件不能为空");
				return "bidmeetingrecordinput";				
			}
			String ext = FileTypeUtil.getExtensionByName(meetingDocFileName);
			if(ext!=null&&FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return "bidmeetingrecordinput";
			}
			
			FileInputStream is = new FileInputStream(meetingDoc);
			bidMeetingRecord.setMeetingNote(Hibernate.createBlob(is));
			bidMeetingRecord.setMeetingNoteExt(ext);
		}
		bidMeetingRecord.setItemId(item.getId());
		
		if(StringUtil.isEmpty(bidMeetingRecord.getId())){
			service.createBo(bidMeetingRecord);
		}else{
			service.updateBo(bidMeetingRecord);
		}
		
		for (RecommendBidder bidder: recommendBidderList) {
			service.updateBo(bidder);
		}
		this.addActionMessage("保存成功");
		return editBidMeetingRecord();
	}
	public String deleteBidMeetingRecord()throws Exception{
		service.deleteBo(BidMeetingRecord.class,bidMeetingRecord.getId());
		return input();
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

	public File getBidWinInfo() {
		return bidWinInfo;
	}

	public void setBidWinInfo(File bidWinInfo) {
		this.bidWinInfo = bidWinInfo;
	}

	public String getBidWinInfoFileName() {
		return bidWinInfoFileName;
	}

	public void setBidWinInfoFileName(String bidWinInfoFileName) {
		this.bidWinInfoFileName = bidWinInfoFileName;
	}

	public File getContract() {
		return contract;
	}

	public void setContract(File contract) {
		this.contract = contract;
	}

	public String getContractFileName() {
		return contractFileName;
	}

	public void setContractFileName(String contractFileName) {
		this.contractFileName = contractFileName;
	}

	public List<RecommendBidder> getRecommendBidderList() {
		return recommendBidderList;
	}

	public void setRecommendBidderList(List<RecommendBidder> recommendBidderList) {
		this.recommendBidderList = recommendBidderList;
	}

	public RecommendBidder getRecommendBidder() {
		return recommendBidder;
	}

	public void setRecommendBidder(RecommendBidder recommendBidder) {
		this.recommendBidder = recommendBidder;
	}

	public String getRecommendBidderDoc() {
		return recommendBidderDoc;
	}

	public void setRecommendBidderDoc(String recommendBidderDoc) {
		this.recommendBidderDoc = recommendBidderDoc;
	}

	public String getRecommendBidderDocFileName() {
		return recommendBidderDocFileName;
	}

	public void setRecommendBidderDocFileName(String recommendBidderDocFileName) {
		this.recommendBidderDocFileName = recommendBidderDocFileName;
	}

	public List<BidMeetingRecord> getBidMeetingRecordList() {
		return bidMeetingRecordList;
	}

	public void setBidMeetingRecordList(List<BidMeetingRecord> bidMeetingRecordList) {
		this.bidMeetingRecordList = bidMeetingRecordList;
	}

	public BidMeetingRecord getBidMeetingRecord() {
		return bidMeetingRecord;
	}

	public void setBidMeetingRecord(BidMeetingRecord bidMeetingRecord) {
		this.bidMeetingRecord = bidMeetingRecord;
	}

	public String getMeetingDoc() {
		return meetingDoc;
	}

	public void setMeetingDoc(String meetingDoc) {
		this.meetingDoc = meetingDoc;
	}

	public String getMeetingDocFileName() {
		return meetingDocFileName;
	}

	public void setMeetingDocFileName(String meetingDocFileName) {
		this.meetingDocFileName = meetingDocFileName;
	}

	public String getJudgeSummary() {
		return judgeSummary;
	}

	public void setJudgeSummary(String judgeSummary) {
		this.judgeSummary = judgeSummary;
	}

	public String getJudgeSummaryFileName() {
		return judgeSummaryFileName;
	}

	public void setJudgeSummaryFileName(String judgeSummaryFileName) {
		this.judgeSummaryFileName = judgeSummaryFileName;
	}

	public String getJudgeChengnuoshu() {
		return judgeChengnuoshu;
	}

	public void setJudgeChengnuoshu(String judgeChengnuoshu) {
		this.judgeChengnuoshu = judgeChengnuoshu;
	}

	public String getJudgeChengnuoshuFileName() {
		return judgeChengnuoshuFileName;
	}

	public void setJudgeChengnuoshuFileName(String judgeChengnuoshuFileName) {
		this.judgeChengnuoshuFileName = judgeChengnuoshuFileName;
	}

}
