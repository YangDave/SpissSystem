package com.ss.spiss.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

public class JSONTest {

	
	public static void main(String[] args) {
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < 5; i++) {
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("name", "yang");
			map.put("age", 5);
			list.add(map);
		}
		
		JSONArray ja = JSONArray.fromObject(list);
		System.out.println(ja);
	}
}
