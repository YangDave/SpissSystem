package com.ss.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;


@Transactional
public class BaseDaoImpl<T> implements BaseDao<T>{

	// DAO������г־û������ײ�������SessionFactory���
		private SessionFactory sessionFactory;
		// ����ע��SessionFactory�����setter����
		public void setSessionFactory(SessionFactory sessionFactory)
		{
			this.sessionFactory = sessionFactory;
		}
		public SessionFactory getSessionFactory()
		{
			return this.sessionFactory;
		}
		// ����ID����ʵ��
		@SuppressWarnings("unchecked")
		public T get(Class<T> entityClazz , Serializable id)
		{
			return (T)getSessionFactory().getCurrentSession()
				.get(entityClazz , id);
		}
		// ����ʵ��
		public Serializable save(T entity)
		{
			Serializable s = getSessionFactory().getCurrentSession()
				.save(entity);
			getSessionFactory().getCurrentSession().flush();
			return s;
		}
		// ����ʵ��
		public void saveOrUpdate(T entity)
		{
			System.out.println("save or update is execute");
			if(getSessionFactory().getCurrentSession() == null){
				System.out.println("session is null");
			}
			getSessionFactory().getCurrentSession().saveOrUpdate(entity);
			getSessionFactory().getCurrentSession().flush();
		}
		// ɾ��ʵ��
		public void delete(T entity)
		{
			getSessionFactory().getCurrentSession().delete(entity);
			getSessionFactory().getCurrentSession().flush();
		}
		// ����IDɾ��ʵ��
		public void delete(Class<T> entityClazz , Serializable id)
		{
			getSessionFactory().getCurrentSession()
				.createQuery("delete " + entityClazz.getSimpleName()
					+ " en where en.id = ?0")
				.setParameter("0" , id)
				.executeUpdate();
			getSessionFactory().getCurrentSession().flush();
		}
		// ��ȡ����ʵ��
		public List<T> findAll(Class<T> entityClazz)
		{
			return find("select en from "
				+ entityClazz.getSimpleName() + " en");
		}
		// ��ȡʵ������

		public long findCount(Class<T> entityClazz)
		{
			List<?> l = find("select count(*) from "
				+ entityClazz.getSimpleName());
			// ���ز�ѯ�õ���ʵ������
			if (l != null && l.size() == 1 )
			{
				return (Long)l.get(0);
			}
			return 0;
		}

		// ����HQL����ѯʵ��
		@SuppressWarnings("unchecked")
		protected List<T> find(String hql)
		{
			if(getSessionFactory() == null){
				System.out.println("sessionFactory null");
			}
			if(getSessionFactory().getCurrentSession()== null){
				System.out.println("sessionFactory session null");
			}
			return (List<T>)getSessionFactory().getCurrentSession()
				.createQuery(hql)
				.list();
		}
		// ���ݴ�ռλ������HQL����ѯʵ��
		@SuppressWarnings("unchecked")
		protected List<T> find(String hql , Object... params)
		{
			// ������ѯ
			Query query = getSessionFactory().getCurrentSession()
				.createQuery(hql);
			// Ϊ����ռλ����HQL������ò���
			for(int i = 0 , len = params.length ; i < len ; i++)
			{
				query.setParameter(i + "" , params[i]);
			}
			return (List<T>)query.list();
		}
		/**
		 * ʹ��hql �����з�ҳ��ѯ����
		 * @param hql ��Ҫ��ѯ��hql���
		 * @param pageNo ��ѯ��pageNoҳ�ļ�¼
		 * @param pageSize ÿҳ��Ҫ��ʾ�ļ�¼��
		 * @return ��ǰҳ�����м�¼
		 */
		@SuppressWarnings("unchecked")
		public List<T> findByPage(String hql,
			 int pageNo, int pageSize)
		{
			// ������ѯ
			return getSessionFactory().getCurrentSession()
				.createQuery(hql)
				// ִ�з�ҳ
				.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
		}
		/**
		 * ʹ��hql �����з�ҳ��ѯ����
		 * @param hql ��Ҫ��ѯ��hql���
		 * @param params ���hql��ռλ��������params���ڴ���ռλ������
		 * @param pageNo ��ѯ��pageNoҳ�ļ�¼
		 * @param pageSize ÿҳ��Ҫ��ʾ�ļ�¼��
		 * @return ��ǰҳ�����м�¼
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<T> findByPage(String hql , int pageNo, int pageSize
			, Object... params)
		{
			// ������ѯ
			Query query = getSessionFactory().getCurrentSession()
				.createQuery(hql);
			// Ϊ����ռλ����HQL������ò���
			for(int i = 0 , len = params.length ; i < len ; i++)
			{
				query.setParameter(i + "" , params[i]);
			}
			// ִ�з�ҳ�������ز�ѯ���
			return query.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
		}
		
		
		@SuppressWarnings("unchecked")
		public List<Integer> findId(String hql,Object...params) {
			
			// ������ѯ
			Query query = getSessionFactory().getCurrentSession()
				.createQuery(hql);
			// Ϊ����ռλ����HQL������ò���
			for(int i = 0 , len = params.length ; i < len ; i++)
			{
				query.setParameter(i + "" , params[i]);
			}
			return (List<Integer>)query.list();
			
			
		}
		@Override
		public void persist(T entity) {
			getSessionFactory().getCurrentSession().persist(entity);
			
		}
		@Override
		public void update(T entity) {
			getSessionFactory().getCurrentSession().update(entity);
			getSessionFactory().getCurrentSession().flush();
			
			
		}
		@Override
		public boolean update(String hql,Object...params) {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			int i = 0;
			for(Object p:params){
				query.setParameter(i+++"", p);
			}
			
			int ret = query.executeUpdate();
			getSessionFactory().getCurrentSession().flush();
			
			if(ret > 0){
				return true;
			}
			return false;
			
		}
		@SuppressWarnings("unchecked")
		@Override
		public T load(Class<T> entityClazz, Serializable id) {
			
			return (T)getSessionFactory().getCurrentSession().load(entityClazz, id);
		}
		/**
		 * ���¶������ݿ��в����ض��󸱱�
		 * 
		 */
		@SuppressWarnings("unchecked")
		@Override
		public T merge(T t) {
			
			T obj = (T) getSessionFactory().getCurrentSession().merge(t);
			getSessionFactory().getCurrentSession().flush();
			return obj;
		}
		@Override
		public T findByUniqueAttr(String hql, Object... params) {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			int i = 0;
			for(Object obj:params){
				query.setParameter(i+++"", obj);
			}
			
			@SuppressWarnings("unchecked")
			T t = (T) query.uniqueResult();
			
			return t;
		}
		@SuppressWarnings("unchecked")
		@Override
		public List<Map<String, Object>> findMapList(String hql, Object... params) {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(hql)
					.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			int i = 0;
			for(Object obj:params){
				query.setParameter(i+++"", obj);
			}
			 
			
			return (List<Map<String,Object>>)query.list();
		}
		@SuppressWarnings("unchecked")
		@Override
		public List<Map<String, Object>> findMapListByPage(String hql, int pageNo,
				int pageSize, Object... params) {
			Query query = getSessionFactory().getCurrentSession().createQuery(hql)
					.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
			int i = 0;
			for(Object obj:params){
				query.setParameter(i+++"", obj);
			}
			
			return (List<Map<String,Object>>)query.setFirstResult((pageNo - 1) * pageSize)
					.setMaxResults(pageSize)
					.list();
		}
		
		
		
	

}
