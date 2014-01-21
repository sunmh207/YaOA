package com.jitong.oa.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.form.ItemSearchForm;
import com.jitong.oa.service.ItemService;
import com.opensymphony.xwork2.Preparable;

public class ItemAction extends JITActionBase implements Preparable {
	//private ItemSearchForm searchForm;
	private String qryItemName;
	private String qryItemType;
	private String qryStatus;
	private String qryApplyDateStart;
	private String qryApplyDateEnd;
	private String qryHostDept;

	private static ItemService service;
	private Item item;
	private Map<String,String> statusMap;


	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
		}
		statusMap = new LinkedHashMap<String,String>();
		statusMap.put(Item.STATUS_1_NEW, Item.status2Txt(Item.STATUS_1_NEW));
		statusMap.put(Item.STATUS_2_PENDING_APPROVAL, Item.status2Txt(Item.STATUS_2_PENDING_APPROVAL));
		statusMap.put(Item.STATUS_3_REJECT, Item.status2Txt(Item.STATUS_3_REJECT));

		statusMap.put(Item.STATUS_5_PENDING_INFO, Item.status2Txt(Item.STATUS_5_PENDING_INFO));
		statusMap.put(Item.STATUS_5_PENDING_MKT_SIGN, Item.status2Txt(Item.STATUS_5_PENDING_MKT_SIGN));
		statusMap.put(Item.STATUS_5_MKT_SIGNED, Item.status2Txt(Item.STATUS_5_MKT_SIGNED));
		statusMap.put(Item.STATUS_5_PENDING_MKT_CONFIRM, Item.status2Txt(Item.STATUS_5_PENDING_MKT_CONFIRM));
		statusMap.put(Item.STATUS_5_MKT_CONFIRMED, Item.status2Txt(Item.STATUS_5_MKT_CONFIRMED));
		
		statusMap.put(Item.STATUS_6_MKT_RPT_PENDING_APPROVE, Item.status2Txt(Item.STATUS_6_MKT_RPT_PENDING_APPROVE));
		statusMap.put(Item.STATUS_6_MKT_RPT_REJECT, Item.status2Txt(Item.STATUS_6_MKT_RPT_REJECT));
		
		statusMap.put(Item.STATUS_7_SETUP, Item.status2Txt(Item.STATUS_7_SETUP));
		statusMap.put(Item.STATUS_7_SETUP_PENDING_APPROVE, Item.status2Txt(Item.STATUS_7_SETUP_PENDING_APPROVE));
		statusMap.put(Item.STATUS_7_SETUP_REJECT, Item.status2Txt(Item.STATUS_7_SETUP_REJECT));
		
		statusMap.put(Item.STATUS_9_ON_BID, Item.status2Txt(Item.STATUS_9_ON_BID));
		statusMap.put(Item.STATUS_9_ON_BID_PENDING_ZBDEPT_APPROVE, Item.status2Txt(Item.STATUS_9_ON_BID_PENDING_ZBDEPT_APPROVE));
		statusMap.put(Item.STATUS_9_ON_BID_ZBDEPT_APPROVED, Item.status2Txt(Item.STATUS_9_ON_BID_ZBDEPT_APPROVED));
		statusMap.put(Item.STATUS_9_ON_BID_ZBDEPT_REJECT, Item.status2Txt(Item.STATUS_9_ON_BID_ZBDEPT_REJECT));
		statusMap.put(Item.STATUS_9_ON_BID_PENDING_JJW_APPROVE, Item.status2Txt(Item.STATUS_9_ON_BID_PENDING_JJW_APPROVE));
		statusMap.put(Item.STATUS_9_ON_BID_JJW_REJECT, Item.status2Txt(Item.STATUS_9_ON_BID_JJW_REJECT));
		statusMap.put(Item.STATUS_9_ON_BID_JJW_APPROVED, Item.status2Txt(Item.STATUS_9_ON_BID_JJW_APPROVED));
		statusMap.put(Item.STATUS_9_ON_BID_PENDING_ZBDEPT_MEETINGNOTE, Item.status2Txt(Item.STATUS_9_ON_BID_PENDING_ZBDEPT_MEETINGNOTE));
		
		statusMap.put(Item.STATUS_10_ON_ACCEPT, Item.status2Txt(Item.STATUS_10_ON_ACCEPT));
		
		statusMap.put(Item.STATUS_11_ON_FINISH, Item.status2Txt(Item.STATUS_11_ON_FINISH));
		/*statusMap.put(Item.STATUS_11_ON_FINISH_PENDING_LEAD_APPROVE, Item.status2Txt(Item.STATUS_11_ON_FINISH_PENDING_LEAD_APPROVE));
		statusMap.put(Item.STATUS_11_ON_FINISH_LEAD_APPROVED, Item.status2Txt(Item.STATUS_11_ON_FINISH_LEAD_APPROVED));
		statusMap.put(Item.STATUS_11_ON_FINISH_LEAD_REJECT, Item.status2Txt(Item.STATUS_11_ON_FINISH_LEAD_REJECT));
		statusMap.put(Item.STATUS_11_ON_FINISH_PENDING_JJW_APPROVE, Item.status2Txt(Item.STATUS_11_ON_FINISH_PENDING_JJW_APPROVE));
		statusMap.put(Item.STATUS_11_ON_FINISH_JJW_REJECT, Item.status2Txt(Item.STATUS_11_ON_FINISH_JJW_REJECT));*/
		
		statusMap.put(Item.STATUS_12_ON_PAY, Item.status2Txt(Item.STATUS_12_ON_PAY));
		statusMap.put(Item.STATUS_13_CLOSE, Item.status2Txt(Item.STATUS_13_CLOSE));
	}


	public String input() {
		return INPUT;
	}

	public String delete() throws JTException {
		service.deleteItem(item.getId());
		return list();
	}


	public String save() throws Exception {
		try {
			User u = this.getLoginUser();
			if (u==null){
				this.addActionError("当前用户会话过期");
				return INPUT;
			}
			// new
			if (item.getId() == null || "".equals(item.getId())) {
				item.setRequesterId(u.getId());
				item.setRequesterName(u.getName());
				item.setApplyDate(DateUtil.getCurrentDate());
				service.insertItem(item);
				// modify
			} else { 
				service.updateItem(item);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}



	public String getListHQL(ArrayList<Object> params) throws JTException {
		/*if(searchForm==null){
			searchForm = new ItemSearchForm();
		}*/
		//String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from Item item,ItemBid bid,ItemFinish finish where item.id = bid.itemId and finish.item.id=item.id " ;
		if(!StringUtil.isEmpty(qryItemName)){
			hql+=" and item.itemName like '%"+qryItemName+"%'";
		}
		if(!StringUtil.isEmpty(qryItemType)){
			hql+=" and item.itemType = '"+qryItemType+"'";
		}
		if(!StringUtil.isEmpty(qryStatus)){
			hql+=" and item.status = '"+qryStatus+"'";
		}
		if(!StringUtil.isEmpty(qryApplyDateStart)){
			hql+=" and bid.applyDate >= '"+qryApplyDateStart+"'";
		}
		if(!StringUtil.isEmpty(qryApplyDateEnd)){
			hql+=" and bid.applyDate <= '"+qryApplyDateEnd+"'";
		}
		if(!StringUtil.isEmpty(qryHostDept)){
			hql+=" and bid.hostDept like '%"+qryHostDept+"%'";
		}
		return hql;
	}


/*	public ItemSearchForm getSearchForm() {
		return searchForm;
	}


	public void setSearchForm(ItemSearchForm searchForm) {
		this.searchForm = searchForm;
	}*/


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public Map<String, String> getStatusMap() {
		return statusMap;
	}


	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}


	public String getQryItemName() {
		return qryItemName;
	}


	public void setQryItemName(String qryItemName) {
		this.qryItemName = qryItemName;
	}


	public String getQryItemType() {
		return qryItemType;
	}


	public void setQryItemType(String qryItemType) {
		this.qryItemType = qryItemType;
	}


	public String getQryStatus() {
		return qryStatus;
	}


	public void setQryStatus(String qryStatus) {
		this.qryStatus = qryStatus;
	}


	public String getQryApplyDateStart() {
		return qryApplyDateStart;
	}


	public void setQryApplyDateStart(String qryApplyDateStart) {
		this.qryApplyDateStart = qryApplyDateStart;
	}


	public String getQryApplyDateEnd() {
		return qryApplyDateEnd;
	}


	public void setQryApplyDateEnd(String qryApplyDateEnd) {
		this.qryApplyDateEnd = qryApplyDateEnd;
	}


	public String getQryHostDept() {
		return qryHostDept;
	}


	public void setQryHostDept(String qryHostDept) {
		this.qryHostDept = qryHostDept;
	}
	
}
