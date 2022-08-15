package com.cloud.spring.microservices.currencyexchange.currencyexchangeservice.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {

    private Logger logger =
            LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    // circuit breaker with default configuration
    //@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")

    //Rate limit with default configuration
    @RateLimiter(name="default")
    // bulk head for concurrent calls allowed
   // @Bulkhead(name="sample-api", fallbackMethod = "hardcodedResponse")
    //10s => 10000 calls to the sample api
    public String sampleApi() {
        logger.info("Sample api call received");

        // want to intimate the behavior of a service which is not working
       /* ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        logger.info("Sample api call processed");
		return forEntity.getBody();*/

		// enable below for rate limit example
        return "sample-api";
    }

    // return type should be same as main method otherwise runtime exception will be thrown
    public String hardcodedResponse(Exception ex) {
        logger.info("inside hardcodedResponse");
        return "fallback-response";
    }
}