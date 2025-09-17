package com.hulkhiretech.payments.dao.impl;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.hulkhiretech.payments.dao.interfaces.TransactionDao;
import com.hulkhiretech.payments.entity.TransactionEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TransactionDaoImpl implements TransactionDao {
	
	private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT_SQL = """
        INSERT INTO payments.`Transaction` (
            userId, paymentMethodId, providerId, paymentTypeId, txnStatusId,
            amount, currency, merchantTransactionReference, txnReference
        ) VALUES (
            :userId, :paymentMethodId, :providerId, :paymentTypeId, :txnStatusId,
            :amount, :currency, :merchantTransactionReference, :txnReference
        )
        """;

	@Override
	public Integer insertTransaction(TransactionEntity txn) {
		log.info("Inserting transaction: {}", txn);
		KeyHolder keyHolder = new GeneratedKeyHolder();
        
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(txn);

        jdbcTemplate.update(INSERT_SQL, params, keyHolder, new String[]{"id"});
        // set the generated id back to the entity
        txn.setId(keyHolder.getKey().intValue());
        
        log.info("Inserted transaction with generated id: {}", txn.getId());
        return txn.getId();
	}

}
