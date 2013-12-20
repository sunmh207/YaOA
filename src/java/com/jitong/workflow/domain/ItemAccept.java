package com.jitong.workflow.domain;

import java.sql.Blob;

import com.mysql.jdbc.Clob;
/**
 * 项目验收
 * @author stanley.sun
 *
 */
public class ItemAccept {
	private String id;
	private String itemId;
	private Blob acceptInfo;
	private String acceptInfoExt;
	private String acceptDate;
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

	public Blob getAcceptInfo() {
		return acceptInfo;
	}
	public void setAcceptInfo(Blob acceptInfo) {
		this.acceptInfo = acceptInfo;
	}
	public String getAcceptInfoExt() {
		return acceptInfoExt;
	}
	public void setAcceptInfoExt(String acceptInfoExt) {
		this.acceptInfoExt = acceptInfoExt;
	}
	public String getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}
}
