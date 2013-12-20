package com.jitong.oa.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SearchForm;

public class ItemSearchForm  extends SearchForm{
	private String itemName;
	private String itemType;
	private String status;
	
	@FieldCondition(op=Operation.like)
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@FieldCondition(op=Operation.eq)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@FieldCondition(op=Operation.eq)
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

}
