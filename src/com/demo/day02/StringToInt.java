package com.demo.day02;

public class StringToInt {

	public Integer changeType(String s) {
		try {
			return Integer.valueOf(s);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String [] args) {
		StringToInt t = new StringToInt();
		System.out.println(t.changeType("s"));
	}
}
