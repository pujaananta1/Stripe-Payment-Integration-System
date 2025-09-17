package com.hulkhiretech.payments.service.interfaces;

import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;
import com.hulkhiretech.payments.pojo.InitiateTxnRequest;

public interface PaymentService {
	
	public CreateTxnResponse createTxn(CreateTxnRequest createTxnRequest);
	
	public String initiateTxn(String id, InitiateTxnRequest initiateTxnRequest);

}
