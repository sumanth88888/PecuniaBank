package com.capgemini.pecunia.bank.dao;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Customer;

/**********************************************************************************************************
 *          @author          Rakesh Kuruva
 *          Description      It is a DAO(Data Access Object) interface of BankDao contains the method
 *                                      declarations of BankDaoImpl
 *          @version         1.0
 *          Created Date     05-AUG-2020
 **********************************************************************************************************/
public interface BankDao {

		
		public boolean addCustomer(Customer cust);
		
		public boolean addAccount(Account account);
		
		public boolean editAccount(Account account);
		
		public boolean deleteCustomer(Customer cust);
		
		public boolean deleteAccount(Account account);

		public Account viewAccount(String accId);
		
		public Customer viewCustomer(String aadhar_number);



}
