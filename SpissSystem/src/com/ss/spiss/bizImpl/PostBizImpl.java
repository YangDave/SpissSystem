package com.ss.spiss.bizImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ss.spiss.biz.BizSupport;
import com.ss.spiss.biz.PostBiz;
import com.ss.spiss.entity.Plate;
import com.ss.spiss.entity.Post;
import com.ss.spiss.entity.UserMapping;
import com.ss.spiss.utils.MyJSONUtils;
import com.ss.spiss.utils.MyTimeFormat;

public class PostBizImpl extends BizSupport implements PostBiz{

	@Override
	public JSONObject findPostByPage(String plate_id, String page, String size) {
		
	
		List<Post> list = getPostDao().findPostByPage(Integer.parseInt(plate_id),
				Integer.parseInt(page), Integer.parseInt(size));
		
		List<Map<String,Object>> listMap = new ArrayList<>();
		for(Post p:list){

			Map<String,Object> map = new HashMap<>();
			map.put("post_id", p.getPost_id());
			map.put("lastReplyDate", MyTimeFormat.dateTimeFormat(p.getLastReplyDate()));
			map.put("launch_date", MyTimeFormat.dateTimeFormat(p.getLaunch_date()));
			map.put("content", p.getContent());
			map.put("title", p.getTitle());
			map.put("userm_id", p.getPosts_userm().getUserm_id());
			map.put("user_name", p.getPosts_userm().getUser().getUser_name());
			
			listMap.add(map);
		}
		
		JSONArray ja = JSONArray.fromObject(listMap);
//		JSONArray ja = MyJSONUtils.ListToJson(list, "lastUserm","userm_posts","userm_replies","userms_plate","userm_roles","userMapping"
//				,"post_replies","posts_plate","replyDate","add_time");
		
		return MyJSONUtils.createSuccessJson(ja);
	}

	@Override
	public JSONObject newPost(String title, String content, String userm_id,
			String plate_id) {
		
		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);
		Plate plate = getPlateDao().get(Plate.class, Integer.parseInt(plate_id));
		UserMapping um = getUserMappingDao().get(UserMapping.class, Integer.parseInt(userm_id));
		post.setPosts_plate(plate);
		post.setPosts_userm(um);
		post.setLastReplyDate(new Date());
		getPostDao().saveOrUpdate(post);
		
		
		JSONObject result = MyJSONUtils.ObjectToJson(post, "lastUserm","posts_plate","posts_userm","post_replies");
		
		return MyJSONUtils.createSuccessJson(result);
	}

}
