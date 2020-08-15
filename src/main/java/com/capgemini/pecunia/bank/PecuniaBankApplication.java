package com.capgemini.pecunia.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capgemini.pecunia.bank.web.LoggerInterceptor;

@SpringBootApplication
public class PecuniaBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(PecuniaBankApplication.class, args);
	}
	
	@Autowired
	public LoggerInterceptor loggerInterceptor;

}
