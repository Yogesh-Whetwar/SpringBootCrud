package com.practice.myappvs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.")
@SpringBootApplication
public class MyappvsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappvsApplication.class, args);
		System.out.println("Started.....");
	}

}
