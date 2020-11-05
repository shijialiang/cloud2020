package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @program: PaymentHystrixServiceImpl
 * @description:
 * @author: 石佳良
 * @create: 2020-10-29 9:41
 **/
@Service
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String showInfo_ok(Integer id) {
        return "当前进程为："+Thread.currentThread().getName()+",执行方法为：showInfo_ok"+"，Id为："+id;
    }

    @HystrixCommand(fallbackMethod = "showInfo_waitHandler",//超时后回调方法
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",//时间单位
                            value="3000")})//超时时间
    @Override
    public String showInfo_wait(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "当前进程为："+Thread.currentThread().getName()+",执行方法为：showInfo_ok"+"，Id为："+id;
    }


    public String showInfo_waitHandler(Integer id) {
        return "当前进程为："+Thread.currentThread().getName()+",执行兜底方法方法为：showInfo_waitHandler"+"，Id为："+id;
    }


}
