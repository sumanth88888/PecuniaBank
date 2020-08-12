package com.capgemini.pecunia.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.capgemini.pecunia.bank"})
public class PecuniaBankApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(PecuniaBankApplication.class, args);
		
			
		
	}

}
