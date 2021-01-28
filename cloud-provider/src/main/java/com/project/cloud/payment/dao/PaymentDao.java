package com.project.cloud.payment.dao;

import com.project.cloud.entity.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author Qiao guorui
 * @Date 2021/1/16 12:45 下午
 */
@Repository
public interface PaymentDao {

    int create(Payment payment);

    /**
     * 加上@Param注解，mapper中就可以采用#{}的方式把@Param注解括号内的参数进行引用
     */
    Payment getPaymentById(@Param("id") Long id);
}
