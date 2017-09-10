package com.jylu.study.cuncurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPractice {
	
	private static final ReentrantLock lock = new ReentrantLock();
	
	private static final CountDownLatch latch = new CountDownLatch(10);
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		ReentrantTask task = new ReentrantTask(lock, latch);
		task.setNum(0);
		for(int i = 0; i < 10; i++){
			pool.submit(task);
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool.shutdown();
		System.out.println("最终计算结果为: " + task.getNum());
	}
	

}

class ReentrantTask implements Runnable{
	
	private int num;
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}
	
	private final ReentrantLock lock;
	
	private final CountDownLatch latch;
	
	public ReentrantTask(ReentrantLock lock, CountDownLatch latch) {
		this.lock = lock;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			lock.lock();
			for(int i = 0; i < 10000; i++){
				num++;
			}
			latch.countDown();
		} finally {
			lock.unlock();
		}
	}
}
