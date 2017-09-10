package com.jylu.study.cuncurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchPractice {
	
	private static final CountDownLatch latch = new CountDownLatch(10);
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 10; i++){
			pool.execute(new Thread(new CountDownLatchTask(latch)));
		}
		try {
			// 阻塞等待所有的线程执行完毕
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() +" start...........");
		pool.shutdown();
	}

}

class CountDownLatchTask implements Runnable{
	
	private final CountDownLatch latch;
	
	public CountDownLatchTask(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(10) * 1000);
			System.out.println(Thread.currentThread().getName() +": in progress");
			latch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
