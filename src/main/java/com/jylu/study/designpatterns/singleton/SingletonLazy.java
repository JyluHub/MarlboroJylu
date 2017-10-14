package com.jylu.study.designpatterns.singleton;

/**
 * @author Lujiayun
 *
 */
public class SingletonLazy {
	
	private static SingletonLazy instance = null;
	
	private SingletonLazy() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("懒汉式单利模式调用构造器");
	}
	
	public /*synchronized*/ static SingletonLazy getInstance(){
		// 会有多线程安全的问题,需要加锁保证单例
//		if(null == instance){
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			instance = new SingletonLazy();
//		}
		if(null == instance){
			synchronized(SingletonLazy.class){
				if(null == instance){
					instance = new SingletonLazy();
				}
			}
		}
		return instance;
	}

}
