package com.jitong.workflow.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
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
 * 活动立项
 * @author stanley.sun
 *
 */
public class OnSetup_SetupAction extends JITActionBase implements Preparable {
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
	
	private List<RecommendBidder> recommendBidderList;
	private List<ItemApprove> setupApproveList;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null&&!"".equals(item.getId())) {
			item = service.findItem(item.getId());
			bid = (ItemBid) service.findBoById(ItemBid.class, item.getId());
		}
	}

	public String input() throws JTException {
		recommendBidderList = (List<RecommendBidder>)service.queryByHql("from RecommendBidder bidder where bidder.itemId='"+item.getId()+"'");
		setupApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_SETUP_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		return INPUT;
	}
	
	public String approve() throws Exception {
		item.setStatus(Item.STATUS_9_ON_BID);
		service.updateItem(item);
		return SUCCESS;
	}
	public String reject() throws Exception {
		item.setStatus(Item.STATUS_7_SETUP_REJECT);
		service.updateItem(item);
		return SUCCESS;
	}
	
	public String submitSetup() throws Exception {
		try{
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		
		//ItemBid bid = item.getBid();
		if(bid==null){
			bid=new ItemBid();
		}
		
		boolean existing=true;
		if(StringUtil.isEmpty(bid.getItemId())){
			existing=false;
		}
		//bid.setItem(item);
		bid.setItemId(item.getId());
		
		if(bidNegotiation!=null){
			String ext = FileTypeUtil.getExtensionByName(bidNegotiationFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return INPUT;
			}
			FileInputStream bidNegotiationIS = new FileInputStream(bidNegotiation);
			bid.setBidNegotiation(Hibernate.createBlob(bidNegotiationIS));
			bid.setBidNegotiationExt(ext);
		}
		
		if(bidDoc!=null){
			String ext = FileTypeUtil.getExtensionByName(bidDocFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return INPUT;
			}
			FileInputStream bidDocIS = new FileInputStream(bidDoc);
			bid.setBidDoc(Hibernate.createBlob(bidDocIS));
			bid.setBidDocExt(ext);
		}
		
		//Recommend Bidders
		for (int i = 0; bidderName!=null && i < bidderName.length; i++) {
			if(StringUtil.isEmpty(bidderName[i])){
				continue;
				/*this.addActionError("推荐投标单位名称不能为空");
				return input();	*/		
			}
			
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
		if(existing){
			service.updateBo(bid);
		}else{
			service.createBo(bid);
		}
		///////////ITEM/////////////////
		item.setStatus(Item.STATUS_7_SETUP_PENDING_APPROVE);
		service.updateItem(item);
		
		///////////ItemApprove//////////////////
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (String id : ids) {
			//check existing
			String hql="from ItemApprove approve where approve.type='"+ItemApprove.TYPE_SETUP_APPROVE+"' " +
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
				itemApprove.setType(ItemApprove.TYPE_SETUP_APPROVE);
				itemApprove.setStatus(ItemApprove.STATUS_PENDING);
				service.createBo(itemApprove);
			}
		}
		
		return SUCCESS;
		}catch(Exception e){
			this.addActionError(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String submit2ZBDept() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		
		boolean existing=true;
		if(bid==null){
			bid=new ItemBid();
			existing=false;
		}
		//bid.setItem(item);
		bid.setItemId(item.getId());
		if(bidNegotiation!=null){
			String ext = FileTypeUtil.getExtensionByName(bidNegotiationFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return INPUT;
			}
			FileInputStream bidNegotiationIS = new FileInputStream(bidNegotiation);
			bid.setBidNegotiation(Hibernate.createBlob(bidNegotiationIS));
			bid.setBidNegotiationExt(ext);
		}
		
		if(bidDoc!=null){
			String ext = FileTypeUtil.getExtensionByName(bidDocFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return INPUT;
			}
			FileInputStream bidDocIS = new FileInputStream(bidDoc);
			bid.setBidDoc(Hibernate.createBlob(bidDocIS));
			bid.setBidDocExt(ext);
		}
		
		//Recommend Bidders
		for (int i = 0; bidderDoc!=null && i < bidderDoc.length; i++) {
			String docFileName = bidderDocFileName[i];
			if(StringUtil.isEmpty(docFileName)){
				this.addActionError("上传文件不能为空");
				return INPUT;				
			}
			String name = bidderName[i];
			if(StringUtil.isEmpty(name)){
				this.addActionError("推荐投标单位名称不能为空");
				return INPUT;				
			}
			String ext = FileTypeUtil.getExtensionByName(bidderDocFileName[i]);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return INPUT;
			}
			
			RecommendBidder bidder = new RecommendBidder();
			FileInputStream is = new FileInputStream(bidderDoc[i]);
			bidder.setName(name);
			bidder.setDoc(Hibernate.createBlob(is));
			bidder.setItemId(item.getId());
			bidder.setDocExt(ext);
			service.createBo(bidder);
		}
		if(existing){
			service.updateBo(bid);
		}else{
			service.createBo(bid);
		}
		///////////ITEM/////////////////
		item.setStatus(Item.STATUS_9_ON_BID_PENDING_ZBDEPT_APPROVE);
		service.updateItem(item);
		
		///////////ItemApprove//////////////////
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (String id : ids) {
			ItemApprove itemApprove = new ItemApprove();
			itemApprove.setItemId(item.getId());
			
			itemApprove.setApproverId(id);
			itemApprove.setApproverName(SysCache.interpertUserName(id));
			itemApprove.setType(ItemApprove.TYPE_SETUP_APPROVE);
			itemApprove.setStatus(ItemApprove.STATUS_PENDING);
			service.createBo(itemApprove);
		}
		
		return SUCCESS;
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		User u = this.getLoginUser();
		if(u==null){
			throw new JTException("用户超时",this.getClass());
		}
		String hql = "from com.jitong.oa.domain.Item me where me.status in('"+Item.STATUS_7_SETUP+"','"+Item.STATUS_7_SETUP_REJECT+"','"+Item.STATUS_7_SETUP_PENDING_APPROVE+"','"+Item.STATUS_6_MKT_RPT_PENDING_APPROVE+"') " +
				"and me.requesterId='"+u.getId()+"'";
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

	public List<ItemApprove> getSetupApproveList() {
		return setupApproveList;
	}

	public void setSetupApproveList(List<ItemApprove> setupApproveList) {
		this.setupApproveList = setupApproveList;
	}

	
}
