package com.ss.spiss.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbMethod extends ArrayList<String>{
	
	
	@XmlAttribute(name="name")
	public String methodName;
	
	@XmlAttribute(name="class")
	public String bizClass;
	
	@XmlElement(name="property")
	public List<String> getPropertys() {
		return this;
	}
	
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getBizClass() {
		return bizClass;
	}

	public void setBizClass(String bizClass) {
		this.bizClass = bizClass;
	}
	
	
	

}
