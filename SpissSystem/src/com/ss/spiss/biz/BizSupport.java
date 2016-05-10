package com.ss.spiss.biz;

import com.ss.spiss.dao.PlateDao;
import com.ss.spiss.dao.PostDao;
import com.ss.spiss.dao.PrivilegeDao;
import com.ss.spiss.dao.ReplyDao;
import com.ss.spiss.dao.RoleDao;
import com.ss.spiss.dao.UserDao;
import com.ss.spiss.dao.UserMappingDao;
import com.ss.spiss.daoImpl.DaoBundle;

public class BizSupport {
	
	protected DaoBundle daoBundle;

	public DaoBundle getDaoBundle() {
		return daoBundle;
	}

	public void setDaoBundle(DaoBundle daoBundle) {
		this.daoBundle = daoBundle;
	}
	
	public PlateDao getPlateDao(){
		return daoBundle.getPlateDao();
	}
	
	public PostDao getPostDao(){
		return daoBundle.getPostDao();
	}
	
	public PrivilegeDao getPrivilegeDao(){
		return daoBundle.getPrivilegeDao();
	}
	
	public ReplyDao getReplyDao(){
		return daoBundle.getReplyDao();
	}
	
	public RoleDao getRoleDao(){
		return daoBundle.getRoleDao();
	}
	
	public UserDao getUserDao(){
		return daoBundle.getUserDao();
	}
	public UserMappingDao getUserMappingDao(){
		return daoBundle.getUserMappingDao();
	}

}
