package com.project.cloud.payment.service;


import com.project.cloud.entity.Payment;

/**
 * @Author Qiao guorui
 * @Date 2021/1/16 1:09 下午
 */
public interface PaymentService {

    int create(Payment payment);

    /**
     * 加上@Param注解，mapper中就可以采用#{}的方式把@Param注解括号内的参数进行引用
     */
    Payment getPaymentById(Long id);

}
