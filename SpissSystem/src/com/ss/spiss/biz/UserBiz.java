package com.ss.spiss.biz;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import net.sf.json.JSONObject;

import com.ss.spiss.entity.Role;

public interface UserBiz {
	
//	登录
	@Cacheable(value="users")
	JSONObject login(String username,String password);
//	人脸登录
	@Cacheable(value="users")
	JSONObject faceLogin(String username);
	Long findCount();
	List<Role> getRoles(int user_id);
	
//	用户注册，需要用户名、密码、邮箱
	JSONObject register(String username,String password,String email);
	
	JSONObject modifyPass(String username,String password,String email);
	
	JSONObject addTwo(String username,String password,String email);
	
	

}
