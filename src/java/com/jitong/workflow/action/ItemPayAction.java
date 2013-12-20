package com.jitong.workflow.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemAccept;
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.ItemPayment;
import com.jitong.workflow.domain.RecommendBidder;
import com.opensymphony.xwork2.Preparable;

/**
 * 项目验收
 * 
 * @author stanley.sun
 * 
 */
public class ItemPayAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemBid bid;
	private ItemPayment itemPayment;

	private List<RecommendBidder> recommendBidderList;
	private List<ItemPayment> itemPaymentList;

	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			bid = (ItemBid) service.findBoById(ItemBid.class, item.getId());
		}
		if (itemPayment != null && itemPayment.getId() != null) {
			itemPayment = (ItemPayment)service.findBoById(ItemPayment.class,itemPayment.getId());
		}
	}

	public String input() throws JTException {
		recommendBidderList = (List<RecommendBidder>) service.queryByHql("from RecommendBidder bidder where bidder.itemId='" + item.getId() + "'");
		itemPaymentList = (List<ItemPayment>) service.queryByHql("from ItemPayment payment where payment.itemId='" + item.getId() + "'");
		return INPUT;
	}

	public String save() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		itemPayment.setItemId(item.getId());
		service.saveOrUpdateBo(itemPayment);
		this.addActionMessage("保存成功");
		return input();
	}
	public String delete() throws Exception {
		if(itemPayment!=null&&itemPayment.getId()!=null){
			service.deleteBo(ItemPayment.class, itemPayment.getId());
		}
		this.addActionMessage("删除成功");
		return input();
	}
	public String finishPay() throws Exception {
		item.setStatus(Item.STATUS_13_CLOSE);
		service.updateItem(item);
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		String hql = "from Item item  where item.status in('"+ Item.STATUS_12_ON_PAY + "') ";
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

	public ItemPayment getItemPayment() {
		return itemPayment;
	}

	public void setItemPayment(ItemPayment itemPayment) {
		this.itemPayment = itemPayment;
	}

	public List<ItemPayment> getItemPaymentList() {
		return itemPaymentList;
	}

	public void setItemPaymentList(List<ItemPayment> itemPaymentList) {
		this.itemPaymentList = itemPaymentList;
	}

}
