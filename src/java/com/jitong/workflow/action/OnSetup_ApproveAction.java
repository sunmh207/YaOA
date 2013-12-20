package com.jitong.workflow.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemApprove;
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.RecommendBidder;
import com.opensymphony.xwork2.Preparable;

public class OnSetup_ApproveAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemBid bid;
	private ItemApprove itemApprove;
	
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
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			//bid=item.getBid();
			bid = (ItemBid)service.findBoById(ItemBid.class, item.getId());
		}
		if (itemApprove != null && itemApprove.getId() != null) {
			itemApprove = (ItemApprove) service.findBoById(ItemApprove.class, itemApprove.getId());
		}
	}

	public String input() throws JTException {
		recommendBidderList = (List<RecommendBidder>)service.queryByHql("from RecommendBidder bidder where bidder.itemId='"+item.getId()+"'");
		setupApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_SETUP_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		return INPUT;
	}
	public String downloadReport() {
		session.put("BLOB", item.getSurveyReportBody());
		session.put("EXT", item.getSurveyReportBodyExt());
		return "download";
	}

	public String approve() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			this.addActionError("当前用户会话过期");
			return INPUT;
		}
		
		itemApprove.setApproverId(u.getId());
		itemApprove.setApproverName(u.getName());
		itemApprove.setItemId(item.getId());
		itemApprove.setOperationTime(DateUtil.getCurrentTime());
		itemApprove.setType(ItemApprove.TYPE_SETUP_APPROVE);
		itemApprove.setStatus(ItemApprove.STATUS_APPROVED);
		itemApprove.setComments(bid.getHostDeptComments());
		service.updateBo(itemApprove);
		
		String hql="select count(*) as cnt from ItemApprove approve where approve.type='"+ItemApprove.TYPE_SETUP_APPROVE+"' and approve.itemId='" + item.getId() + "' and approve.status='"+ItemApprove.STATUS_PENDING+"'";
		long cnt = (java.lang.Long) service.queryByHql(hql).get(0);
		
		if(cnt==0){//多个审批都通过
			item.setStatus(Item.STATUS_9_ON_BID);
			service.updateItem(item);
		}
		
		service.updateBo(bid);
		return SUCCESS;
	}
	public String reject() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			this.addActionError("当前用户会话过期");
			return INPUT;
		}
		
		itemApprove.setApproverId(u.getId());
		itemApprove.setApproverName(u.getName());
		itemApprove.setItemId(item.getId());
		itemApprove.setOperationTime(DateUtil.getCurrentTime());
		itemApprove.setType(ItemApprove.TYPE_SETUP_APPROVE);
		itemApprove.setStatus(ItemApprove.STATUS_REJECTED);
		itemApprove.setComments(bid.getHostDeptComments());
		service.updateBo(itemApprove);
		
		item.setStatus(Item.STATUS_7_SETUP_REJECT);
		service.updateItem(item);
		service.updateBo(bid);
		return SUCCESS;
	}

	/*public String getListHQL(ArrayList<Object> params) throws JTException {
		User u = this.getLoginUser();
		if(u==null){
			throw new JTException("用户超时",this.getClass());
		}
		String hql = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
				"item.status in ('"+Item.STATUS_7_SETUP_REJECT+"','" + Item.STATUS_7_SETUP_PENDING_APPROVE + "','"+Item.STATUS_9_ON_BID+"')" +
						" and approve.approverId='" + u.getId() + "' " +
								"and approve.status in('"+ItemApprove.STATUS_REJECTED+"','" + ItemApprove.STATUS_PENDING + "','"+ItemApprove.STATUS_APPROVED+"') " +
										"and approve.type='"+ ItemApprove.TYPE_SETUP_APPROVE + "'";
		return hql;
	}*/
	
	public String list() throws JTException{
		User u = this.getLoginUser();
		if(u==null){
			throw new JTException("用户超时",this.getClass());
		}
		String hql = "select item,approve from Item item, ItemApprove approve where item.id=approve.itemId and " +
		"item.status in ('"+Item.STATUS_7_SETUP_REJECT+"','" + Item.STATUS_7_SETUP_PENDING_APPROVE + "','"+Item.STATUS_9_ON_BID+"')" +
				" and approve.approverId='" + u.getId() + "' " +
						"and approve.status in('"+ItemApprove.STATUS_REJECTED+"','" + ItemApprove.STATUS_PENDING + "','"+ItemApprove.STATUS_APPROVED+"') " +
								"and approve.type='"+ ItemApprove.TYPE_SETUP_APPROVE + "'";
		
		List l = service.queryByHql(hql);
		Map<String,Object[]> objMap= new HashMap<String,Object[]>();
		for(Object o:l){
			Object[] objAry=(Object[])o;
			Item item = (Item)objAry[0];
			ItemApprove itemApprove = (ItemApprove)objAry[1];
			String itemId= item.getId();
			
			Object tmp = objMap.get(itemId);
			if(tmp==null){
				objMap.put(itemId, objAry);
			}else{
				if(ItemApprove.STATUS_PENDING.equals(itemApprove.getStatus())){
					objMap.put(itemId, objAry);
				}
			}
		}
		List<Object[]> list = new ArrayList<Object[]>();
		list.addAll(objMap.values());
		request.setAttribute("objectList", list);
		this.setObjectList(list);
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

	public ItemApprove getItemApprove() {
		return itemApprove;
	}

	public void setItemApprove(ItemApprove itemApprove) {
		this.itemApprove = itemApprove;
	}

	public List<ItemApprove> getSetupApproveList() {
		return setupApproveList;
	}

	public void setSetupApproveList(List<ItemApprove> setupApproveList) {
		this.setupApproveList = setupApproveList;
	}

	
}
