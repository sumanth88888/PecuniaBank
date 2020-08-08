package com.capgemini.pecunia.bank.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Sprint2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sprint2Application.class, args);
	}
	/*
	 * @Autowired public LoginInterceptor loginInterceptor;
	 * 
	 * @Autowired public LoggerInterceptor loggerInterceptor;
	 * 
	 * @Bean(name="authenticatemap") public Map<String, WalletAccount>
	 * getAuthenticateMap(){ return new HashMap<String, WalletAccount>(); }
	 */
	
}
