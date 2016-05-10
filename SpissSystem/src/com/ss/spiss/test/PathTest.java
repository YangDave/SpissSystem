package com.ss.spiss.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

public class PathTest {
	
	public static void main(String[] args) throws IOException {
		
		ClassPathResource cr = new ClassPathResource("jaxb-servlet.xml");
		System.out.println(cr.getPath());
		System.out.println(cr.getDescription());
		InputStreamReader isr = new InputStreamReader(cr.getInputStream());

		BufferedReader br = new BufferedReader(isr);
		String line;
		while((line = br.readLine())!=null){
			System.out.println(line);
		}
	}

}
