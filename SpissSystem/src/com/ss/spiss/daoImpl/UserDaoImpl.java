package com.ss.spiss.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import com.ss.common.BaseDaoImpl;
import com.ss.spiss.dao.UserDao;
import com.ss.spiss.entity.User;


@Transactional
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public User query(String username, String password) {

		String hql = "select u from User u where u.user_name = ?0 and u.password = ?1";
		
		User u = findByUniqueAttr(hql, username,password);
//		以下也可行
//		getSessionFactory().getCurrentSession()
//		.createQuery("select u from User u where u.user_name = :username and u.password = :password").setParameter("username", username)
//		.setParameter("password", password).uniqueResult();

		return u;
	}

	@Override
	public boolean isUsernameExist(String username) {
		
		String hql = "select u.user_id from User u where u.user_name = ?0";
		Object obj = getSessionFactory().getCurrentSession().createQuery(hql).setParameter("0", username).uniqueResult();
		if(obj != null){
			return true;
		}
		return false;
	}

	@Override
	public User getUser(String username) {
		
		String hql = "from User u where u.user_name = ?";
		User user = (User) getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameter(0, username).uniqueResult();
		return user;
	}


}
