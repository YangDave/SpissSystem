package com.ss.spiss.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ss.spiss.jaxb.JaxbMethod;
import com.ss.spiss.jaxb.JaxbMethods;
import com.ss.spiss.jaxb.JaxbReadXml;
import com.ss.spiss.utils.MyJSONUtils;

@WebServlet(name="myServlet",urlPatterns={"/servlet/myServlet"})
public class MyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 234135324234L;
	public JaxbMethods jaxbMethods = null;


	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("-----------init----------");
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		JSONObject respJson = null;
		JSONObject reqJson = null;
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json;charSet=UTF-8");
		
		
		jaxbMethods = checkJaxbMethods(req);

		try {
			/* 读取数据 */
			String reqStr = readStream(req);

			//解析string获取其中json
			reqJson = decodeJson(reqStr);

		} catch (Exception e) {
			e.printStackTrace();
			wrongFormat(resp);
//			不正确的数据格式不予处理
			return;
		}

		PrintStream ps = new PrintStream(resp.getOutputStream());
		try{
			respJson = executeBiz(reqJson);

		}catch(Exception e){
			e.printStackTrace();
			respJson = MyJSONUtils.createErrorJson("非法请求");

		}finally{
			System.out.println("返回报文:"+respJson);
			System.out.println(">-----------------------处理结束-----------------------<  \n");
			ps.write(respJson.toString().getBytes("UTF-8"));
			ps.flush();
			ps.close();
		}
		

	}


	private void wrongFormat(HttpServletResponse resp) throws IOException,
			UnsupportedEncodingException {
		JSONObject respJson;
		PrintStream out = new PrintStream(resp.getOutputStream());
		respJson = MyJSONUtils.createErrorJson("数据格式不正确");
		out.write(respJson.toString().getBytes("UTF-8"));
		out.flush();
		out.close();
		System.out.println(">----------数据格式不正确-----------<");
		System.out.println(">----------处理结束---------------<");
	}
/*
 * 读取数据流
 */
	private String readStream(HttpServletRequest req) throws UnsupportedEncodingException, IOException{
		BufferedReader br = new BufferedReader(
				new InputStreamReader((ServletInputStream) req.getInputStream(), "utf-8"));
		StringBuffer sb =new StringBuffer("");
		String temp;
		while((temp=br.readLine())!=null){
			sb.append(temp);
		}
		br.close();
		return sb.toString();
	}
	
/*
 * 	检查jaxbMethods是否为空，若为空则创建一个
 */
	private JaxbMethods checkJaxbMethods(HttpServletRequest req){
		
		if(jaxbMethods == null){
			ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			JaxbReadXml jaxbReadXml = applicationContext.getBean("jaxbReadXml", JaxbReadXml.class);
			jaxbMethods = jaxbReadXml.getJaxbMethods();
			
		}
		
		return jaxbMethods;
		
		
	}

/*
 *   通过查找XML文件来获取需要执行的方法，类，以及所需参赛
 */
	private JSONObject executeBiz(JSONObject reqJson) throws ClassNotFoundException, NoSuchMethodException,
	SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

		String func = reqJson.optString("func");
		System.out.println(">----------------- 正在处理业务: "+func+" ---------------------< \n");
		JaxbMethod bizMethod = jaxbMethods.getMethodByName(func);
		Class<?> bizClass = Class.forName(bizMethod.getBizClass());
		
//		从spring中获取bean
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		Object bizObject = applicationContext.getBean(bizClass);

		Class<?>[] classArray = new Class<?>[bizMethod.size()];
		Object[] para = new Object[bizMethod.size()];
		for(int i = 0;i < bizMethod.size();i++){
			classArray[i] = String.class;
			para[i] = reqJson.opt(bizMethod.get(i));
		}
		Method method = bizClass.getMethod(func, classArray);
		JSONObject respJson = (JSONObject)method.invoke(bizObject, para);
		return respJson;

	}
/*
 * 解析请求数据
 */
	public JSONObject decodeJson(String str) throws UnsupportedEncodingException{
		String decodeStr = URLDecoder.decode(str, "UTF-8");
		System.out.println("请求报文:" + decodeStr);
		String[] strs = decodeStr.split("=",2);
		JSONObject json = JSONObject.fromObject(strs[1]);
		return json;
	}



}
