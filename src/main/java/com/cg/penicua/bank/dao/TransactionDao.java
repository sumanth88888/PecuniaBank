package com.cg.penicua.bank.dao;

import com.cg.bank.entity.Account;
import com.cg.bank.entity.Cheque;
import com.cg.bank.entity.Customer;
import com.cg.bank.entity.Slip;
import com.cg.bank.entity.Transaction;

public interface TransactionDao {

	public Account getAccount(String acc_id);

	/* public int getBalance(Account account); */

	/*
	 * public Boolean updateBalance(Account account);
	 */
	public int generateChequeId(Cheque cheque);

	public int generateTransactionId(Transaction transaction);

	public Boolean addTransaction(Transaction transaction);

	public boolean addCustomer(Customer cust);

	public boolean addSlip(Slip slip);

	public boolean addCheque(Cheque cheque);
	
	public boolean addAccount(Account account);
}
