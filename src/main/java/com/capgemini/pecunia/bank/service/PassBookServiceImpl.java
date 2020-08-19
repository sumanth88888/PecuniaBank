package com.capgemini.pecunia.bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pecunia.bank.dao.BankDao;
import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;

@Service
@Transactional
public class PassBookServiceImpl implements PassBookService {

	@Autowired
	private BankDao bankDao;


	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : passbookUpdate
	 * Description   : getting transactions of given user's UserId 
	 * Return Type   : List(List of Transactions)
	 * Parameter     : String USerId
	 * @throws       : ValidateException,PbankTXNNotFouException
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> passbookUpdate(String userId)
			throws  ValidateException,PbankTXNNotFouException {
		if (!userId.matches("[0-9]{12}"))
			throw new ValidateException("Account ID must be 12 digit");
		
		List<Transaction> txnList =  bankDao.passbookUpdate(userId);
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException("No Transaction available.");
		txnList = txnList.stream().sorted((t1, t2)->t2.getTransactionDate().compareTo(t1.getTransactionDate())).collect(Collectors.toList());
		return txnList;
	}

	
	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : accountSummary
	 * Description   : getting transactions of given user's UserId between given dates
	 * Return Type   : List(List of Transactions)
	 * Parameters    : String UserId,LocalDate fromDt, LocalDate toDate
	 * @throws       : PbankTXNNotFouException,ValidateException,DateException
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> accountSummary(String userId, LocalDate fromDt, LocalDate toDate)
			throws  ValidateException,PbankTXNNotFouException,DateException{
		if (!userId.matches("[0-9]{12}"))
			throw new ValidateException("Account ID must be 12 digit");
		if (toDate.compareTo(fromDt) < 0)
			throw new DateException("ToDate must be graeter than FromDate");
		if(toDate.isBefore(LocalDate.now()))
			throw new DateException("from date must be less than current date");

		List<Transaction> txnList =  bankDao.accountSummary(userId, fromDt, toDate);
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException("No Transaction available.");
		txnList.sort((t1, t2)->t2.getTransactionDate().compareTo(t1.getTransactionDate()));
		return txnList;
		
	}

	
	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : getBankTransactions
	 * Description   : getting transactions of given user's UserId with limited transactions
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String UserId,int txns
	 * @throws       : ValidateException, PbankTXNNotFouException
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> getBankTransactions(String userId, int txns) throws ValidateException, PbankTXNNotFouException {
		if (!userId.matches("[0-9]{12}"))
			throw new ValidateException("Account ID must be 12 digit");
		
		List<Transaction> txnList =  bankDao.getBankTransactions(userId, txns);
		
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException("No Transaction available.");
		return txnList;
	}
	
	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : lastPassbookUpdate
	 * Description   : getting transactions of given user's UserId from Last Updated Date
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String UserId,LocalDate fromDt
	 * @throws       : ValidateException, PbankTXNNotFouException
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> lastPassbookUpdate(String userId, LocalDate fromDt)throws ValidateException, PbankTXNNotFouException {
		if (!userId.matches("[0-9]{12}"))
			throw new ValidateException("Account ID must be 12 digit");
		List<Transaction> txnList =  bankDao.accountSummary(userId, fromDt, LocalDate.now());
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException("No Transaction available.");
		return txnList;
	}

	
}
