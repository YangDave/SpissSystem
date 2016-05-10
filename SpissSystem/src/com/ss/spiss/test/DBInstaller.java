package com.ss.spiss.test;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ss.spiss.entity.Privilege;
import com.ss.spiss.entity.Role;
import com.ss.spiss.entity.User;
import com.ss.spiss.entity.UserMapping;
import com.ss.spiss.utils.HibernateUtil;

public class DBInstaller {
	
	public static void main(String[] args)
	{

		DBInstaller installer = new DBInstaller();
		installer.install();
		HibernateUtil.sessionFactory.close();
	}

	private void install()
	{
		
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		UserBiz userBiz = ac.getBean("userBiz",UserBiz.class);
//		User user = userBiz.login("admin", "1234");
//		System.out.println(user.getUser_id()+" "+user.getUser_name());
		
		Session session = HibernateUtil.currentSession();
		Transaction tx = session.beginTransaction();
		
		Privilege privilege = new Privilege();
		privilege.setPrivilege_comment("删帖权限");
		privilege.setPrivilege_name("删帖");
		session.save(privilege);
		
		Privilege privilege2 = new Privilege();
		privilege2.setPrivilege_comment("发帖权限");
		privilege2.setPrivilege_name("发帖");
		session.save(privilege2);
		
		Role role = new Role();
		role.setRole_name("root");
		role.setRole_comment("超级超级管理员");
		role.getRole_privileges().add(privilege);
		role.getRole_privileges().add(privilege2);
		session.save(role);
		
		User user = new User();
		user.setUser_name("admin");
		user.setPassword("admin");
		session.save(user);
		
		UserMapping um = new UserMapping();
		um.getUserm_roles().add(role);
		um.setUser(user);
		session.save(um);
		
		
		tx.commit();
		
		HibernateUtil.closeSession();
//		User user = new User();
//		user.setAdd_time(new Date());
//		user.setUser_name("yang1");
//		user.setPassword("1234");
//		session.save(user);
//		
//		Role superAdmin = new Role();
//		superAdmin.setRole_name("普通成员");
//		superAdmin.setRole_comment("普通成员");
//		superAdmin.getUser().add(user);
//		session.save(superAdmin);
		
//		HibernateUtil.closeSession();
	}

}
