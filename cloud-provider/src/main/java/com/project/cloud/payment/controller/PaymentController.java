package com.project.cloud.payment.controller;

import com.project.cloud.entity.CommonResult;
import com.project.cloud.entity.Payment;
import com.project.cloud.payment.service.HystrixServiceImpl;
import com.project.cloud.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @Author Qiao guorui
 * @Date 2021/1/16 2:26 下午
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private HystrixServiceImpl hystrixServiceImpl;


    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("******插入的数据为：" + payment);
        log.info("******插入结果：" + result);

        if(result > 0){
            //插入成功
            return new CommonResult(200, "插入数据库成功, serverPort:" + port, result);
        }else{
            return new CommonResult(444, "插入数据库失败");
        }
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询结果：" + payment);

        if(payment != null){
            //查询成功
            return new CommonResult(200, "查询成功,, serverPort:" + port, payment);
        }else{
            return new CommonResult(444, "没有对应记录，查询ID：" + id);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("**element:{}**", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort()
            + "\t" + instance.getUri());
        }
        return discoveryClient;
    }

    @GetMapping("/payment/zk")
    public String paymentZk() {
        return "springCloud with zookeeper :" + port + "\t" + UUID.randomUUID().toString();
    }

    @GetMapping("/payment/consul")
    public String paymentConsul() {
        return "springCloud with consul :" + port + "\t" + UUID.randomUUID().toString();
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return port;
    }

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        long now = System.currentTimeMillis();
        String result = hystrixServiceImpl.paymentInfo_OK(id);
        log.info("******result：{}, 耗时:{}ms ", result, System.currentTimeMillis() - now);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(Integer id){
        String result = hystrixServiceImpl.paymentInfo_Timeout(id);
        log.info("******result：" + result);
        return result;
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = hystrixServiceImpl.paymentCircuitBreaker(id);
        log.info("******result：" + result);
        return result;
    }
}
