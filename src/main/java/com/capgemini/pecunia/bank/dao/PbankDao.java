package com.capgemini.pecunia.bank.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.pecunia.bank.entity.Transaction;



public interface PbankDao {

	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : passbookUpdate
	 * Description   : getting transactions of given user's UserId 
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String UserId
	 * 
	 **********************************************************************************/
	public List<Transaction> passbookUpdate(String userId);
	
	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : accountSummary
	 * Description   : getting transactions of given user's UserId between given dates
	 * Return Type   : List(List of Transactions)
	 * Parameters    : String userId, LocalDate fromDt, LocalDate toDate
	 * 
	 **********************************************************************************/
	public List<Transaction> accountSummary(String userId, LocalDate fromDt, LocalDate toDate);
	
	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : getBankTransactions
	 * Description   : getting transactions of given user's UserId with limited transactions
	 * Return Type   : List(List of Transactions)
	 * Parameters    : String UserId, int txns
	 * 
	 **********************************************************************************/
	public List<Transaction> getBankTransactions(String userId, int txns);
	
}
