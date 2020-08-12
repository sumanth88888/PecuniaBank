package com.capgemini.pecunia.bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pecunia.bank.dao.PbankDao;
import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;


@Transactional
@Service
public class PBankServiceImpl implements PBankService{

	public PBankServiceImpl() {
		
	}
	@Autowired
	private PbankDao dao;

	@Override
	public List<Transaction> passbookUpdate(String userId)
			throws  ValidateException,PbankTXNNotFouException {
		if (!userId.matches("[0-9]{12}"))
			throw new ValidateException("Account ID must be 12 digit");
		
		List<Transaction> txnList =  dao.passbookUpdate(userId);
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException("No Transaction available.");
		txnList = txnList.stream().sorted((t1, t2)->t2.getTransactionDate().compareTo(t1.getTransactionDate())).collect(Collectors.toList());
		return txnList;
	}

	@Override
	public List<Transaction> accountSummary(String userId, LocalDate fromDt, LocalDate toDate)
			throws  ValidateException,PbankTXNNotFouException,DateException{
		if (!userId.matches("[0-9]{12}"))
			throw new ValidateException("Account ID must be 12 digit");
		if (toDate.compareTo(fromDt) < 0)
			throw new DateException("ToDate must be graeter than FromDate");
		if(toDate.compareTo(LocalDate.now())>0)
			throw new DateException("from date must be less than current date");

		List<Transaction> txnList =  dao.accountSummary(userId, fromDt, toDate);
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException("No Transaction available.");
		txnList.sort((t1, t2)->t2.getTransactionDate().compareTo(t1.getTransactionDate()));
		return txnList;
		
	}

	
	
}

