package com.ss.spiss.bizImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ss.spiss.biz.BizSupport;
import com.ss.spiss.biz.ReplyBiz;
import com.ss.spiss.entity.Post;
import com.ss.spiss.entity.Reply;
import com.ss.spiss.entity.UserMapping;
import com.ss.spiss.utils.MyJSONUtils;
import com.ss.spiss.utils.MyTimeFormat;

public class ReplyBizImpl extends BizSupport implements ReplyBiz{

	@Override
	public JSONObject addReply(String userm_id, String post_id, String replyContent) {
		
		UserMapping um = getUserMappingDao().get(UserMapping.class, Integer.parseInt(userm_id));
		Post post = getPostDao().get(Post.class, Integer.parseInt(post_id));
		
		Reply reply = new Reply();
		reply.setReplyDate(new Date());
		reply.setReplies_userm(um);
		reply.setReplies_post(post);
		reply.setReplyContent(replyContent);
		
		post.setLastReplyDate(new Date());
		
		getPostDao().saveOrUpdate(post);
		getReplyDao().save(reply);
		
//		Reply r2= getReplyDao().get(Reply.class, reply.getReply_id());
		
		if(reply.getReply_id() != 0){
			JSONObject result = MyJSONUtils.ObjectToJson(reply, "replies_post","userm_replies","userm_posts","userms_plate","userm_roles","userMapping");
			
			return MyJSONUtils.createSuccessJson(result);
		}
		
		return MyJSONUtils.createFailureJson("发表回复失败");
	}

	@Override
	public JSONObject getRepliesByPage(String post_id, String page, String size) {
		
	
		Post post = getPostDao().get(Post.class, Integer.parseInt(post_id));
		if(post == null){
			return MyJSONUtils.createFailureJson("post_id不存在");
		}
		
		List<Reply> list = getReplyDao().findRepliesByPageAndPlate(Integer.parseInt(post_id), 
				Integer.parseInt(page), Integer.parseInt(size));
		
		List<Map<String,Object>> listMap = new ArrayList<>();
		for(Reply r:list){

			Map<String,Object> map = new HashMap<>();
			map.put("replyContent", r.getReplyContent());
			map.put("replyDate", MyTimeFormat.dateTimeFormat(r.getReplyDate()));
			map.put("userm_id", r.getReplies_userm().getUserm_id());
			map.put("user_name", r.getReplies_userm().getUser().getUser_name());
			listMap.add(map);
		}
		
		JSONArray result = JSONArray.fromObject(listMap);
		
		return MyJSONUtils.createSuccessJson(result);
	}

}
