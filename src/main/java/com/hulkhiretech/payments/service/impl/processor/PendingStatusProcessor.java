package com.hulkhiretech.payments.service.impl.processor;

import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.service.interfaces.TxnStatusProcessor;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PendingStatusProcessor implements TxnStatusProcessor {

	@Override
	public TransactionDTO processStatus(TransactionDTO txnDto) {
		log.info("Processing PENDING status");
		
		// TODO logic to save txn record in DB as PENDING, providerReference
		
		
		return txnDto;
	}

}
