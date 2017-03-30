package com.lujiayun.test;

/**
 * ClassName: User <br/>
 * Description: demo <br/>
 * Date: 17-3-22 下午8:17 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class User {

    private String name;

    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void sayHello(String hello){
        System.out.println("hello,my name is demo "+hello);
    }
}