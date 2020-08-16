package com.capgemini.pecunia.bank.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.util.BankConstants;

@Repository
public class PbankDaoImpl implements PbankDao {

	@PersistenceContext
	private EntityManager entityManager;

	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : passbookUpdate
	 * Description   : getting transactions of given user's UserId 
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String UserId
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> passbookUpdate(String userId) {
		String jpql = "from Transaction txns inner join fetch txns.account acc " 
	                  + " where acc.accountId=:userid";
		TypedQuery<Transaction> query = entityManager.createQuery(jpql, Transaction.class);
		query.setParameter(BankConstants.USERID, userId);
		return query.getResultList();
	}

	
	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : accountSummary
	 * Description   : getting transactions of given user's UserId between given dates
	 * Return Type   : List(List of Transactions)
	 * Parameters    : String userId, LocalDate fromDt, LocalDate toDate
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> accountSummary(String userId, LocalDate fromDt, LocalDate toDate) {
		String jpql = "from Transaction txns inner join fetch txns.account acc "
				+ "where acc.accountId=:userid and txns.transactionDate between :fromdt and :todt";
		TypedQuery<Transaction> query = entityManager.createQuery(jpql, Transaction.class);
		query.setParameter(BankConstants.USERID, userId);
		query.setParameter("fromdt", fromDt);
		query.setParameter("todt", toDate);
		return query.getResultList();
	}


	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : getBankTransactions
	 * Description   : getting transactions of given user's UserId with limited transactions
	 * Return Type   : List(List of Transactions)
	 * Parameters    : String UserId, int txns
	 * 
	 **********************************************************************************/
	public List<Transaction> getBankTransactions(String userId, int txns) {
		String jpql = "from Transaction txns inner join fetch txns.account acc"
				+ " where acc.accountId=:userid order by txns.transactionDate desc";
		TypedQuery<Transaction> query = entityManager.createQuery(jpql, Transaction.class);
		query.setFirstResult(1);
		query.setMaxResults(txns);
		query.setParameter(BankConstants.USERID, userId);
		return query.getResultList();
	}


}
