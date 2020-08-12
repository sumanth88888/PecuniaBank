package com.capgemini.pecunia.bank.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.bank.entity.Transaction;



public interface PbankDao {

	public List<Transaction> passbookUpdate(String userId);
	public List<Transaction> accountSummary(String userId, LocalDate fromDt, LocalDate toDate);
	public List<Transaction> getBankTransactions(String UserId, int txns);
	
}
