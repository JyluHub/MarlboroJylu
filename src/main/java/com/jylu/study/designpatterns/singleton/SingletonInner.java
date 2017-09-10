package com.jylu.study.designpatterns.singleton;

public class SingletonInner {
	
	// 静态内部类方式实现单例
	private static class Singleton{
		
		private Singleton() {
			System.out.println("静态内部类单利模式调用构造器");
		}
		
		private static final Singleton instance = new Singleton();
	}
	
	public static Singleton getInstance(){
		return Singleton.instance;
	}

}
