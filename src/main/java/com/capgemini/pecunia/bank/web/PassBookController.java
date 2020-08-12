package com.capgemini.pecunia.bank.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.bank.dto.ReportForm;
import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;
import com.capgemini.pecunia.bank.service.PBankService;

@RestController
public class PassBookController {


	

		@Autowired
		private PBankService service;

		@CrossOrigin
		@GetMapping("/getBankTransactions/{userId}")
		public List<Transaction> passbookUpdate(@PathVariable("userId") String userId)
				throws ValidateException, PbankTXNNotFouException {
			return service.passbookUpdate(userId);
		}

		@CrossOrigin
		@PostMapping("/getBankTxnsForDateRange")
		public List<Transaction> accountSummary(@RequestBody ReportForm form) throws PbankTXNNotFouException, ValidateException, DateException {

			return service.accountSummary(form.getUserId(), form.getFromDt(), form.getToDt());
		}

		
	}
