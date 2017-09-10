package com.jylu.study.designpatterns.singleton.test;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jylu.study.designpatterns.singleton.SingletonInner;


public class TestInner {
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 100; i++){			
			pool.submit(new Runnable(){
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getId()+":"+SingletonInner.getInstance());
				}
			});
		}
		pool.shutdown();
	}

}
