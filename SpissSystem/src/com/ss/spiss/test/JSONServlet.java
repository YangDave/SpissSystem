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

@WebServlet(name="JSONServlet",urlPatterns={"/JSONServlet"})
public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String result = "";
		try {
			/* ��ȡ���� */
			BufferedReader br = new BufferedReader(
					new InputStreamReader((ServletInputStream) req.getInputStream(), "utf-8"));
			StringBuffer sb =new StringBuffer("");
			String temp;
			while((temp=br.readLine())!=null){
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			System.out.println("������:" + result);
		} catch (Exception e) {
			result = "{err:\"error\"}";
		} finally {
			/* �������� */
			System.out.println("���ر���:" + result);
			PrintWriter pw = resp.getWriter();
			pw.write(result);
			pw.flush();
			pw.close();
		}
	}
}
 