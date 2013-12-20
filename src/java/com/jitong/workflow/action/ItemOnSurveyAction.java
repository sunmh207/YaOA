package com.jitong.workflow.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemApprove;
import com.opensymphony.xwork2.Preparable;

public class ItemOnSurveyAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private File uploadfile;
	private String uploadfileFileName;
	private List<ItemApprove> rptApproveList;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			rptApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_RPT_APPROVE+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
		}
	}

	public String input() throws JTException {
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
		}
		return INPUT;
	}

	/**
	 * 发通知
	 * 
	 * @return
	 * @throws Exception
	 */
	public String submitReport() throws Exception {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		
		item.setStatus(Item.STATUS_6_MKT_RPT_PENDING_APPROVE);
		item.setSurveyReportDate(DateUtil.getCurrentDate());
		item.setSurveyReportReporterId(u.getId());
		item.setSurveyReportReporterName(SysCache.interpertUserName(u.getId()));
		
		if (uploadfile != null) {
			String ext = FileTypeUtil.getExtensionByName(uploadfileFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return INPUT;
			}
			FileInputStream is = new FileInputStream(uploadfile);
			item.setSurveyReportBody(Hibernate.createBlob(is));
			item.setSurveyReportBodyExt(ext);
		}
		service.updateItem(item);
		
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (String id : ids) {
			//check existing
			String hql="from ItemApprove approve where approve.type='"+ItemApprove.TYPE_RPT_APPROVE+"' " +
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
				itemApprove.setType(ItemApprove.TYPE_RPT_APPROVE);
				itemApprove.setStatus(ItemApprove.STATUS_PENDING);
				service.createBo(itemApprove);
			}
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		User u = this.getLoginUser();
		if (u == null) {
			throw new JTException("用户超时", this.getClass());
		}
		//String hql = " from Item item where item.surveyInfoReceiverIds like '%" + u.getId() + "%' and item.status in('" + Item.STATUS_5_ON_SURVEY + "','" + Item.STATUS_6_MKT_RPT_REJECT + "','"+Item.STATUS_6_MKT_RPT_PENDING_APPROVE+"') ";
		return "";
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getUploadfileFileName() {
		return uploadfileFileName;
	}

	public void setUploadfileFileName(String uploadfileFileName) {
		this.uploadfileFileName = uploadfileFileName;
	}

	public List<ItemApprove> getRptApproveList() {
		return rptApproveList;
	}

	public void setRptApproveList(List<ItemApprove> rptApproveList) {
		this.rptApproveList = rptApproveList;
	}

}
