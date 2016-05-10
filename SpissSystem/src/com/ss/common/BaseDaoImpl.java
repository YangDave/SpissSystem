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

	// DAO组件进行持久化操作底层依赖的SessionFactory组件
		private SessionFactory sessionFactory;
		// 依赖注入SessionFactory所需的setter方法
		public void setSessionFactory(SessionFactory sessionFactory)
		{
			this.sessionFactory = sessionFactory;
		}
		public SessionFactory getSessionFactory()
		{
			return this.sessionFactory;
		}
		// 根据ID加载实体
		@SuppressWarnings("unchecked")
		public T get(Class<T> entityClazz , Serializable id)
		{
			return (T)getSessionFactory().getCurrentSession()
				.get(entityClazz , id);
		}
		// 保存实体
		public Serializable save(T entity)
		{
			Serializable s = getSessionFactory().getCurrentSession()
				.save(entity);
			getSessionFactory().getCurrentSession().flush();
			return s;
		}
		// 更新实体
		public void saveOrUpdate(T entity)
		{
			System.out.println("save or update is execute");
			if(getSessionFactory().getCurrentSession() == null){
				System.out.println("session is null");
			}
			getSessionFactory().getCurrentSession().saveOrUpdate(entity);
			getSessionFactory().getCurrentSession().flush();
		}
		// 删除实体
		public void delete(T entity)
		{
			getSessionFactory().getCurrentSession().delete(entity);
			getSessionFactory().getCurrentSession().flush();
		}
		// 根据ID删除实体
		public void delete(Class<T> entityClazz , Serializable id)
		{
			getSessionFactory().getCurrentSession()
				.createQuery("delete " + entityClazz.getSimpleName()
					+ " en where en.id = ?0")
				.setParameter("0" , id)
				.executeUpdate();
			getSessionFactory().getCurrentSession().flush();
		}
		// 获取所有实体
		public List<T> findAll(Class<T> entityClazz)
		{
			return find("select en from "
				+ entityClazz.getSimpleName() + " en");
		}
		// 获取实体总数

		public long findCount(Class<T> entityClazz)
		{
			List<?> l = find("select count(*) from "
				+ entityClazz.getSimpleName());
			// 返回查询得到的实体总数
			if (l != null && l.size() == 1 )
			{
				return (Long)l.get(0);
			}
			return 0;
		}

		// 根据HQL语句查询实体
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
		// 根据带占位符参数HQL语句查询实体
		@SuppressWarnings("unchecked")
		protected List<T> find(String hql , Object... params)
		{
			// 创建查询
			Query query = getSessionFactory().getCurrentSession()
				.createQuery(hql);
			// 为包含占位符的HQL语句设置参数
			for(int i = 0 , len = params.length ; i < len ; i++)
			{
				query.setParameter(i + "" , params[i]);
			}
			return (List<T>)query.list();
		}
		/**
		 * 使用hql 语句进行分页查询操作
		 * @param hql 需要查询的hql语句
		 * @param pageNo 查询第pageNo页的记录
		 * @param pageSize 每页需要显示的记录数
		 * @return 当前页的所有记录
		 */
		@SuppressWarnings("unchecked")
		public List<T> findByPage(String hql,
			 int pageNo, int pageSize)
		{
			// 创建查询
			return getSessionFactory().getCurrentSession()
				.createQuery(hql)
				// 执行分页
				.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
		}
		/**
		 * 使用hql 语句进行分页查询操作
		 * @param hql 需要查询的hql语句
		 * @param params 如果hql带占位符参数，params用于传入占位符参数
		 * @param pageNo 查询第pageNo页的记录
		 * @param pageSize 每页需要显示的记录数
		 * @return 当前页的所有记录
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<T> findByPage(String hql , int pageNo, int pageSize
			, Object... params)
		{
			// 创建查询
			Query query = getSessionFactory().getCurrentSession()
				.createQuery(hql);
			// 为包含占位符的HQL语句设置参数
			for(int i = 0 , len = params.length ; i < len ; i++)
			{
				query.setParameter(i + "" , params[i]);
			}
			// 执行分页，并返回查询结果
			return query.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize)
				.list();
		}
		
		
		@SuppressWarnings("unchecked")
		public List<Integer> findId(String hql,Object...params) {
			
			// 创建查询
			Query query = getSessionFactory().getCurrentSession()
				.createQuery(hql);
			// 为包含占位符的HQL语句设置参数
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
		 * 更新对象到数据库中并返回对象副本
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
