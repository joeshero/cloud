package com.project.cloud.service;

import com.project.cloud.entity.CommonResult;
import org.springframework.stereotype.Component;

/**
 * @Author Qiao guorui
 * @Date 2021/1/21 12:01 上午
 */
@Component
public class PaymentFeignFallbackService implements PaymentFeignService {

    @Override
    public CommonResult getPaymentById(Long id) {
        return null;
    }

    @Override
    public String getHyOk(Long id) {
        return "----PaymentFallbackService\t fallback-paymentInfo_OK----";    }

    @Override
    public String getHyTimeOut(Long id) {
        return "----PaymentFallbackService\t fallback-paymentInfo_TimeOut----";    }
}
