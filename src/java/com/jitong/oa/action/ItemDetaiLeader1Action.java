package com.jitong.oa.action;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.oa.domain.Item;
import com.jitong.oa.domain.ItemOperator;
import com.jitong.oa.service.ItemService;
import com.opensymphony.xwork2.Preparable;

public class ItemDetaiLeader1Action extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemOperator itemOperator;
	private String approvalMsg;

	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
		}
		if (itemOperator != null && itemOperator.getId() != null) {
			itemOperator = service.findItemOperator(itemOperator.getId());
		}
	}

	public String view() {
		return INPUT;
	}
	public String approve() {
		try{
			service.leader1Approve(item, itemOperator,approvalMsg);
		}catch(Exception e){
			this.addActionError(e.getMessage());
			logger.error(e.getMessage());
		}
		return view();
	}
	public String disapprove() {
		try{
			itemOperator.setApproval(ItemOperator.APPROVAL_NO);
			itemOperator.setApprovalMsg(approvalMsg);
			service.updateBo(itemOperator);
		}catch(Exception e){
			this.addActionError(e.getMessage());
			logger.error(e.getMessage());
		}
		return view();
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	

	public String getApprovalMsg() {
		return approvalMsg;
	}


	public void setApprovalMsg(String approvalMsg) {
		this.approvalMsg = approvalMsg;
	}


	public ItemOperator getItemOperator() {
		return itemOperator;
	}


	public void setItemOperator(ItemOperator itemOperator) {
		this.itemOperator = itemOperator;
	}

	
}
