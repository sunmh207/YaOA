package com.jitong.console.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.dao.UserDAO;
import com.jitong.console.domain.Menu;
import com.jitong.console.domain.Role;
import com.jitong.console.domain.User;

public class UserService extends BaseService {
	UserDAO dao;

	public UserService(Session s) {
		super(s);
		dao = new UserDAO(s);
	}

	public UserService() throws JTException {
		super();
		dao = new UserDAO(s);
	}

	public User findUser(String userId) throws JTException {
		return dao.findUser(userId);
	}

	public User findUserByLoginName(String loginName) throws JTException {
		return dao.findUserByLoginName(loginName);
	}

	public List<User> queryUsers() throws JTException {
		return dao.queryUsers();
	}

	public List<User> queryUsersByDept(String unit, String dept) throws JTException {
		boolean hasUnit = !StringUtil.isEmpty(unit);
		boolean hasDept = !StringUtil.isEmpty(dept);
		String op;
		String value;
		if (hasUnit && hasDept) {
			op = "=";
			value = unit + "-" + dept;
		} else if (hasUnit) {
			op = "like";
			value = unit + "-%";
		} else {
			op = "like";
			value = "%-" + dept;
		}

		return dao.find("from User u where u.unitDept " + op + " ?", new Object[] { value });
	}

	public void deleteUser(String userId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(User.class, userId);
			SysCache.reloadUserById(userId, SysCache.OPER_DELETE);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}

	/**
	 * user当前用户 person:需要增加的人
	 */
	public void insertUser(User user, User person) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			if(phoneNumberExists(person.getPhoneNumber())){
				throw new JTException("电话号码"+person.getPhoneNumber()+"已经存在", this.getClass());
			}
			if(loginNameExists(person.getLoginName())){
				throw new JTException("登录名"+person.getLoginName()+"已经存在", this.getClass());
			}
			
			person.setCreatorId(user==null?"":user.getId());
			String userId = dao.createBo(person);
			SysCache.reloadUserById(userId, SysCache.OPER_ADD);
			insertUserPerson(user, person);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw e;
		}
	}
	public void updateUser(User user, String[] roleIds) throws JTException {
		Transaction tx = null;
		try{
			if(roleIds==null||roleIds.length==0){
				updateUser(user);
			}else{
				UserRoleService urs = new UserRoleService(s);
				urs.deleteRoleUserByUserId(user.getId());//删除用户的所有角色
				for(String roleId:roleIds){
					urs.addRoleUser(user.getId(), roleId);//添加选中的所有新角色所有角色
				}
				
			}
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw e;
		}
	}
	
	private void updateUser(User user) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			User u = SysCache.interpertUserByPhone(user.getPhoneNumber());
			if(u!=null&&!u.getId().equals(user.getId())){
				throw new JTException("电话号码"+user.getPhoneNumber()+"已经存在", this.getClass());
			}
			
			u = dao.findUserByLoginName(user.getLoginName());
			if(u!=null&&!u.getId().equals(user.getId())){
				throw new JTException("登录名"+user.getLoginName()+"已经存在", this.getClass());
			}
			dao.updateBo(user);
			SysCache.reloadUserById(user.getId(), SysCache.OPER_UPDATE);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw e;
		}
	}
	
	/**
	 * check if there the user whose phoneNumber = @phoneNumber exist 
	 * @param phoneNumber
	 * @return
	 */
	public boolean phoneNumberExists(String phoneNumber){
		if(SysCache.interpertUserNameByPhone(phoneNumber)!=null){
			return true;
		}
		return false;
	}
	public boolean loginNameExists(String loginName) throws JTException {
		User u = dao.findUserByLoginName(loginName);
		if(u!=null){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param userName
	 * @param password
	 * @param session
	 *            put user information into session
	 * @return
	 * @throws JTException
	 */
	public User verifyLogon(String userName, String password, Map<String, Object> session) throws JTException {
		Transaction tx = null;
		try {
			tx = super.beginTransaction();// 事务开启
			String pwd = StringUtil.md5(password);// 加密
			User user = dao.verifyLogon(userName, pwd);

			if (user == null) {
				//throw new JTException("用户名或密码错误", this.getClass());
				return null;
			}
			loadUserInfo(user, session); //
			super.commitTransaction(tx);
			return user;
		} catch (JTException jte) {
			super.rollbackTransaction(tx);
			throw jte;
		} catch (Exception e) {
			super.rollbackTransaction(tx);
			throw new JTException("登录失败", e, UserService.class);
		}
	}

	private void loadUserInfo(User user, Map<String, Object> session) throws JTException {
		// load
		user.setMenusMap(dao.queryUserModuleMenu(user.getId()));
		user.setPersonMap(dao.queryUserPerson(user.getId()));
	}
	
	public Map<String, List<Menu>> queryUserModuleMenu(String userId) throws JTException {
		return dao.queryUserModuleMenu(userId);
	}

	/**
	 * 将person加入user的人员权限中去 1.找到user的所有角色 2.给每个角色加入person人员权限 (未考虑上级角色的人员权限)
	 * 
	 * @param user
	 * @param person
	 * @throws JTException
	 */
	public void insertUserPerson(User user, User person) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			UserRoleService urService = new UserRoleService(s);
			// find all roles which the user has
			List<Role> roles = urService.queryRoleAndSuperRoleByUserId(user.getId());
			if (roles != null && roles.size() > 0) {
				RolePersonService rpService = new RolePersonService(s);
				for (int i = 0; i < roles.size(); i++) {
					Role role = roles.get(i);
					rpService.addRolePerson(person.getId(), role.getRoleId());
				}
			}
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增用户的人员权限失败", e, this.getClass());
		}
	}
}
