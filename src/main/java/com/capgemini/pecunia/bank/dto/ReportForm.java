package com.capgemini.pecunia.bank.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ReportForm {

	private String userId;
	private int txns;
	public int getTxns() {
		return txns;
	}
	public void setTxns(int txns) {
		this.txns = txns;
	}
	@DateTimeFormat(pattern="yyyy-M-d")
	private LocalDate fromDt;
	@DateTimeFormat(pattern="yyyy-M-d")
	private LocalDate toDate;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDate getFromDt() {
		return fromDt;
	}
	public void setFromDt(LocalDate fromDt) {
		this.fromDt = fromDt;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	
	
	
	
	
	
}