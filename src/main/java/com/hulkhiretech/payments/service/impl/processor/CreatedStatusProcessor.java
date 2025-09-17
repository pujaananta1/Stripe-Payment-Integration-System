package com.hulkhiretech.payments.service.impl.processor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.dao.interfaces.TransactionDao;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.entity.TransactionEntity;
import com.hulkhiretech.payments.service.interfaces.TxnStatusProcessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreatedStatusProcessor implements TxnStatusProcessor {
	
	private final TransactionDao transactionDao;
	
	private final ModelMapper modelMapper;

	@Override
	public TransactionDTO processStatus(TransactionDTO txnDto) {
		log.info("Processing CREATED status|| txnDto: {}", txnDto);
		
		TransactionEntity txnEntity = modelMapper.map(
				txnDto, TransactionEntity.class);
		log.info("Mapped txnEntity: {}", txnEntity);  
		
		int pkId = transactionDao.insertTransaction(txnEntity);
		log.info("Inserted transaction with pkId: {}", pkId);
		
		txnDto.setId(pkId);
		
		log.info("Final txnDto after insertion: {}", txnDto);
		return txnDto;
	}

}
