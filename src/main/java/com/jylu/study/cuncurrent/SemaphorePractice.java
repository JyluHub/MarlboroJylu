package com.jylu.study.cuncurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphorePractice {
	
	private static final Semaphore semaphore = new Semaphore(10);
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(20);
		for(int i = 0; i < 20; i++){			
			pool.submit(new Thread(new SemaphoreTask(semaphore)));
		}
		pool.shutdown();
	}

}

class SemaphoreTask implements Runnable{
	
	private final Semaphore semaphore;
	
	public SemaphoreTask(Semaphore semaphore){
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			// 获得许可
			semaphore.acquire();
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " 获得许可");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放许可
			semaphore.release();
		}
	}
	
}
