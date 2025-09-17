package com.hulkhiretech.payments.service.impl.processor;

import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.service.interfaces.TxnStatusProcessor;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InitiatedStatusProcessor implements TxnStatusProcessor {

	@Override
	public TransactionDTO processStatus(TransactionDTO txnDto) {
		log.info("Processing INITIATED status");
		
		// TODO logic to update txn record in DB as INITIATED
		
		
		return txnDto;
	}

}
