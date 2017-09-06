package com.lujiayun.test;

import com.jylu.entity.Users;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName: LoadClass <br/>
 * Description: 类加载 <br/>
 * Date: 17-3-22 下午8:13 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class LoadClass {

    public static void main(String[] args) {
        try {
            Class c = Class.forName("com.jylu.entity.Users");
            // java中每个类型都有class属性
//            Class c = User.class;
            // java语言中任何一个java对象都有getClass 方法
//            Class c = new User().getClass();
            // 获取类加载器
            ClassLoader classLoader = c.getClassLoader();
            System.out.println(classLoader);
            Class clazz = classLoader.loadClass("com.lujiayun.test.User");
            System.out.println(clazz);
//            Constructor<String> constructor = c.getConstructor(String.class, String.class);
//            System.out.println(constructor.getName());
            // 获取类的方法
            Method method = c.getDeclaredMethod("sayHello", String.class);
            method.setAccessible(true);
            System.out.println(method.getName());
            Users user = new Users();
            method.invoke(user, "hello");
            // 调用无参构造函数,实例化一个类
//            Users user = (Users)c.newInstance();
//            user.setName("lujiayun");
//            System.out.println(user.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}