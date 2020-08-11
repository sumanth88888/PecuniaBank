package com.capgemini.pecunia.bank.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;



public interface PBankService {
	
	public List<Transaction> getBankTransactions(String UserId)throws ValidateException,PbankTXNNotFouException;
	public List<Transaction> getBankTransactions(String UserId, LocalDate fromDt, LocalDate toDate)throws PbankTXNNotFouException,ValidateException,DateException;
	
}
