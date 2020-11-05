package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: OrderHystrixController
 * @description:
 * @author: 石佳良
 * @create: 2020-11-05 14:23
 **/
@RestController
@RequestMapping("/consumer")
public class OrderHystrixController {
    @Autowired
    private OrderHystrixService orderHystrixService;

    @GetMapping("/showInfo_ok/{id}")
    public String showInfo_ok(@PathVariable("id") Integer id){
        return orderHystrixService.showInfo_ok(id);
    }

    @GetMapping("/showInfo_wait/{id}")
    @HystrixCommand(fallbackMethod = "showInfo_waitHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    public String showInfo_wait(@PathVariable("id") Integer id){
        return orderHystrixService.showInfo_wait(id);
    }
    public String showInfo_waitHandler(@PathVariable("id") Integer id){
        return "我是消费者80的兜底方法";
    }

}
