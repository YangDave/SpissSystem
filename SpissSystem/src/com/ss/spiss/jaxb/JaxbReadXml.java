package com.ss.spiss.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.MessageFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.context.support.ServletContextResource;

public class JaxbReadXml {
	
	private JaxbMethods jaxbMethods;
	
	public JaxbReadXml(){
		
		try {
			ClassPathResource cr = new ClassPathResource("jaxb-servlet.xml");
			File file = cr.getFile();
			System.out.println("path:"+cr.getPath());
//			
			setJaxbMethods(JaxbReadXml.readFile(JaxbMethods.class,file));
		} catch (JAXBException | MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
    public JaxbMethods getJaxbMethods() {
		return jaxbMethods;
	}

	public void setJaxbMethods(JaxbMethods jaxbMethods) {
		this.jaxbMethods = jaxbMethods;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T readFile(Class<T> clazz,File file) throws JAXBException{
		
		  try {
	            JAXBContext jc = JAXBContext.newInstance(clazz);
	            Unmarshaller u = jc.createUnmarshaller();
	            return (T) u.unmarshal(file);
	        } catch (JAXBException e) {
	            // logger.trace(e);
	            throw e;
	        }
	}

	@SuppressWarnings("unchecked")
    public static <T> T readString(Class<T> clazz, String context) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(new File(context));
        } catch (JAXBException e) {
            // logger.trace(e);
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T readConfig(Class<T> clazz, String config, Object... arguments) throws IOException,
            JAXBException {
        InputStream is = null;
        try {
            if (arguments.length > 0) {
                config = MessageFormat.format(config, arguments);
            }
            // logger.trace("read configFileName=" + config);
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            is = new FileInputStream(config);
            return (T) u.unmarshal(is);
        } catch (IOException e) {
            // logger.trace(config, e);
            throw e;
        } catch (JAXBException e) {
            // logger.trace(config, e);
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T readConfigFromStream(Class<T> clazz, InputStream dataStream) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(clazz);
            Unmarshaller u = jc.createUnmarshaller();
            return (T) u.unmarshal(dataStream);
        } catch (JAXBException e) {
            // logger.trace(e);
            throw e;
        }
    }

    public static void main(String[] args) throws JAXBException {
//        TestOrgs testOrgs = JaxbReadXml.readString(TestOrgs.class, "src/jaxb.xml");
//        System.out.println(testOrgs.getSize());
//        System.out.println(testOrgs.getBatchNumber());
//        System.out.println(testOrgs.getErrmsg());
//        for (TestOrg o : testOrgs) {
//            System.out.println(o.getOrgName());
//        }
    	
    	JaxbMethods jaxbMethods = JaxbReadXml.readString(JaxbMethods.class,"src/jaxb-servlet.xml");
    	
//    	for(JaxbMethod method : jaxbMethods){
//    		System.out.println(method.getMethodName());
//    		System.out.println(method.getBizClass());
//    	}
    	String str = jaxbMethods.getMethodByName("register").getMethodName();
    	System.out.println(str);
    	
    }
}