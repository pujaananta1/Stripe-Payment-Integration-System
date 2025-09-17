package com.hulkhiretech.payments.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.constant.TransactionStatusEnum;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;
import com.hulkhiretech.payments.pojo.InitiateTxnRequest;
import com.hulkhiretech.payments.service.interfaces.PaymentService;
import com.hulkhiretech.payments.service.interfaces.PaymentStatusService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	
	private final PaymentStatusService paymentStatusService;
	
	private final ModelMapper modelMapper;

	@Override
	public CreateTxnResponse createTxn(CreateTxnRequest createTxnRequest) {
		log.info("Creating payment transaction createTxnRequest:{}",
                createTxnRequest);
		
		TransactionDTO txnDto = modelMapper.map(
				createTxnRequest, TransactionDTO.class);
		log.info("Mapped txnDto: {}", txnDto);
		
		String txnStatus = TransactionStatusEnum.CREATED.name();
		String txnReference = generateUniqueTxnRef();
		
		txnDto.setTxnStatus(txnStatus);
		txnDto.setTxnReference(txnReference);
		
		log.info("Final txnDto to be saved: {}", txnDto);
		
		TransactionDTO response = paymentStatusService.processStatus(txnDto);
		log.info("Response from PaymentStatusService: {}", response);
		
		CreateTxnResponse createTxnResponse = new CreateTxnResponse();
		createTxnResponse.setTxnReference(response.getTxnReference());
		createTxnResponse.setTxnStatus(response.getTxnStatus());
		
		log.info("CreateTxnResponse to be returned: {}", createTxnResponse);
		
		return createTxnResponse;
	}

	private String generateUniqueTxnRef() {
		return UUID.randomUUID().toString();
	}

	@Override
	public String initiateTxn(String id, InitiateTxnRequest initiateTxnRequest) {
		log.info("Initiating payment transaction||id:{}|initiateTxnRequest:{}", 
				id, initiateTxnRequest);
		
		//TODO
		// update DB as INITIATED

		// Make Rest Http API call to stripe-provider-service for create-payment api

		// Update DB as PENDING, providerReference
		
		// return the url back to the invoker.
		
		return "return initiateTxn from service=>" + id;
	}

}
