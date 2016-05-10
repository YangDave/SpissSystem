package com.ss.spiss.biz;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import net.sf.json.JSONObject;

public interface PostBiz {
	
	@Cacheable(value="posts")
	JSONObject findPostByPage(String plate_id,String page,String size);
	@CacheEvict(value="posts",allEntries=true)
	JSONObject newPost(String title,String content,String userm_id,String plate_id);

}
