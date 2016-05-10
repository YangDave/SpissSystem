package com.ss.spiss.test;


import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import net.sf.json.JSONObject;

import com.ss.common.BaseServlet;
import com.ss.spiss.biz.UserBiz;

@WebServlet(name="register",urlPatterns={"/servlet/register"})
public class Register extends BaseServlet<UserBiz>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 43523623452345L;

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintStream ps = new PrintStream(res.getOutputStream());
		UserBiz userBiz = getObject("userBiz");
		respJson = userBiz.register(reqJson.optString("username"), reqJson.optString("password"), reqJson.optString("email"));
		
		System.out.println("·µ»Ø±¨ÎÄ:"+respJson);
		
		ps.write(respJson.toString().getBytes("UTF-8"));
		ps.flush();
		ps.close();
		
	}
	
	

}
