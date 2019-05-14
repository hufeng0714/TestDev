package com.demo.day01;

public class HelloWorld {
	
	public String helloWorld;
	
	public String getHelloWorld() {
		return helloWorld;
	}
	
	public void setHelloWorld(String helloWorld) {
		this.helloWorld = helloWorld;
	}
	
	public HelloWorld(String helloWorld) {
		this.helloWorld = helloWorld;
	}
	
	public HelloWorld() {}
	
	public void outputHelloWorld() {
		System.out.println(helloWorld);
	}
	
	public static void main(String[] args) {
		
		String helloWorld = "Hello World!";
		HelloWorld h = new HelloWorld();
		
		h.setHelloWorld(helloWorld);
		h.outputHelloWorld();
		
		HelloWorld w = new HelloWorld(helloWorld);
		w.outputHelloWorld();
	}

}
