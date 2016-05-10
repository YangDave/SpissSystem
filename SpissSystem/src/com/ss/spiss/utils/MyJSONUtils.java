package com.ss.spiss.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

/**
 * @author Charles
 *
 */
public class MyJSONUtils {

	/*
	 * 创建请求结果，
	 * code = 1 请求成功，不管用户是否获取到期望的结果
	 * code = 0 非法请求，如数据格式不正确或为空，以及导致各种异常的请求
	 */
	private static JSONObject createRespJson(int code,Object result){
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("code", code);
		map.put("result", result);
		return JSONObject.fromObject(map);
	}

	public static JSONObject mapToJson(Map<String,Object> map){
		return JSONObject.fromObject(map);
	}

	public  static JSONObject createSuccessJson(Object object){
		return createRespJson(1, object);
	}

	//	请求成功，但用户请求未获得期望的结果  code = 2
	public static JSONObject createFailureJson(String message){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("message", message);
		return createRespJson(2, JSONObject.fromObject(map));

	}

	//	非法请求 code = 0
	public static JSONObject createErrorJson(String message){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("message", message);
		return createRespJson(0, JSONObject.fromObject(map));
	}

	public static <T> JSONObject ObjectToJson(T t,String...params){
		JsonConfig config = new JsonConfig();
		String[] p2 = ArrayAddPassExcludes(params);
		config.setExcludes(p2);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());  
		JSONObject jo = JSONObject.fromObject(t,config);

		return jo;

	}
	private static String[] ArrayAddPassExcludes(String... params) {
		String[] p2 = new String[params.length + 1];
		for (int i = 0; i < params.length; i++) {
			p2[i]= params[i];

		}
		p2[params.length] = "password";
		return p2;
	}

	public static <T> JSONArray ListToJson(List<T> list,String...params){
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());  
		String[] p2 =ArrayAddPassExcludes(params);
		config.setExcludes(p2);
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray ja = JSONArray.fromObject(list,config);
		return ja;
	}


}
