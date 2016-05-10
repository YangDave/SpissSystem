package com.ss.spiss.dao;

import net.sf.json.JSONObject;

import com.ss.common.BaseDao;
import com.ss.spiss.entity.User;

public interface UserDao extends BaseDao<User>{
	
	public User query(String username,String password);
	
	
//	�ж��û����Ƿ����
	public boolean isUsernameExist(String username);
	
	public User getUser(String username);

}
