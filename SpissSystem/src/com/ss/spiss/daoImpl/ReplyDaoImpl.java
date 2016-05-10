package com.ss.spiss.daoImpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.ss.common.BaseDaoImpl;
import com.ss.spiss.dao.ReplyDao;
import com.ss.spiss.entity.Reply;

public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements ReplyDao{

	@Override
	public List<Reply> findRepliesByPageAndPlate(int post_id, int page,
			int size) {
		
		String hql = "select r from Reply r where r.replies_post.post_id =?0";

		List<Reply> list = findByPage(hql, page, size, post_id);
		return list;
	}

//	@SuppressWarnings("unchecked")
//	public List<Map<String,Object>> findRepliesByPageAndPlate(String hql,int page,int size,Object...params){
//		Query query = getSessionFactory().getCurrentSession()
//				.createQuery(hql);
//		// 为包含占位符的HQL语句设置参数
//		for(int i = 0 , len = params.length ; i < len ; i++)
//		{
//			query.setParameter(i + "" , params[i]);
//		}
//
//		return (List<Map<String,Object>>)(query.setFirstResult((page - 1) * size)
//				.setMaxResults(size)
//				.list());
//
//	}

}
