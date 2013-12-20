package com.jitong.workflow.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.FileTypeUtil;
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
 * 招标文件
 * @author stanley.sun
 *
 */
public class OnBid1_BidDocAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemBid bid;
	
	
	private File  bidNegotiation;//议标说明
	private String  bidNegotiationFileName;
	private File  bidDoc;//招标文件
	private String  bidDocFileName;
	
	private String[] bidderName;
	private File[]  bidderDoc;
	private String[] bidderDocFileName;
	
	List<Item> pendingBidList;
	
	private List<RecommendBidder> recommendBidderList;

	///////////recommendBidder edit//////////////
	private RecommendBidder recommendBidder;
	private String recommendBidderDoc;
	private String recommendBidderDocFileName;
	
	/////////////////////////////
	private List<ItemApprove> zbdeptApproveList;
	private List<ItemApprove> jjwApproveList;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			bid = (ItemBid) service.findBoById(ItemBid.class, item.getId());
		}
		if (recommendBidder != null && recommendBidder.getId() != null) {
			recommendBidder = (RecommendBidder)service.findBoById(RecommendBidder.class,recommendBidder.getId());
		}
	}

	public String input() throws JTException {
		recommendBidderList = (List<RecommendBidder>)service.queryByHql("from RecommendBidder bidder where bidder.itemId='"+item.getId()+"'");
		zbdeptApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ZBDEPT_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		jjwApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_JJW_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		return INPUT;
	}
	

	public String submitBid() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		
		boolean existing=true;
		if(bid==null||StringUtil.isEmpty(bid.getItemId())){
			bid=new ItemBid();
			existing=false;
		}
		
		
		if(bidNegotiation!=null){
			String ext = FileTypeUtil.getExtensionByName(bidNegotiationFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return input();
			}
			FileInputStream bidNegotiationIS = new FileInputStream(bidNegotiation);
			bid.setBidNegotiation(Hibernate.createBlob(bidNegotiationIS));
			bid.setBidNegotiationExt(ext);
		}
		
		if(bidDoc!=null){
			String ext = FileTypeUtil.getExtensionByName(bidDocFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return input();
			}
			FileInputStream bidDocIS = new FileInputStream(bidDoc);
			bid.setBidDoc(Hibernate.createBlob(bidDocIS));
			bid.setBidDocExt(ext);
		}
		if(existing){
			service.updateBo(bid);
		}else{
			bid.setItemId(item.getId());
			service.createBo(bid);
		}
		//Recommend Bidders
		//for (int i = 0; bidderDoc!=null && i < bidderDoc.length; i++) {
		for (int i = 0; bidderName!=null && i < bidderName.length; i++) {
			if(StringUtil.isEmpty(bidderName[i])){
				continue;
			/*	this.addActionError("推荐投标单位名称不能为空");
				return input();	*/		
			}
			
			//String docFileName = bidderDocFileName[i];
			/*if(StringUtil.isEmpty(docFileName)){
				this.addActionError("上传文件不能为空");
				return INPUT;				
			}*/
			RecommendBidder bidder = new RecommendBidder();
			bidder.setName(bidderName[i]);
			bidder.setItemId(item.getId());
			
			if(bidderDocFileName!=null&&bidderDocFileName.length>i&&"empty"!=bidderDocFileName[i]){
				String ext = FileTypeUtil.getExtensionByName(bidderDocFileName[i]);
				if(FileTypeUtil.isForbiddenUploadType(ext)){
					this.addActionError("禁止上传 "+ext+" 文件");
					return INPUT;
				}
				FileInputStream is = new FileInputStream(bidderDoc[i]);
				bidder.setDoc(Hibernate.createBlob(is));
				bidder.setDocExt(ext);
			}
			
			service.createBo(bidder);
		}
		
		
		item.setStatus(Item.STATUS_9_ON_BID_PENDING_ZBDEPT_APPROVE);
		service.updateItem(item);
		
		//Add Approve record
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (String id : ids) {
			//check existing
			String hql="from ItemApprove approve where approve.type='"+ItemApprove.TYPE_ZBDEPT_APPROVE+"' " +
					" and approve.itemId='" + item.getId() + "' " +
					" and approve.status='"+ItemApprove.STATUS_PENDING+"'"+
					" and approve.approverId='" + id + "' ";
			
			List approveList =  service.queryByHql(hql);
			ItemApprove itemApprove = null;
			if(approveList.isEmpty()){
				itemApprove = new ItemApprove();
				itemApprove.setItemId(item.getId());
				itemApprove.setApproverId(id);
				itemApprove.setApproverName(SysCache.interpertUserName(id));
				itemApprove.setType(ItemApprove.TYPE_ZBDEPT_APPROVE);
				itemApprove.setStatus(ItemApprove.STATUS_PENDING);
				service.createBo(itemApprove);
			}
		}
		
		return SUCCESS;
	}
	public String list() throws JTException {
		User u = this.getLoginUser();
		if (u == null) {
			this.addActionError("当前用户会话过期");
			return SUCCESS;
		}
		String pendingBidHQL = "select item from Item item  where item.requesterId='"+u.getId()+"' and item.status in ('"+Item.STATUS_9_ON_BID+"','"+Item.STATUS_9_ON_BID_ZBDEPT_REJECT+"')";
		pendingBidList = service.queryByHql(pendingBidHQL);
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
	
	
	public String deleteRecommendBidder()throws Exception{
		service.deleteBo(RecommendBidder.class, recommendBidder.getId());
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

	public String[] getBidderName() {
		return bidderName;
	}

	public void setBidderName(String[] bidderName) {
		this.bidderName = bidderName;
	}

	public File[] getBidderDoc() {
		return bidderDoc;
	}

	public void setBidderDoc(File[] bidderDoc) {
		this.bidderDoc = bidderDoc;
	}

	public String[] getBidderDocFileName() {
		return bidderDocFileName;
	}

	public void setBidderDocFileName(String[] bidderDocFileName) {
		this.bidderDocFileName = bidderDocFileName;
	}

	public File getBidNegotiation() {
		return bidNegotiation;
	}

	public void setBidNegotiation(File bidNegotiation) {
		this.bidNegotiation = bidNegotiation;
	}

	public File getBidDoc() {
		return bidDoc;
	}

	public void setBidDoc(File bidDoc) {
		this.bidDoc = bidDoc;
	}

	public List<RecommendBidder> getRecommendBidderList() {
		return recommendBidderList;
	}

	public void setRecommendBidderList(List<RecommendBidder> recommendBidderList) {
		this.recommendBidderList = recommendBidderList;
	}

	public String getBidNegotiationFileName() {
		return bidNegotiationFileName;
	}

	public void setBidNegotiationFileName(String bidNegotiationFileName) {
		this.bidNegotiationFileName = bidNegotiationFileName;
	}

	public String getBidDocFileName() {
		return bidDocFileName;
	}

	public void setBidDocFileName(String bidDocFileName) {
		this.bidDocFileName = bidDocFileName;
	}

	public List<Item> getPendingBidList() {
		return pendingBidList;
	}

	public void setPendingBidList(List<Item> pendingBidList) {
		this.pendingBidList = pendingBidList;
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
