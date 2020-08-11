package com.capgemini.pecunia.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/****************************************************************************************************
 *          @author          Rakesh Kuruva
 *          Description      It is an Entity class of Account that is mapped with the Oracle DataBase
 *                                       of table bank_account.
 *          @version         1.0
 *          Created Date     05-AUG-2020
 ****************************************************************************************************/

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="bank_account")
public class Account {
	
	@Id
	@Column(name = "account_id")
	private String accountId;
	
	@OneToOne
	@JoinColumn(name = "aadhar_number", referencedColumnName = "aadhar_number")
	private Customer cust = new Customer();
	
	@Column(name = "acc_balance")
	private double balance;
	
	@Column(name = "ifsc_code", length = 15)
	private String ifsc;
	
	@Column(name = "branch_id", length = 15)
	private String branch_id;
	
	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public Customer getCust() {
		return cust;
	}
	
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getIfsc() {
		return ifsc;
	}
	
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	
	public String getBranch_id() {
		return branch_id;
	}
	
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

}
