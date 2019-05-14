package com.demo.day02;

public class LoopTest {

	private int [] testArray = new int[] {2,4,3,5,1};
	
	public void testForLoop() {
		for(int i=0;i<testArray.length;i++) {
			if(testArray[i]==3) {
				//continue�˳�����ѭ�����������䲻ִ��
				continue;
			}
			System.out.println(testArray[i]);
		}
	}
	
	public void testForLoopEnhance() {
		for(int i:testArray) {
			System.out.println(i);
			if(i==3) {
				//break�˳���ǰѭ����
				break;
			}
		}
	}
	
	public void testWhileLoop() {
		int index = 0;
		while(index<testArray.length) {
			System.out.println(testArray[index]);
			index++;
		}
	}
	
	public static void main(String [] args) {
		LoopTest t = new LoopTest();
		t.testForLoop();
		System.out.println("===============");
		t.testForLoopEnhance();
		System.out.println("===============");
		t.testWhileLoop();
	}
	
}
