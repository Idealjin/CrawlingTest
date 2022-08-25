package com.sangjin.crawling.app.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class CrwalingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrwalingApplication.class, args);
	}

}
