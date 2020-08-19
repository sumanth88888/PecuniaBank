package com.capgemini.pecunia.bank.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Cheque;
import com.capgemini.pecunia.bank.entity.Customer;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.entity.Slip;
import com.capgemini.pecunia.bank.entity.Transaction;

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
		
		public boolean editCustomer(Customer customer);
		
		public boolean deleteCustomer(Customer cust);
		
		public boolean deleteAccount(Account account);

		public Account viewAccount(String accId);
		
		public Customer viewCustomer(String aadhar_number);

		public LoanRequest getLoanRequest(String loanRequestId);
		
		public boolean editLoanRequest(LoanRequest loanRequest);
		
		public List<LoanRequest> retrieveDisbursedLoanRequest(String status);
		
		public boolean addLoanDetails(LoanRequest loanRequest);
		
		public Account getAccount(String accountId);
		
		public long countLoansForCustomer(String custID);
		
		public List<Transaction> passbookUpdate(String userId);
		
		public List<Transaction> accountSummary(String userId, LocalDate fromDt, LocalDate toDate);
		
		public List<Transaction> getBankTransactions(String userId, int txns);
		
		public boolean addSlip(Slip slip);

		public boolean addCheque(Cheque cheque);
		

}