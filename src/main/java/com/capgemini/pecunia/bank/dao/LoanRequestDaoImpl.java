package com.capgemini.pecunia.bank.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.LoanRequest;

@Repository
public class LoanRequestDaoImpl implements LoanRequestDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : addLoanDetails
	 * Description   : This method adds loanRequest into the database
	 * @param  		 : loanRequest
	 * @return 	     : true if loanRequest is added into database
	 * 
	 ******************************************************************************/
	
	@Override
	public boolean addLoanDetails(LoanRequest loanRequest) {
		entityManager.persist(loanRequest);
		return true;
	}
	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : getAccount
	 * Description   : This method gets the account for the given accountId
	 * @param  		 : accountId
	 * @return 	     : Account instance
	 * 
	 ******************************************************************************/
	
	@Override
	public Account getAccount(String accountId) {
		return entityManager.find(Account.class, accountId);
	}
	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : countLoansForCustomer
	 * Description   : This method gets the number of previous loans for customer
	 * @param  		 : accontId
	 * @return 	     : long
	 * 
	 ******************************************************************************/
	
	@Override
	public long countLoansForCustomer(String accountId) {
		String jpql = "select count(request.loanRequestId) from LoanRequest request inner join request.account account where account.accountId=:accountId";
		TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
		query.setParameter("accountId", accountId);
		return query.getSingleResult();
	}
	
}
