package com.capgemini.pecunia.bank.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.bank.entity.Transaction;

@Repository
public class PbankDaoImpl implements PbankDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Transaction> passbookUpdate(String userId) {
		String jpql = "from Transaction txns inner join fetch txns.account acc " 
	                  + " where acc.accountId=:userid";
		TypedQuery<Transaction> query = entityManager.createQuery(jpql, Transaction.class);
		query.setParameter("userid", userId);
		return query.getResultList();
	}

	@Override
	public List<Transaction> accountSummary(String userId, LocalDate fromDt, LocalDate toDate) {
		String jpql = "from Transaction txns inner join fetch txns.account acc  "
				+ "where acc.accountId=:userid and txns.transactionDate between :fromdt and :todt ";
		TypedQuery<Transaction> query = entityManager.createQuery(jpql, Transaction.class);
		query.setParameter("userid", userId);
		query.setParameter("fromdt", fromDt);
		query.setParameter("todt", toDate);
		return query.getResultList();
	}



}
