package com.jylu.study.designpatterns.singleton.test;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jylu.study.designpatterns.singleton.SingletonHungry;


public class TestHungry {
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 10; i++){			
			pool.submit(new Runnable(){
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getId()+":"+SingletonHungry.NUM);
				}
			});
		}
		pool.shutdown();
	}

}
