package com.capgemini.pecunia.bank;


import java.util.List;
import java.util.Scanner;

import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;
import com.capgemini.pecunia.bank.service.PBankService;
import com.capgemini.pecunia.bank.service.PBankServiceImpl;

public class clint {
	public static void main(String[] args) throws ValidateException, PbankTXNNotFouException, DateException {
		PBankService service = new PBankServiceImpl();
		Scanner scan=new Scanner(System.in);
		String UserId=scan.next();
		List<Transaction> lst = service.getBankTransactions(UserId);
		for(int i=0;i<lst.size();i++){
		    System.out.println(lst.get(i));
		} 
		/*
		 * List<Transaction> lst1 = service.getBankTransactions("123456654321",
		 * LocalDate.of(2019, 07, 14), LocalDate.of(2020, 01, 15));
		 */

	}
}
