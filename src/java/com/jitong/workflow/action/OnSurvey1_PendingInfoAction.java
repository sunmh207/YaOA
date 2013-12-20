package com.jitong.workflow.action;

import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemApprove;
import com.opensymphony.xwork2.Preparable;

public class OnSurvey1_PendingInfoAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;

	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
		}
	}

	public String input() throws JTException {
		return INPUT;
	}

	/**
	 * 发通知
	 * 
	 * @return
	 * @throws Exception
	 */
	public String info() throws Exception {
		item.setStatus(Item.STATUS_5_PENDING_MKT_SIGN);
		//item.setSurveyInfoDate(DateUtil.getCurrentDate());
		
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		
		for (String id : ids) {
			//check existing
			String hql="from ItemApprove approve where approve.type='"+ItemApprove.TYPE_MKT_SIGN+"' " +
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
				itemApprove.setType(ItemApprove.TYPE_MKT_SIGN);
				itemApprove.setStatus(ItemApprove.STATUS_PENDING);
				service.createBo(itemApprove);
			}
		}
		/*for (String id : ids) {
			names+=SysCache.interpertUserName(id)+",";
		}*/
		/*item.setSurveyInfoReceiverNames(names);
		item.setSurveyInfoReceiverIds(idString);*/
		

		service.updateItem(item);
		return SUCCESS;
	}

/*	public String getListHQL(ArrayList<Object> params) throws JTException {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		String hql = " from Item item where item.requesterId='" + u.getId() +
		"' and item.status in ('"+Item.STATUS_5_PENDING_INFO+"' )";
		return hql;
	}*/

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
