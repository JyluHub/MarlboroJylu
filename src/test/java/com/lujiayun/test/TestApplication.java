package com.lujiayun.test;

import com.jylu.service.EmployeeService;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
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
