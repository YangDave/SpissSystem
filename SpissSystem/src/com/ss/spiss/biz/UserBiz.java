package com.ss.spiss.biz;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import net.sf.json.JSONObject;

import com.ss.spiss.entity.Role;

public interface UserBiz {
	
//	��¼
	@Cacheable(value="users")
	JSONObject login(String username,String password);
//	������¼
	@Cacheable(value="users")
	JSONObject faceLogin(String username);
	Long findCount();
	List<Role> getRoles(int user_id);
	
//	�û�ע�ᣬ��Ҫ�û��������롢����
	JSONObject register(String username,String password,String email);
	
	JSONObject modifyPass(String username,String password,String email);
	
	JSONObject addTwo(String username,String password,String email);
	
	

}
