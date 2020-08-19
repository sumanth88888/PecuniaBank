package com.capgemini.pecunia.bank.service;

import com.capgemini.pecunia.bank.dto.Transfer;
import com.capgemini.pecunia.bank.dto.TxnForm;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.TransactionException;

public interface TransactionService {
	
	public boolean creditUsingSlip(TxnForm txnform) throws AccountNotFoundException;

	public boolean debitUsingSlip(TxnForm txnform) throws AccountNotFoundException,TransactionException;

	public boolean creditUsingCheque(Transfer transfer) throws AccountNotFoundException,TransactionException;

	public boolean debitUsingCheque(TxnForm txnform) throws AccountNotFoundException,TransactionException;
	
}
