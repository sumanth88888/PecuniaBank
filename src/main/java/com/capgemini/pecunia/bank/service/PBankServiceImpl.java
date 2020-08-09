package com.capgemini.pecunia.bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.pecunia.bank.dao.PbankDao;
import com.capgemini.pecunia.bank.entity.Transaction;



public class PBankServiceImpl implements PBankService{

	@Autowired
	private PbankDao dao;
	
	@Override
	public List<Transaction> getBankTransactions(String UserId) {
		List<Transaction> txnList =  dao.getBankTransactions(UserId);
		txnList = txnList.stream().sorted((t1, t2)->t2.getTransactionDate().compareTo(t1.getTransactionDate())).collect(Collectors.toList());
		return txnList;
	}
	

	@Override
	public List<Transaction> getBankTransactions(String UserId, LocalDate fromDt, LocalDate toDate) {
		List<Transaction> txnList =  dao.getBankTransactions(UserId, fromDt, toDate);
		txnList.sort((t1, t2)->t2.getTransactionDate().compareTo(t1.getTransactionDate()));
		return txnList;
	}

	

}
