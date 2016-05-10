package com.ss.spiss.daoImpl;

import com.ss.spiss.dao.PlateDao;
import com.ss.spiss.dao.PostDao;
import com.ss.spiss.dao.PrivilegeDao;
import com.ss.spiss.dao.ReplyDao;
import com.ss.spiss.dao.RoleDao;
import com.ss.spiss.dao.UserDao;
import com.ss.spiss.dao.UserMappingDao;
import com.ss.spiss.utils.MyJSONUtils;

public class DaoBundle {
	
	protected PlateDao plateDao;
	protected PostDao postDao;
	protected PrivilegeDao privilegeDao;
	protected ReplyDao replyDao;
	protected RoleDao roleDao;
	protected UserDao userDao;
	protected UserMappingDao userMappingDao;
	protected MyJSONUtils myJsonUtils;
	
	
	public MyJSONUtils getMyJsonUtils() {
		return myJsonUtils;
	}
	public void setMyJsonUtils(MyJSONUtils myJsonUtils) {
		this.myJsonUtils = myJsonUtils;
	}
	public PlateDao getPlateDao() {
		return plateDao;
	}
	public void setPlateDao(PlateDao plateDao) {
		this.plateDao = plateDao;
	}
	public PostDao getPostDao() {
		return postDao;
	}
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	public PrivilegeDao getPrivilegeDao() {
		return privilegeDao;
	}
	public void setPrivilegeDao(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}
	public ReplyDao getReplyDao() {
		return replyDao;
	}
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	public RoleDao getRoleDao() {
		return roleDao;
	}
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public UserMappingDao getUserMappingDao() {
		return userMappingDao;
	}
	public void setUserMappingDao(UserMappingDao userMappingDao) {
		this.userMappingDao = userMappingDao;
	}
	
}
