package com.capgemini.pecunia.bank.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.bank.entity.Transaction;


public interface PBankService {
	
	public List<Transaction> getBankTransactions(String UserId);
	public List<Transaction> getBankTransactions(String UserId, LocalDate fromDt, LocalDate toDate);
	

}
