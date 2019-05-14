package com.demo.day02;

public class StaticDemoTest {

	static {
		
		System.out.println("hello world!");
	}
	public static void main(String []args) {
		System.out.println(StaticDemo.USER_NAME);
		StaticDemo.USER_NAME = "testUser";
		System.out.println(StaticDemo.USER_NAME);
	}
}
