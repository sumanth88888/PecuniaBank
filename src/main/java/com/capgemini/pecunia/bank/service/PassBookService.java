package com.capgemini.pecunia.bank.service;

import java.util.List;

import com.capgemini.pecunia.bank.dto.ReportForm;
import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.InvalidAccountException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.TxnsNumberException;

public interface PassBookService {
	
	
	public List<Transaction> userTransactions(ReportForm rform)throws AccountNotFoundException,PbankTXNNotFouException;
	
	public List<Transaction> transactionsDtRange(ReportForm rform)throws AccountNotFoundException,PbankTXNNotFouException,DateException;
	
	public List<Transaction> limitedTransactions(ReportForm rform) throws AccountNotFoundException,PbankTXNNotFouException, TxnsNumberException;
	
	public List<Transaction> lastPassbookUpdate(ReportForm rform)throws AccountNotFoundException,PbankTXNNotFouException;

}