package com.capgemini.pecunia.bank.dao;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Customer;

public interface BankDao {

		
		public boolean addCustomer(Customer cust);
		
		public boolean addAccount(Account account);
		
		public boolean editAccount(Account account);
		
		public boolean deleteCustomer(Customer cust);
		
		public boolean deleteAccount(Account account);

		public Account viewAccount(String accId);
		
		public Customer viewCustomer(String aadhar_number);



}
