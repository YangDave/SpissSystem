package com.ss.spiss.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ss.spiss.biz.UserBiz;
import com.ss.spiss.bizImpl.UserBizImpl;

@WebServlet(name="userServlet",urlPatterns={"/servlet/userServlet"})
public class UserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3434534L;
	
	private UserBiz userBiz = new UserBizImpl();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		super.doGet(req, resp);
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String result = "";
		try {
			/* 读取数据 */
			BufferedReader br = new BufferedReader(
					new InputStreamReader((ServletInputStream) req.getInputStream(), "utf-8"));
			StringBuffer sb =new StringBuffer("");
			String temp;
			while((temp=br.readLine())!=null){
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			System.out.println("请求报文:" + result);
		} catch (Exception e) {
			result = "{err:\"error\"}";
		} finally {
			/* 返回数据 */
			
			ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			userBiz = (UserBiz) applicationContext.getBean("userBiz");
			Long i = userBiz.findCount();
			result = " "+i;
			System.out.println("返回报文:" + i);
			PrintWriter pw = resp.getWriter();
			pw.write(result);
			pw.flush();
			pw.close();
		}
	}
	
	

}
