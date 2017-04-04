package com.lujiayun.test;

import com.jylu.service.EmployeeService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApplication {

    @Test
    public void main() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationTest.xml");
        EmployeeService service = (EmployeeService) ac.getBean("employeeService");
        service.sayHello();
        ac.close();
    }

}
