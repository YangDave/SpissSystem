package com.ss.spiss.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.ss.common.BaseServlet;
import com.ss.spiss.biz.UserBiz;

@WebServlet(name="modifyPassword",urlPatterns={"/servlet/modifyPassword"})
public class ModifyPassword extends BaseServlet<UserBiz> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6758467567L;

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		
		
	}
	
	

}
