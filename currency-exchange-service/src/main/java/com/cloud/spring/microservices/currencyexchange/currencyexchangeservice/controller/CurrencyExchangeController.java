package com.cloud.spring.microservices.currencyexchange.currencyexchangeservice.controller;

import com.cloud.spring.microservices.currencyexchange.currencyexchangeservice.model.CurrencyExchange;
import com.cloud.spring.microservices.currencyexchange.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue
            (@PathVariable String from, @PathVariable String to){

        CurrencyExchange currencyExchange =
                repository.findByFromAndTo(from, to);

        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));

        logger.info("{}", currencyExchange);

        return currencyExchange;

    }
}