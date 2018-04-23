package com.singleton;

public class SingleTon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Thread t1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				Santhosh santu=Santhosh.getInstance();
				System.out.println(santu.hashCode());
				
			}
		});
		//second thread
Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				Santhosh santu2=Santhosh.getInstance();
				System.out.println(santu2.hashCode());
				
			}
		});
		
		t1.start();
		t2.start();
	}

}
