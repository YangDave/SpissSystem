package com.ss.spiss.biz;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import net.sf.json.JSONObject;

public interface PlateBiz {

	@Cacheable(value="plates")
	JSONObject getAllPlate();
	@Cacheable(value="plates")
	JSONObject getPlateInfo(String plate_id);
	@CacheEvict(value="plates",allEntries=true)
	JSONObject addPlate(String plate_name,String plate_introduction,String userm_id);
	@CacheEvict(value="plates",key="#plate_id")
	JSONObject setModerator(String plate_id,String userm_id);
	
}
