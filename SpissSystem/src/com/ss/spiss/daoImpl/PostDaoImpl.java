package com.ss.spiss.daoImpl;

import java.util.List;

import com.ss.common.BaseDaoImpl;
import com.ss.spiss.dao.PostDao;
import com.ss.spiss.entity.Post;

public class PostDaoImpl extends BaseDaoImpl<Post> implements PostDao{

	@Override
	public List<Post> findPostByPage(int plate_id, int page, int size) {
		
	String hql = "select p from Post p where p.posts_plate.plate_id =?0";
		
		List<Post> postList = findByPage(hql, page, size,plate_id);
		
		return postList;
	}

}
