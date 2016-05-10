package com.ss.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ss.spiss.utils.MyJSONUtils;

public class BaseServlet<T> extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 234135324234L;
	
	protected JSONObject reqJson;
	protected JSONObject respJson;
	
	public T getObject(String beanId){
		
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		 @SuppressWarnings("unchecked")
		T t = (T) applicationContext.getBean(beanId);
		return t;
	}
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		reqJson = null;
		respJson = null;
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json;charSet=UTF-8");
		
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
			
//			解析json
			reqJson = decodeJson(sb.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			PrintStream out = new PrintStream(resp.getOutputStream());
			respJson = MyJSONUtils.createErrorJson("未知错误");
			out.write(respJson.toString().getBytes("UTF-8"));
			out.flush();
			out.close();
			return;
		}
		
		
	}
	
	public JSONObject decodeJson(String str) throws UnsupportedEncodingException{
		String decodeStr = URLDecoder.decode(str, "UTF-8");
		System.out.println("请求报文:" + decodeStr);
		String[] strs = decodeStr.split("=",2);
		JSONObject json = JSONObject.fromObject(strs[1]);
		System.out.println(json.optString("phone")+" "+json.optString("name"));
		return json;
	}
	
	
	
	

}
