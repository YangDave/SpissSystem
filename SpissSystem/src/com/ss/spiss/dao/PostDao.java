package com.ss.spiss.dao;

import java.util.List;

import com.ss.common.BaseDao;
import com.ss.spiss.entity.Post;

public interface PostDao extends BaseDao<Post>{
	
	List<Post> findPostByPage(int plate_id,int page,int size);

}
