package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: PaymentHystrixController
 * @description:
 * @author: 石佳良
 * @create: 2020-10-29 9:46
 **/
@RestController
@RequestMapping("/paymentHystrix")
public class PaymentHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/showInfo_ok/{id}")
    public String showInfo_ok(@PathVariable("id") Integer id){
        return paymentHystrixService.showInfo_ok(id);
    }

    @GetMapping("/showInfo_wait/{id}")
    public String showInfo_wait(@PathVariable("id") Integer id){
        return paymentHystrixService.showInfo_wait(id);
    }



}
