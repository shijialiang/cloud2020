package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: OrderHystrixService
 * @description:
 * @author: 石佳良
 * @create: 2020-11-05 14:21
 **/
@Service
@FeignClient(value = "cloud-payment-hystrix-server")
public interface OrderHystrixService {
    @GetMapping("/paymentHystrix/showInfo_ok/{id}")
    public String showInfo_ok(@PathVariable("id") Integer id);

    @GetMapping("/paymentHystrix/showInfo_wait/{id}")
    public String showInfo_wait(@PathVariable("id") Integer id);
}
