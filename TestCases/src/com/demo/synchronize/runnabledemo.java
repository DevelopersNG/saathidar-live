package com.demo.synchronize;

public class runnabledemo implements Runnable {

	public static void main(String[] args) {
		
		runnabledemo cl=new runnabledemo();
//		System.out.println(cl.getData("d1"));
//		
		Thread th=new Thread(cl);
		th.start();
	}

	@Override
	public void run() {
		System.out.println("run method");
		
	}
}

class callClass{
	public String getData(String value) {
		return value;
	}
}
