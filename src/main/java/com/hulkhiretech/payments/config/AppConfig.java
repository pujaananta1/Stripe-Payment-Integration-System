package com.hulkhiretech.payments.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.entity.TransactionEntity;
import com.hulkhiretech.payments.util.convert.nametoid.PaymentMethodEnumConverter;
import com.hulkhiretech.payments.util.convert.nametoid.PaymentTypeEnumConverter;
import com.hulkhiretech.payments.util.convert.nametoid.ProviderEnumConverter;
import com.hulkhiretech.payments.util.convert.nametoid.TransactionStatusEnumConverter;

@Configuration
public class AppConfig {

	@Bean
    ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
        
		// Only map when field names match exactly
        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.STRICT)
                   .setSkipNullEnabled(true); // Optional: ignore nulls
        
        Converter<String, Integer> paymentMethodEnumConverter = new PaymentMethodEnumConverter();
        Converter<String, Integer> providerEnumConverter = new ProviderEnumConverter();
        // Define converters for TxnStatusEnum and PaymentTypeEnum if needed
        Converter<String, Integer> paymentTypeEnumConverter = new PaymentTypeEnumConverter();
        Converter<String, Integer> transactionStatusEnumConverter = new TransactionStatusEnumConverter();
        
        
        modelMapper.addMappings(new PropertyMap<TransactionDTO, TransactionEntity>() {
            @Override
            protected void configure() {
                using(paymentMethodEnumConverter).map(source.getPaymentMethod(), destination.getPaymentMethodId());
                using(providerEnumConverter).map(source.getProvider(), destination.getProviderId());
                // Add mappings for txnStatusId and paymentTypeId with their respective converters
                using(paymentTypeEnumConverter).map(source.getPaymentType(), destination.getPaymentTypeId());
                using(transactionStatusEnumConverter).map(source.getTxnStatus(), destination.getTxnStatusId());
            }
        });
        
        return modelMapper;
    }
}
