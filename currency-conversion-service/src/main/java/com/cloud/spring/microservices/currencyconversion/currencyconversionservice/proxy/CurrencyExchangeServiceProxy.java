package com.cloud.spring.microservices.currencyconversion.currencyconversionservice.proxy;

import com.cloud.spring.microservices.currencyconversion.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//http://localhost:8000/currency-exchange/from/{from}/to/{to}

//@FeignClient(name = "currency-exchange", url = "localhost:8000")// without eureka server we have to mention port no as well
//@FeignClient(name = "currency-exchange")
//CHANGE-KUBERNETES
//CURRENCY_EXCHANGE_SERVICE_HOST will be automatically created by kubernetes
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000") // This is from k8s env variable

public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}