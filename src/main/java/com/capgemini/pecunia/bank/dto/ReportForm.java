package com.capgemini.pecunia.bank.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class ReportForm {

	private String UserId;
	private String receipentId;
	@DateTimeFormat(pattern="yyyy-M-d")
	private LocalDate fromDt;
	@DateTimeFormat(pattern="yyyy-M-d")
	private LocalDate toDt;
	
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		this.UserId = userId;
	}
	public String getReceipentId() {
		return receipentId;
	}
	public void setReceipentId(String receipentId) {
		this.receipentId = receipentId;
	}
	public LocalDate getFromDt() {
		return fromDt;
	}
	public void setFromDt(LocalDate fromDt) {
		this.fromDt = fromDt;
	}
	public LocalDate getToDt() {
		return toDt;
	}
	public void setToDt(LocalDate toDt) {
		this.toDt = toDt;
	}
	
	
	
	
}
