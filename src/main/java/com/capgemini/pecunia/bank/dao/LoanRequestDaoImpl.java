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
	
	@Override
	public String addLoanDetails(LoanRequest loanRequest) {
		entityManager.persist(loanRequest);
		return loanRequest.getLoanRequestId();
	}
	
	@Override
	public Account getAccount(String accountId) {
		return entityManager.find(Account.class, accountId);
	}
	
	@Override
	public long countLoansForCustomer(String accountId) {
		String jpql = "select count(request.loanRequestId) from LoanRequest request inner join request.account account where account.accountId=:accountId";
		TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
		query.setParameter("accountId", accountId);
		return query.getSingleResult();
	}
	
}
