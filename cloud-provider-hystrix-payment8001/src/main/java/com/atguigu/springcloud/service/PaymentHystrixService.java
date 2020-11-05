package com.atguigu.springcloud.service;

/**
 * @program: PaymentHystrixService
 * @description:
 * @author: 石佳良
 * @create: 2020-10-29 9:40
 **/
public interface PaymentHystrixService {
    public String showInfo_ok(Integer id);

    public String showInfo_wait(Integer id);
}
