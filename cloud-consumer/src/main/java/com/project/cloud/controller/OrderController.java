package com.project.cloud.controller;

import com.project.cloud.entity.CommonResult;
import com.project.cloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Qiao guorui
 * @Date 2021/1/16 11:02 下午
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create", payment, CommonResult.class);
    }

//    @GetMapping("/consumer/payment/get/{id}")
//    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
//        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/" + id, CommonResult.class);
//    }

    @GetMapping("/consumer/payment/zk")
    public String getPaymentZkInfo() {
        StringBuilder sb = new StringBuilder();
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zk", String.class);
    }

    @GetMapping("/consumer/payment/consul")
    public String getPaymentConsulInfo() {
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul", String.class);
    }

    @GetMapping("/consumer/payment/createEntity")
    public CommonResult<Payment> create2(Payment payment){
        log.info("********插入的数据：" + payment);
        //postForObject分别有三个参数：请求地址，请求参数，返回的对象类型
//        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444, "操作失败");
        }
    }

}
