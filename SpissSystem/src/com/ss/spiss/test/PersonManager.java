package com.ss.spiss.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ss.spiss.biz.UserBiz;
import com.ss.spiss.utils.HibernateUtil;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class PersonManager
{
	public static void main(String[] args)
	{
		PersonManager mgr = new PersonManager();
		mgr.testPerson();
		HibernateUtil.sessionFactory.close();
	}

	private void testPerson()
	{
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserBiz userBiz = ac.getBean("userBiz",UserBiz.class);
		System.out.println(userBiz.login("admin", "1234"));
		
//		PlateDao plateDao = ac.getBean("plateDao",PlateDao.class);
//		UserDao userDao = ac.getBean("userDao",UserDao.class);
//		UserMappingDao umDao = ac.getBean("userMappingDao",UserMappingDao.class);
//		
//		PlateBiz plateBiz = ac.getBean("plateBiz",PlateBiz.class);
//		plateBiz.addPlate("fdasfa", "dasdfs", "6");
		
//		UserMapping um = umDao.get(UserMapping.class, 6);
//		Plate p = plateDao.get(Plate.class, 8);
//		
//		if(um != null){
//			um.setUserms_plate(p);
//		}
//		
//		umDao.save(um);
		
//		User user = 
		
//		plateDao.update(entity);
//		
//		Session session = 
		
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
