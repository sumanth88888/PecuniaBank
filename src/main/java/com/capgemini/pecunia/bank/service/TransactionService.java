package com.capgemini.pecunia.bank.service;

import com.cg.bank.dto.Transfer;
import com.cg.bank.dto.TxnForm;
import com.cg.bank.entity.Account;
import com.cg.bank.entity.Cheque;
import com.cg.bank.entity.Customer;
import com.cg.bank.entity.Slip;
import com.cg.bank.entity.Transaction;
import com.cg.bank.exceptions.AccountNotFoundException;
import com.cg.bank.exceptions.TransactionException;

public interface TransactionService {
	/* public int getBalance(Account account); */

	/*
	 * public Boolean updateBalance(Account account);
	 */
	public Boolean creditUsingSlip(TxnForm txnform) throws AccountNotFoundException;

	public Boolean debitUsingSlip(TxnForm txnform) throws AccountNotFoundException,TransactionException;

	public Boolean creditUsingCheque(Transfer transfer) throws AccountNotFoundException,TransactionException;

	public Boolean debitUsingCheque(TxnForm txnform) throws AccountNotFoundException,TransactionException;

	public Boolean addSlip(Slip slip);

	public boolean addCust(Customer cust);

	public boolean addCheque(Cheque cheque);
	
	public boolean addAccount(Account account);

}
