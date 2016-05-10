package com.ss.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

public interface BaseDao<T> {
	
	// 根据ID加载实体
		T get(Class<T> entityClazz , Serializable id);
		// 保存实体
		Serializable save(T entity);
		// 更新实体
		void saveOrUpdate(T entity);
		void update(T entity);
		// 删除实体
		void delete(T entity);
		// 根据ID删除实体
		void delete(Class<T> entityClazz , Serializable id);
		// 获取所有实体
		List<T> findAll(Class<T> entityClazz);
		// 获取实体总数
		long findCount(Class<T> entityClazz);
		List<T> findByPage(String hql , int pageNo, int pageSize, Object... params);
		List<T> findByPage(String hql,int pageNo, int pageSize);
		
		void persist(T entity);

		boolean update(String hql,Object...params);
		
		SessionFactory getSessionFactory();
//		通过id加载一个对象
		T load(Class<T> entityClazz,Serializable id);
//		merge后返回对象副本
		T merge(T t);
//		通过唯一属性查找对象
		T findByUniqueAttr(String hql,Object...params);
		
		List<Integer> findId(String hql,Object...params);
		
		List<Map<String,Object>> findMapList(String hql,Object...params);
		
		List<Map<String,Object>> findMapListByPage(String hql,int pageNo,int pageSize,Object...params);
		
		
}
