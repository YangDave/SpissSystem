package com.ss.spiss.test;

public class ObjectTest {
	
	public static void main(String[] args) {
		
		Yang y = new Yang();
		
		Object obj = (Object)y;
		System.out.println(obj.getClass().equals(Yang.class));
		try {
			Class<?> clazz = Class.forName("com.ss.spiss.test.Yang");
			Object obj2 = clazz.newInstance();
			System.out.println(obj2.getClass().equals(Yang.class));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

class Yang{
	public void say() {
		
		System.out.println("yang");

	}
}
