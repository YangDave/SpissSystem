package com.ss.spiss.bizImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.test.annotation.Rollback;

import com.ss.spiss.biz.BizSupport;
import com.ss.spiss.biz.PlateBiz;
import com.ss.spiss.entity.Plate;
import com.ss.spiss.entity.UserMapping;
import com.ss.spiss.utils.MyJSONUtils;

public class PlateBizImpl extends BizSupport implements PlateBiz{


	@Override
	public JSONObject getAllPlate() {
		
		List<Plate> list = getPlateDao().findAll(Plate.class);
		JSONArray ja = MyJSONUtils.ListToJson(list, "plate_userms","plate_posts","plate_replies");
		
		return MyJSONUtils.createSuccessJson(ja);
	}

	@Override
	public JSONObject getPlateInfo(String plate_id) {
		
		Plate plate = getPlateDao().get(Plate.class, Integer.parseInt(plate_id));
		
		
		JSONObject result = MyJSONUtils.ObjectToJson(plate, "plate_posts","plate_replies","add_time","userm_roles","userm_posts"
				,"userms_plate","add_time","userMapping","password");
		
		return MyJSONUtils.createSuccessJson(result);
	}

	@Override
	@Rollback(false)
	public JSONObject addPlate(String plate_name, String plate_introduction,
			String userm_id) {
		
		Plate plate = new Plate();
		plate.setPlate_name(plate_name);
		plate.setPlate_introduction(plate_introduction);
		UserMapping um = getUserMappingDao().get(UserMapping.class, Integer.parseInt(userm_id));
		
		if(getPlateDao().isPlateExist(plate_name)){
			return MyJSONUtils.createFailureJson("板块名称已经存在");
		}
		
		if(um != null){
			if(um.getUserms_plate() != null){
				return MyJSONUtils.createFailureJson("你已经是某区版主，不能再创建板块");
			}else{
				plate.getPlate_userms().add(um);
				getPlateDao().save(plate);
				
				if(plate.getPlate_id() != 0){
					plate.getPlate_userms().add(um);
					JSONObject jo = MyJSONUtils.ObjectToJson(plate, "plate_posts","plate_replies","userm_roles","userm_posts","userMapping");
					return MyJSONUtils.createSuccessJson(jo);
				}else{
					return MyJSONUtils.createFailureJson("板块创建失败");
				}
			}
		}
		return MyJSONUtils.createFailureJson("用户ID不存在");
	}

	@Override
	public JSONObject setModerator(String plate_id, String userm_id) {
		
		Plate plate = getPlateDao().get(Plate.class, Integer.parseInt(plate_id));
		UserMapping um = getUserMappingDao().get(UserMapping.class, Integer.parseInt(userm_id));
		if(plate!=null && um!=null){
			if(um.getUserms_plate()==null){
				um.setUserms_plate(plate);
				UserMapping um2 = getUserMappingDao().merge(um);
				if(um2.getUserms_plate() != null){
					Map<String,Object> map = new HashMap<>();
					map.put("message", "设置版主成功");
					return MyJSONUtils.createSuccessJson(JSONObject.fromObject(map));
				}else{
					return MyJSONUtils.createFailureJson("设置版主失败");
				}
			}else{
				return MyJSONUtils.createFailureJson("目标用户已经是版主，不能在担当一个板块版主");
			}
			
			
		}else{
			return MyJSONUtils.createFailureJson("板块ID或用户ID不存在");
		}
		
		
	}


}
