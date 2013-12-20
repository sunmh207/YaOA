package com.jitong.oa.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.oa.domain.Item;
import com.jitong.oa.domain.ItemOperator;
import com.jitong.workflow.domain.ItemApprove;

public class ItemDAO extends BaseDAO {

	public ItemDAO(Session session) {
		super(session);
	}

	public Item findItem(String itemId) throws JTException {
		try {
			return (Item) super.findBoById(Item.class, itemId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when find Item. ItemId=" + itemId, e, this.getClass());
		}
	}


	public List<Item> queryItems() throws JTException {
		try {
			return (List<Item>) super.find("from Item u");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query Items.", e, this.getClass());
		}
	}
	public List<Item> queryTopItems(int rows) throws JTException {
			try {
				return (List<Item>) super.find("from Item u  where rownum<="+rows+" order by applyDate desc");
			} catch (HibernateException e) {
				throw new JTException("Error occured when query Items.", e, this.getClass());
			}
	}
	public List<ItemOperator> queryOperators(String itemId,String itemStatus, String type) throws JTException {
		try {
			String hql ="from ItemOperator o where  o.itemId='"+itemId+"'";
			if(!StringUtil.isEmpty(itemStatus)){
				hql+=" and  o.itemStatus='"+itemStatus+"' ";
			}
			if(!StringUtil.isEmpty(type)){
				hql+=" and  o.type='"+type+"' ";
			}
			return (List<ItemOperator>) super.find(hql);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query ItemOperator.", e, this.getClass());
		}
	}
	public List<Object[]> queryOperatorsByUserId(String userId) throws JTException {
		try {
			String hql ="select i, o from ItemOperator o, Item i where  o.itemId=i.id and o.itemStatus=i.status and o.isDone='"+ItemOperator.ISDONE_NO+"'";
			if(!StringUtil.isEmpty(userId)){
				hql+=" and  o.operatorId='"+userId+"' ";
			}
			hql+=" order by o.createTime desc";
			return (List<Object[]>) super.find(hql);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query ItemOperator.", e, this.getClass());
		}
	}
	
	/////======ItemApprove=========================
	public List<ItemApprove> queryItemApproves(String itemId) throws JTException {
		try {
			String hql ="from ItemApprove approve where approve.itemId='"+itemId+"'";
			hql+=" order by approve.operationTime desc";
			return (List<ItemApprove>) super.find(hql);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query ItemApprove.", e, this.getClass());
		}
	}
	
}
