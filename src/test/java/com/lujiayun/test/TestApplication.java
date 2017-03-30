package com.lujiayun.test;

import com.jylu.service.EmployeeService;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationTest.xml");
        EmployeeService service = (EmployeeService) ac.getBean("employeeService");
        service.sayHello();
        ac.close();
    }

}
