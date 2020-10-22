package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.OrderFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: OrderFeignController
 * @description:
 * @author: 石佳良
 * @create: 2020-10-22 18:00
 **/
@RestController
public class OrderFeignController {
    @Resource
    private OrderFeignService orderFeignService;

    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return orderFeignService.getPaymentById(id);
    }
}
