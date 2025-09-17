package com.hulkhiretech.payments.service.impl;

import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.constant.TransactionStatusEnum;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.service.PaymentStatusFactory;
import com.hulkhiretech.payments.service.interfaces.PaymentStatusService;
import com.hulkhiretech.payments.service.interfaces.TxnStatusProcessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentStatusServiceImpl implements PaymentStatusService {
	
	private final PaymentStatusFactory statusFactory;

	@Override
	public TransactionDTO processStatus(TransactionDTO txnDto) {
		log.info("Processing status for txnDto: {}", txnDto);
		
		TransactionStatusEnum statusEnum = TransactionStatusEnum.fromName(
				txnDto.getTxnStatus());
		
		TxnStatusProcessor statusProcessor = statusFactory.getStatusProcessor(
				statusEnum);
		log.info("Retrieved status statusProcessor: {}", statusProcessor);
		
		if (statusProcessor == null) {
			throw new IllegalArgumentException("Invalid txnStatusId: " + txnDto.getTxnStatus());
		}
		
		TransactionDTO response = statusProcessor.processStatus(txnDto);
		log.info("Status processed with response: {}", response);
		
		return response;
	}

}
