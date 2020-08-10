package com.capgemini.pecunia.bank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.LoanRequest;

@Repository
public class LoanDisbursalDaoImpl implements LoanDisbursalDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public LoanRequest getLoanRequest(String loanRequestId) {
		return entityManager.find(LoanRequest.class, loanRequestId);
	}

	@Override
	public boolean editAccount(Account account) {
		entityManager.merge(account);
		return true;
	}

	@Override
	public boolean editLoanRequest(LoanRequest loanRequest) {
		entityManager.merge(loanRequest);
		return true;
	}

	@Override
	public List<LoanRequest> retrieveDisbursedLoanRequest(String loanStatus) {
		String jpql = "from LoanRequest loanRequest where loanRequest.loanRequestStatus=:status";
		TypedQuery<LoanRequest> query = entityManager.createQuery(jpql, LoanRequest.class);
		query.setParameter("status", loanStatus);
		return query.getResultList();
	}
	
	
	
}
