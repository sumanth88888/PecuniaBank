
package com.capgemini.pecunia.bank;

import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.capgemini.pecunia.bank.dto.ReportForm;
import com.capgemini.pecunia.bank.entity.Customer;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;
import com.capgemini.pecunia.bank.service.PBankService;
import com.capgemini.pecunia.bank.service.PBankServiceImpl;


public class Client {
	public static void main(String[] args) throws ValidateException, PbankTXNNotFouException, DateException  {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfiguration.class);
	//Customer custo = new Customer(); 
//		custo.setAadharNumber("7257332");
//		custo.setAddress("raipur");
//		custo.setCity("raipur");
//		custo.setName("ganesh");
//		custo.setPhoneNumber("708752445343");
//		custo.setCountry("india");
//		custo.setDateOfBirth(LocalDate.of(2020, 9, 26));
//		custo.setGender("male");
//		custo.setPanNumber("hsdhs");
//		custo.setState("chattisgarh");
//		custo.setZipcode("bhshsdh");
//
//		Account ac= new Account();
//		ac.setAccountId("12345677");
//		ac.setAccountType("saving");
//		ac.setBranchId("ghsvg");
//		ac.setBalance(15000.0);
//		ac.setCustomer(custo);
//		ac.setIfsc("83583");

//		TxnForm txnform=new TxnForm();
//		txnform.setAccId("12345678");
//		txnform.setAmount(20000.0);

//		Transfer transfer=new Transfer();
//		transfer.setAmount(1000.0);
//		transfer.setFromAccId("12345677");
//		transfer.setToAccId("12345678");
		// Account account = null; //account.setAccount_id(4596);

//		 Slip slip=new Slip(64735373L, "Credit", 5000, LocalDate.now(), "Completed",
//		 acc, 4635363L);

		// Cheque(int transaction_id, String transaction_type, double
		/*
		 * transaction_amount, LocalDate transaction_date,String transaction_status,
		 * Account account, int cheque_id, String ifsc_code, LocalDate
		 * cheque_issue_date, int beneficiary_account_number) //Cheque cheque = new
		 * Cheque("42", "Debit", 2500, LocalDate.now(), "Completed", account, "7914",
		 * "SBIN00856", LocalDate.of(2020, 04, 15), "985");
		 */

		PBankService service = ctx.getBean(PBankService.class);
		
		

		 //service.addAccount(ac);
		 //service.addCust(custo);
		// service.addCheque(cheque);
		// service.creditUsingSlip(txnform);
		// service.creditUsingCheque(transfer);
		
		service.accountSummary("123456654322", LocalDate.of(2020, 8, 1), LocalDate.of(2020, 8, 3));
		

	}
}

