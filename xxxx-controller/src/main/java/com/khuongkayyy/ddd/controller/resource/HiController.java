package com.khuongkayyy.ddd.controller.resource;

import com.khuongkayyy.ddd.application.service.event.EventAppService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;

@RestController
@RequestMapping("hello")
public class HiController {
    @Autowired
    private EventAppService eventAppService;
    private RestTemplate restTemplate = new RestTemplate();


    @GetMapping("/hi")
    @RateLimiter(name = "backendA",fallbackMethod = "fallbackHello")
    public String hi() {
        return  eventAppService.sayHi("Khuongkayyy");
    }

    public  String fallbackHello(Throwable throwable) {
        return "too many requests";
    }

    @GetMapping("/hi/v1")
    @RateLimiter(name = "backendB",fallbackMethod = "fallbackHello")
    public String sayHi() {
        return  eventAppService.sayHi("Khuongkayyy V1");
    }

    private static final SecureRandom secureRandom = new SecureRandom();
    @GetMapping("/circuit/breaker")
    @CircuitBreaker(name = "checkRandom",fallbackMethod = "fallbackCircuitBreaker")
    public String circuitBreaker() {
        int i = secureRandom.nextInt(20) +1;
        String url = "https://fakestoreapi.com/products/"+i;
        return restTemplate.getForObject(url, String.class);
    }

    public  String fallbackCircuitBreaker(Throwable throwable) {
        return "Service fakeAPI error";
    }
}