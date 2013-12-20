package com.jitong.workflow.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemAccept;
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.RecommendBidder;
import com.opensymphony.xwork2.Preparable;
/**
 * 项目验收
 * @author stanley.sun
 *
 */
public class ItemAcceptAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemBid bid;
	
	private File  itemAcceptInfo;//验收记录
	private String  itemAcceptInfoFileName;
	private ItemAccept itemAccept;
	
	private List<RecommendBidder> recommendBidderList;
	private List<ItemAccept> itemAcceptList;

	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			bid=(ItemBid)service.findBoById(ItemBid.class, item.getId());
		}
		if (itemAccept != null && itemAccept.getId() != null) {
			itemAccept = (ItemAccept)service.findBoById(ItemAccept.class,itemAccept.getId());
		}
	}

	public String input() throws JTException {
		recommendBidderList = (List<RecommendBidder>)service.queryByHql("from RecommendBidder bidder where bidder.itemId='"+item.getId()+"'");
		itemAcceptList = (List<ItemAccept>)service.queryByHql("from ItemAccept accept where accept.itemId='"+item.getId()+"'");
		return INPUT;
	}

	public String uploadAcceptInfo() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		itemAccept.setItemId(item.getId());
		
		if(itemAcceptInfo!=null){
			String ext = FileTypeUtil.getExtensionByName(itemAcceptInfoFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return INPUT;
			}
			FileInputStream itemAcceptInfoIS = new FileInputStream(itemAcceptInfo);
			itemAccept.setAcceptInfo(Hibernate.createBlob(itemAcceptInfoIS));
			itemAccept.setAcceptInfoExt(ext);
		}
		service.saveOrUpdateBo(itemAccept);
		this.addActionMessage("上传成功");
		return input();
	}
	public String delete() throws Exception {
		if(itemAccept!=null&&itemAccept.getId()!=null){
			service.deleteBo(ItemAccept.class, itemAccept.getId());
		}
		this.addActionMessage("删除成功");
		return input();
	}
	
	public String finishAccept() throws Exception {
		//item.setStatus(Item.STATUS_12_ON_PAY);
		item.setStatus(Item.STATUS_11_ON_FINISH);
		service.updateItem(item);
		return SUCCESS;
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		String hql = "from Item item  where item.requesterId='"+u.getId()+"' and item.status in('" + Item.STATUS_10_ON_ACCEPT + "' , '"+Item.STATUS_12_ON_PAY+"') ";
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

	public File getItemAcceptInfo() {
		return itemAcceptInfo;
	}

	public void setItemAcceptInfo(File itemAcceptInfo) {
		this.itemAcceptInfo = itemAcceptInfo;
	}

	public String getItemAcceptInfoFileName() {
		return itemAcceptInfoFileName;
	}

	public void setItemAcceptInfoFileName(String itemAcceptInfoFileName) {
		this.itemAcceptInfoFileName = itemAcceptInfoFileName;
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

	public ItemAccept getItemAccept() {
		return itemAccept;
	}

	public void setItemAccept(ItemAccept itemAccept) {
		this.itemAccept = itemAccept;
	}

}
