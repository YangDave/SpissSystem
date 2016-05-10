package com.ss.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

public interface BaseDao<T> {
	
	// ����ID����ʵ��
		T get(Class<T> entityClazz , Serializable id);
		// ����ʵ��
		Serializable save(T entity);
		// ����ʵ��
		void saveOrUpdate(T entity);
		void update(T entity);
		// ɾ��ʵ��
		void delete(T entity);
		// ����IDɾ��ʵ��
		void delete(Class<T> entityClazz , Serializable id);
		// ��ȡ����ʵ��
		List<T> findAll(Class<T> entityClazz);
		// ��ȡʵ������
		long findCount(Class<T> entityClazz);
		List<T> findByPage(String hql , int pageNo, int pageSize, Object... params);
		List<T> findByPage(String hql,int pageNo, int pageSize);
		
		void persist(T entity);

		boolean update(String hql,Object...params);
		
		SessionFactory getSessionFactory();
//		ͨ��id����һ������
		T load(Class<T> entityClazz,Serializable id);
//		merge�󷵻ض��󸱱�
		T merge(T t);
//		ͨ��Ψһ���Բ��Ҷ���
		T findByUniqueAttr(String hql,Object...params);
		
		List<Integer> findId(String hql,Object...params);
		
		List<Map<String,Object>> findMapList(String hql,Object...params);
		
		List<Map<String,Object>> findMapListByPage(String hql,int pageNo,int pageSize,Object...params);
		
		
}
