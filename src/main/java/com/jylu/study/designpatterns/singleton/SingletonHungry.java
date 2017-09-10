package com.jylu.study.designpatterns.singleton;

/**
 * 饿汉式单利模式
 * @author Lujiayun
 *
 */
public class SingletonHungry {
	
	private static final SingletonHungry instance = new SingletonHungry();
	
	public static final int NUM = 10;
	
	private SingletonHungry(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("饿汉式单利模式调用构造器");
	};
	
	public static SingletonHungry getInstance(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return instance;
	}

}
