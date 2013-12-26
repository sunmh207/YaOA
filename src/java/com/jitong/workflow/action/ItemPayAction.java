package com.jitong.workflow.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemFinish;
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
	//private ItemBid bid;
	//private ItemPayment itemPayment;
	
	private ItemFinish itemFinish;

	//private List<RecommendBidder> recommendBidderList;
	private List<ItemFinish> itemPaymentList;
	
	private Map<String,String> paymentMap=new HashMap<String,String>();;
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (itemFinish != null && itemFinish.getId() != null) {
			itemFinish = (ItemFinish) service.findBoById(ItemFinish.class,itemFinish.getId());
		}
		if (item != null && item.getId() != null) {
			item = (Item) service.findBoById(Item.class,item.getId());
		}
		/*if (itemPayment != null && itemPayment.getId() != null) {
			itemPayment = (ItemPayment)service.findBoById(ItemPayment.class,itemPayment.getId());
		}*/
	}

	public String input() throws JTException {
		if (item!= null && item.getId() != null) {
			itemPaymentList = (List<ItemFinish>) service.queryByHql("from ItemFinish itemFinish where itemFinish.item.id='" + item.getId() + "'");
			
			paymentMap.clear();
			for(ItemFinish f:itemPaymentList){
				if(ItemFinish.STATUS_11_ON_FINISH_JJW_APPROVED.equals(f.getStatus())){
					paymentMap.put(f.getId(), f.getFinishItemName());
				}
			}
		}
		return INPUT;
	}

	public String save() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		itemFinish.setStatus(ItemFinish.STATUS_11_PAID);
		service.updateBo(itemFinish);
		this.addActionMessage("保存成功");
		return input();
	}
	/*public String delete() throws Exception {
		if(itemFinish!=null&&itemFinish.getId()!=null){
			service.deleteBo(ItemFinish.class, itemFinish.getId());
		}
		this.addActionMessage("删除成功");
		return input();
	}*/
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
		String hql = " from Item item where item.requesterId = '" + u.getId() + "' and item.status in ('"+Item.STATUS_11_ON_FINISH+"','"+Item.STATUS_12_ON_PAY+"','"+Item.STATUS_10_ON_ACCEPT+"') ";
		return hql;
	}


	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<ItemFinish> getItemPaymentList() {
		return itemPaymentList;
	}

	public void setItemPaymentList(List<ItemFinish> itemPaymentList) {
		this.itemPaymentList = itemPaymentList;
	}

	public ItemFinish getItemFinish() {
		return itemFinish;
	}

	public void setItemFinish(ItemFinish itemFinish) {
		this.itemFinish = itemFinish;
	}

	public Map<String, String> getPaymentMap() {
		return paymentMap;
	}

	public void setPaymentMap(Map<String, String> paymentMap) {
		this.paymentMap = paymentMap;
	}

}
