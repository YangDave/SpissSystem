package com.ss.spiss.bizImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.cache.annotation.Cacheable;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.ss.spiss.biz.BizSupport;
import com.ss.spiss.biz.UserBiz;
import com.ss.spiss.entity.Role;
import com.ss.spiss.entity.User;
import com.ss.spiss.entity.UserMapping;
import com.ss.spiss.utils.MyJSONUtils;

public class UserBizImpl extends BizSupport implements UserBiz{


	@Override
	public Long findCount() {

		return  getUserDao().findCount(User.class);
	}

	@Override
	public List<Role> getRoles(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}



	
	@Override
	public JSONObject login(String username, String password) {
		User user = getUserDao().query(username, password);

		JSONObject json = null;

		if(user == null){

			json = MyJSONUtils.createFailureJson("用户名或密码不正确");

		}else{
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("user_id", user.getUser_id());
			map.put("user_name",user.getUser_name());
			map.put("userm_id", user.getUserMapping().getUserm_id());

			json = MyJSONUtils.createSuccessJson(JSONObject.fromObject(map));
		}

		return json;
	}



	@Override
	public JSONObject register(String username, String password, String email) {
		
		if(!validate(username,password,email)){
			
			
			return MyJSONUtils.createFailureJson("输入信息不规范");
		}
		if(getUserDao().isUsernameExist(username)){
			return MyJSONUtils.createFailureJson("用户名已存在");
		}
		User user = new User();
		user.setUser_name(username);
		user.setAdd_time(new Date());
		user.setPassword(password);
		user.setEmail(email);
		
		getUserDao().save(user);
		
		System.out.println("===========user_id:"+user.getUser_id());
		
//		创建用户集合表
		UserMapping um = new UserMapping();
		um.setUser(user);
		getUserMappingDao().save(um);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user.getUser_id());
		map.put("user_name",user.getUser_name());
		map.put("userm_id", um.getUserm_id());
		
		
		return MyJSONUtils.createSuccessJson(JSONObject.fromObject(map));
	}
	
	
	private boolean validate(String...params){
//		TODO
		return true;
	}

	@Override
	public JSONObject modifyPass(String username, String password, String email) {
		
		User user;
		if(!validate(username,password,email)){
			return MyJSONUtils.createFailureJson("输入信息不规范");
		}else{
			user = getUserDao().getUser(username);
			if(user == null){
				return MyJSONUtils.createFailureJson("用户名不存在");
			}else{
				if(user.getEmail().equals(email)){
					user.setPassword(password);
					JSONObject returnJson;
					try{  
						getUserDao().saveOrUpdate(user);
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("user_id", user.getUser_id());
						map.put("user_name",user.getUser_name());
						map.put("userm_id", user.getUserMapping().getUserm_id());
						returnJson = MyJSONUtils.createSuccessJson(JSONObject.fromObject(map));
					}catch(Exception e){
						e.printStackTrace();
						returnJson = MyJSONUtils.createFailureJson("密码修改失败");
					}
					
					return returnJson;
					
				}else{
					return MyJSONUtils.createFailureJson("邮箱不正确");
				}
			}
			
		}
		
	}

	
	@Override
	public JSONObject faceLogin(String username) {

		String hql = "from User u where u.user_name = ?0";
//		User user = getUserDao().getUser(username);
		User user = getUserDao().findByUniqueAttr(hql, username);
		if(user == null){
			return MyJSONUtils.createFailureJson("用户名不存在");
		}
		Map<String,Object> map = new HashMap<>();
		map.put("user_id", user.getUser_id());
		map.put("user_name", user.getUser_name());
		map.put("userm_id", user.getUserMapping().getUserm_id());
		
		return MyJSONUtils.createSuccessJson(JSONObject.fromObject(map));
	}

	@Override
	public JSONObject addTwo(String username, String password, String email) {
		
		register(username, password, email);
		JSONObject j = register(username, password, email);
		return j;
	}

}
