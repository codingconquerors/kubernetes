package com.cloud.spring.microservices.currencyexchange.currencyexchangeservice.repository;

import com.cloud.spring.microservices.currencyexchange.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends
        JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}