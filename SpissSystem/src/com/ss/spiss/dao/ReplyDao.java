package com.ss.spiss.dao;

import java.util.List;
import java.util.Map;

import com.ss.common.BaseDao;
import com.ss.spiss.entity.Reply;

public interface ReplyDao extends BaseDao<Reply>{
	
	List<Reply> findRepliesByPageAndPlate(int post_id,int page,int size);

}
