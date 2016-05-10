package com.ss.spiss.biz;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import net.sf.json.JSONObject;

public interface ReplyBiz {
	
	@CacheEvict(value="replies",allEntries=true)
	JSONObject addReply(String userm_id,String post_id,String replyContent);
	@Cacheable(value="replies")
	JSONObject getRepliesByPage(String post_id,String page,String size);

}
