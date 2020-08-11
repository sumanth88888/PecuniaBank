package com.capgemini.pecunia.bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecunia.bank.dao.PbankDao;
import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;



@Service
@Transactional
public class PBankServiceImpl implements PBankService{

	
	@Autowired
	private PbankDao dao;

	@Override
	public List<Transaction> getBankTransactions(String UserId)
			throws  ValidateException,PbankTXNNotFouException {
		if (!UserId.matches("[0-9]{12}"))
			throw new ValidateException("Account ID must be 12 digit");
		
		List<Transaction> txnList =  dao.getBankTransactions(UserId);
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException("No Transaction available.");
		txnList = txnList.stream().sorted((t1, t2)->t2.getTransactionDate().compareTo(t1.getTransactionDate())).collect(Collectors.toList());
		return txnList;
	}

	@Override
	public List<Transaction> getBankTransactions(String UserId, LocalDate fromDt, LocalDate toDate)
			throws  ValidateException,PbankTXNNotFouException,DateException{
		if (!UserId.matches("[0-9]{12}"))
			throw new ValidateException("Account ID must be 12 digit");
		if (toDate.compareTo(fromDt) < 0)
			throw new DateException("ToDate must be graeter than FromDate");
		
		List<Transaction> txnList =  dao.getBankTransactions(UserId, fromDt, toDate);
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException("No Transaction available.");
		txnList.sort((t1, t2)->t2.getTransactionDate().compareTo(t1.getTransactionDate()));
		return txnList;
		
	}

	
}

