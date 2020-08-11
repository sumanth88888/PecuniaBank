package com.capgemini.pecunia.bank.dto;

public class LoanRequestForm {

	private double loanAmt;
	private int tenure;
	private String accountId;
	private String loanType;
	private double annualIncome;
	private double rateOfInterest;
	private int creditScore;
	public double getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	public LoanRequestForm(double loanAmt, int tenure, String accountId, String loanType, double annualIncome,
			double rateOfInterest, int creditScore) {
		super();
		this.loanAmt = loanAmt;
		this.tenure = tenure;
		this.accountId = accountId;
		this.loanType = loanType;
		this.annualIncome = annualIncome;
		this.rateOfInterest = rateOfInterest;
		this.creditScore = creditScore;
	}
	
	
	
}
