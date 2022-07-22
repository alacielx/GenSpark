package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringDemoXMLConfig {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Demo.xml");

		Student x = (Student) context.getBean("Student");

		System.out.println(x);
	}
}
