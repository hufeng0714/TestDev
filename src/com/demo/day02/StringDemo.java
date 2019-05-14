package com.demo.day02;

public class StringDemo {

	private String demoString = "testStringApi";
	
	public void testString() {
		
		//字符串连接，也可以用+号
		String dsg = demoString.concat("very good");
		System.out.println(dsg);
		dsg = dsg + "qqq";
		System.out.println(dsg);
		
		//字符串长度
		int len = dsg.length();
		System.out.println(len);
		
		//比较两个字符串是否相等
		boolean eq = "test".equals(demoString);
		System.out.println(eq);
		
		//从第5个字符开始，不包括第8个字符
		String sub = demoString.substring(5, 8);
		System.out.println(sub);
		
		//从第5个字符开始，到最后
		String substring = demoString.substring(5);
		System.out.println(substring);
		
	}
	
	public static void main(String []args) {
		
		StringDemo sd = new StringDemo();
		sd.testString();
	}
}
