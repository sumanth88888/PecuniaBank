package com.cg.penicua.bank.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Cheque;
import com.capgemini.pecunia.bank.entity.Customer;
import com.capgemini.pecunia.bank.entity.Slip;
import com.capgemini.pecunia.bank.entity.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Account getAccount(String account_id) {
		return em.find(Account.class, account_id);
		
	}

	/*
	 * @Override public int getBalance(Account account) { return 0; }
	 */

	/*
	 * @Override public Boolean updateBalance(Account account) { // TODO
	 * Auto-generated method stub return null; }
	 */

	@Override
	public int generateChequeId(Cheque cheque) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int generateTransactionId(Transaction transaction) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public Boolean addTransaction(Transaction transaction) {
		
		em.persist(transaction);
		return true;
		
	}
	
	
	
	public boolean addSlip(Slip slip) {
		em.merge(slip);
		return true;
	}
	
	public boolean addCustomer(Customer cust) {
		em.persist(cust);
		return true;
	}
	
	public boolean addCheque(Cheque cheque) {
		em.persist(cheque);
		return true;
	}

	@Override
	public boolean addAccount(Account account) {
		em.persist(account);
		return true;
	}
	
	
}
