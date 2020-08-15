package com.capgemini.pecunia.bank.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Customer;


/****************************************************************************************
 *          @author          Rakesh Kuruva
 *          Description      It is a DAO implementation class that interacts with
 *                                      Oracle DataBase for Account,Customer,Transaction
 *                                      and Loan Management.
 *          @version         1.0
 *          Created Date     05-AUG-2020
*****************************************************************************************/


@Repository
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

	@Override
	public boolean editCustomer(Customer customer) {
		
		em.merge(customer);
		return true;
	}

	

}
