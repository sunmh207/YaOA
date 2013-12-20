package com.jitong.oa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.oa.dao.ItemDAO;
import com.jitong.oa.domain.Item;
import com.jitong.oa.domain.ItemOperator;
import com.jitong.workflow.domain.ItemApprove;
import com.jitong.workflow.domain.ItemBid;

public class ItemService extends BaseService {
	ItemDAO dao;

	public ItemService(Session s) {
		super(s);
		dao = new ItemDAO(s);
	}

	public ItemService() throws JTException {
		super();
		dao = new ItemDAO(s);
	}

	public Item findItem(String itemId) throws JTException {
		return dao.findItem(itemId);
	}

	public void deleteItem(String itemId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(Item.class, itemId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}
	public List<Item> queryItems() throws JTException {
		return dao.queryItems();
	}
	public List<Item> queryTopItems(int rows) throws JTException {
		return dao.queryTopItems(rows);
	}
	public String insertItem(Item item) throws JTException {
		Transaction tx = null;
		String id;
		try {
			tx = this.beginTransaction();
			id=dao.createBo(item);
			
			ItemBid bid = new ItemBid();
			bid.setItemId(id);
			dao.createBo(bid);
			
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw e;
		}
		return id;
	}
	
	public void updateItem(Item item) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(item);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw e;
		}
	}
	
	//===========ItemApprove===========================
	
	
	
	public List<ItemApprove> queryItemApproves(String itemId) throws JTException {
		return dao.queryItemApproves(itemId);
	}
	
	//=====================================
	public ItemOperator findItemOperator(String itemOperatorId) throws JTException {
		 return (ItemOperator) dao.findBoById(ItemOperator.class, itemOperatorId);
	}
	/**
	 * 根据项目id，查询
	 */
	public List<ItemOperator> queryOperators(String itemId, String itemStatus) throws JTException {
		return queryOperators(itemId,itemStatus,null);
	}
	public List<ItemOperator> queryOperators(String itemId, String itemStatus, String type) throws JTException {
		return dao.queryOperators(itemId,itemStatus,type);
	}
	/**
	 * 查询 userId需要处理的Operation
	 * @param userId
	 * @return
	 * @throws JTException
	 */
	public List<Object[]> queryOperatorsByUserId(String userId) throws JTException {
		return dao.queryOperatorsByUserId(userId);
	}
	
	public void deleteItemOperatorId(String itemOperatorId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(ItemOperator.class, itemOperatorId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw e;
		}
	}
	public void leader1Approve(Item item, ItemOperator itemOperator, String approvalMsg)throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			itemOperator.setApproval(ItemOperator.APPROVAL_YES);
			itemOperator.setApprovalMsg(approvalMsg);
			itemOperator.setIsDone(ItemOperator.ISDONE_YES);
			itemOperator.setDoneTime(DateUtil.getCurrentTime());
			dao.updateBo(itemOperator);
			
			//item.setStatus(Item.STATUS_4_PENDING_SURVEY);
			item.setStatus(Item.STATUS_5_PENDING_INFO);
			dao.updateBo(item);
			
			//给发起人创建一个待办事宜
			/*ItemOperator newItemOp= new ItemOperator();
			newItemOp.setItemId(item.getId());
			newItemOp.setItemStatus(Item.STATUS_4_PENDING_SURVEY);
			User u = SysCache.interpertUser(item.getRequesterId());
			newItemOp.setType(ItemOperator.TYPE_REQUESTER);
			newItemOp.setOperatorId(u.getId());
			newItemOp.setOperatorName(u.getName());
			newItemOp.setOperatorDept(u.getDept());
			//newItemOp.setApproval()
			newItemOp.setCreateTime(DateUtil.getCurrentTime());
			newItemOp.setIsDone(ItemOperator.ISDONE_NO);
			this.createBo(newItemOp);*/
			
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw e;
		}
	}
}
