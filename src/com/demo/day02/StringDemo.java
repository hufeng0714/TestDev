package com.demo.day02;

public class StringDemo {

	private String demoString = "testStringApi";
	
	public void testString() {
		
		//�ַ������ӣ�Ҳ������+��
		String dsg = demoString.concat("very good");
		System.out.println(dsg);
		dsg = dsg + "qqq";
		System.out.println(dsg);
		
		//�ַ�������
		int len = dsg.length();
		System.out.println(len);
		
		//�Ƚ������ַ����Ƿ����
		boolean eq = "test".equals(demoString);
		System.out.println(eq);
		
		//�ӵ�5���ַ���ʼ����������8���ַ�
		String sub = demoString.substring(5, 8);
		System.out.println(sub);
		
		//�ӵ�5���ַ���ʼ�������
		String substring = demoString.substring(5);
		System.out.println(substring);
		
	}
	
	public static void main(String []args) {
		
		StringDemo sd = new StringDemo();
		sd.testString();
	}
}
