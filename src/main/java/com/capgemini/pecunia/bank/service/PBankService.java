package com.capgemini.pecunia.bank.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;

public interface PBankService {
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
	public List<Transaction> passbookUpdate(String userId)throws ValidateException,PbankTXNNotFouException;
	
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
	public List<Transaction> accountSummary(String userId, LocalDate fromDt, LocalDate toDate)throws PbankTXNNotFouException,ValidateException,DateException;
	
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
	public List<Transaction> getBankTransactions(String userId, int txns) throws ValidateException, PbankTXNNotFouException;
	
}
