package com.capgemini.pecunia.bank.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;



public interface PBankService {
	
	public List<Transaction> passbookUpdate(String userId)throws ValidateException,PbankTXNNotFouException;
	public List<Transaction> accountSummary(String userId, LocalDate fromDt, LocalDate toDate)throws PbankTXNNotFouException,ValidateException,DateException;
	
}
