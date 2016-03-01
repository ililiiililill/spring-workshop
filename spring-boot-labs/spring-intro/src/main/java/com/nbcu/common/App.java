package com.nbcu.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-config.xml");

        HelloWorld obj = (HelloWorld) context.getBean("helloBean");
        obj.printHello();
    }
}
