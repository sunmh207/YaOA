package com.jitong.workflow.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.ItemApprove;
import com.opensymphony.xwork2.Preparable;
/**
 * 提交报告给市场部确认
 * @author stanley.sun
 *
 */
public class OnSurvey3_SignedAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private File  reportDoc;
	private String reportDocFileName;
	
	private List<ItemApprove> mktSignApproveList;
	private List<ItemApprove> mktConfirmApproveList;
	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			mktSignApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_MKT_SIGN+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
			mktConfirmApproveList = (List<ItemApprove>) service.queryByHql("from ItemApprove approve where approve.type='"+ItemApprove.TYPE_MKT_CONFIRM+"' and approve.itemId='" + item.getId() + "' order by approve.operationTime");
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
	public String forConfirm() throws Exception {
		item.setStatus(Item.STATUS_5_PENDING_MKT_CONFIRM);
		

		if(reportDoc!=null){
			String ext = FileTypeUtil.getExtensionByName(reportDocFileName);
			if(FileTypeUtil.isForbiddenUploadType(ext)){
				this.addActionError("禁止上传 "+ext+" 文件");
				return input();
			}
			FileInputStream bidDocIS = new FileInputStream(reportDoc);
			item.setSurveyReportBody(Hibernate.createBlob(bidDocIS));
			item.setSurveyReportBodyExt(ext);
		}
		
		String idString = request.getParameter("useridStr");
		String[] ids;
		if(StringUtil.isEmpty(idString)){
			Set<String> idSet=new HashSet<String>();
			for(ItemApprove approve:mktSignApproveList){
				idSet.add(approve.getApproverId());
			}
			ids=idSet.toArray(new String[0]);
		}else{
			 ids = StringUtil.parseString2Array(idString, ",");
		}
		
		for (String id : ids) {
			//check existing
			String hql="from ItemApprove approve where approve.type='"+ItemApprove.TYPE_MKT_CONFIRM+"' " +
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
				itemApprove.setType(ItemApprove.TYPE_MKT_CONFIRM);
				itemApprove.setStatus(ItemApprove.STATUS_PENDING);
				service.createBo(itemApprove);
			}
		}
		

		service.updateItem(item);
		return SUCCESS;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public File getReportDoc() {
		return reportDoc;
	}

	public void setReportDoc(File reportDoc) {
		this.reportDoc = reportDoc;
	}

	public String getReportDocFileName() {
		return reportDocFileName;
	}

	public void setReportDocFileName(String reportDocFileName) {
		this.reportDocFileName = reportDocFileName;
	}

	public List<ItemApprove> getMktSignApproveList() {
		return mktSignApproveList;
	}

	public void setMktSignApproveList(List<ItemApprove> mktSignApproveList) {
		this.mktSignApproveList = mktSignApproveList;
	}

	public List<ItemApprove> getMktConfirmApproveList() {
		return mktConfirmApproveList;
	}

	public void setMktConfirmApproveList(List<ItemApprove> mktConfirmApproveList) {
		this.mktConfirmApproveList = mktConfirmApproveList;
	}

	
}
