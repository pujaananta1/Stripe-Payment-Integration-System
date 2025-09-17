package com.hulkhiretech.payments.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkhiretech.payments.constant.Constant;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;
import com.hulkhiretech.payments.pojo.InitiateTxnRequest;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(Constant.V1_PAYMENTS)
@Slf4j
@RequiredArgsConstructor
public class PaymentController {
	
	private final PaymentService paymentService;
	
	@PostMapping
	public CreateTxnResponse createTxn(@RequestBody CreateTxnRequest createTxnRequest) {
		log.info("Creating payment transaction||createTxnRequest:{}", 
				createTxnRequest);
		
		CreateTxnResponse response = paymentService.createTxn(createTxnRequest);
		log.info("Response from service: {}", response);
		
		return response;
	}
	
	@PostMapping("/{txnReference}/initiate")
	public String initiateTxn(@PathVariable String txnReference, 
			@RequestBody InitiateTxnRequest initiateTxnRequest) {
		log.info("Initiating payment transaction||id:{}|initiateTxnRequest:{}", 
				txnReference, initiateTxnRequest);
		
		String response = paymentService.initiateTxn(txnReference, initiateTxnRequest);
		log.info("Response from service: {}", response);
		
		return response;
    }

}
