package com.capgemini.pecunia.bank.dto;

public class TxnForm {

	private String accountId;
	private double amount;
	
	public String getWalletAccountId() {
		return accountId;
	}
	public void setWalletAccountId(String walletAccountId) {
		this.accountId = walletAccountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
