package com.ss.spiss.test;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.ss.common.BaseServlet;
import com.ss.spiss.biz.UserBiz;
import com.ss.spiss.entity.User;
import com.ss.spiss.utils.MyJSONUtils;

@WebServlet(name="login",urlPatterns={"/servlet/login"})
public class Login extends BaseServlet<UserBiz>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 324252343L;

	@Override
	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
		super.service(req, resp);
		
		PrintStream ps = new PrintStream(resp.getOutputStream());
		UserBiz userBiz = getObject("userBiz");
		respJson = userBiz.login(reqJson.optString("username"), reqJson.optString("password"));
		
		System.out.println("·µ»Ø±¨ÎÄ:"+respJson);
		
		ps.write(respJson.toString().getBytes("UTF-8"));
		ps.flush();
		ps.close();
		
	}
	

}
