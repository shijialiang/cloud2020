package com.atguigu.springcloud.controller;

import cn.hutool.core.lang.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consul")
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consulTest")
    public String consulTest(){
        return "Consul 正在运行，服务端口："+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
