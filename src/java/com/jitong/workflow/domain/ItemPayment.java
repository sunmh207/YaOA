package com.jitong.workflow.domain;

import java.sql.Blob;

import com.jitong.oa.domain.Item;
/**
 * 项目验收
 * @author stanley.sun
 *
 */
public class ItemPayment {
	private String id;
	private String itemId;
	private String payCompany;
	private String payDate;
	private String payAmount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getPayCompany() {
		return payCompany;
	}
	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	
}
