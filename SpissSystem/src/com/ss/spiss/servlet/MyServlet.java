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
			/* ��ȡ���� */
			String reqStr = readStream(req);

			//����string��ȡ����json
			reqJson = decodeJson(reqStr);

		} catch (Exception e) {
			e.printStackTrace();
			wrongFormat(resp);
//			����ȷ�����ݸ�ʽ���账��
			return;
		}

		PrintStream ps = new PrintStream(resp.getOutputStream());
		try{
			respJson = executeBiz(reqJson);

		}catch(Exception e){
			e.printStackTrace();
			respJson = MyJSONUtils.createErrorJson("�Ƿ�����");

		}finally{
			System.out.println("���ر���:"+respJson);
			System.out.println(">-----------------------�������-----------------------<  \n");
			ps.write(respJson.toString().getBytes("UTF-8"));
			ps.flush();
			ps.close();
		}
		

	}


	private void wrongFormat(HttpServletResponse resp) throws IOException,
			UnsupportedEncodingException {
		JSONObject respJson;
		PrintStream out = new PrintStream(resp.getOutputStream());
		respJson = MyJSONUtils.createErrorJson("���ݸ�ʽ����ȷ");
		out.write(respJson.toString().getBytes("UTF-8"));
		out.flush();
		out.close();
		System.out.println(">----------���ݸ�ʽ����ȷ-----------<");
		System.out.println(">----------�������---------------<");
	}
/*
 * ��ȡ������
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
 * 	���jaxbMethods�Ƿ�Ϊ�գ���Ϊ���򴴽�һ��
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
 *   ͨ������XML�ļ�����ȡ��Ҫִ�еķ������࣬�Լ��������
 */
	private JSONObject executeBiz(JSONObject reqJson) throws ClassNotFoundException, NoSuchMethodException,
	SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

		String func = reqJson.optString("func");
		System.out.println(">----------------- ���ڴ���ҵ��: "+func+" ---------------------< \n");
		JaxbMethod bizMethod = jaxbMethods.getMethodByName(func);
		Class<?> bizClass = Class.forName(bizMethod.getBizClass());
		
//		��spring�л�ȡbean
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
 * ������������
 */
	public JSONObject decodeJson(String str) throws UnsupportedEncodingException{
		String decodeStr = URLDecoder.decode(str, "UTF-8");
		System.out.println("������:" + decodeStr);
		String[] strs = decodeStr.split("=",2);
		JSONObject json = JSONObject.fromObject(strs[1]);
		return json;
	}



}
