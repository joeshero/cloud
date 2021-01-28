package com.project.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.project.cloud.entity.CommonResult;
import com.project.cloud.entity.Payment;
import com.project.cloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@DefaultProperties(defaultFallback = "payment_global_fallback")
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String getHystrixOk(@PathVariable("id") Long id){
        long now = System.currentTimeMillis();
        String commonResult = paymentFeignService.getHyOk(id);
        log.info("cost:{}ms" , System.currentTimeMillis() - now);
        return commonResult;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String getHystrixTimeout(@PathVariable("id") Long id){
        return paymentFeignService.getHyTimeOut(id);
    }

    //全局的fallback方法
    public String payment_global_fallback() {
        return "处理异常，请稍后再试";
    }
}