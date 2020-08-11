package com.capgemini.pecunia.bank.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Customer;



public class BankDaoImpl implements BankDao{
	
	@PersistenceContext
	private EntityManager em;


	@Override
	public boolean editAccount(Account account) {
		em.merge(account);
		return true;
	}

	@Override
	public Account viewAccount(String accId) {
		
		return em.find(Account.class, accId);
	}

	@Override
	public boolean deleteAccount(Account account) {
		em.remove(account);
		return true;
	}

	@Override
	public boolean addCustomer(Customer cust) {
		em.persist(cust);
		return true;
	}

	@Override
	public boolean addAccount(Account account) {
		em.persist(account);
		return true;
	}

	@Override
	public boolean deleteCustomer(Customer cust) {
		em.remove(cust);
		return false;
	}

	@Override
	public Customer viewCustomer(String aadhar_number) {
		
		return em.find(Customer.class, aadhar_number);
	}

	

}
