package com.capgemini.pecunia.bank.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.bank.entity.Transaction;



public interface PbankDao {

	public List<Transaction> getBankTransactions(String UserId);
	public List<Transaction> getBankTransactions(String UserId, LocalDate fromDt, LocalDate toDate);
	
}
