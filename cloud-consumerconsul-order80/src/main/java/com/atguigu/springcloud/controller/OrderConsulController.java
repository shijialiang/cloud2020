package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/orderConsul")
public class OrderConsulController {
    @Resource
    private RestTemplate restTemplate;
    private static final String serviceURL="http://consul-provider-payment";

    @GetMapping("/getConsulTest")
    public String getConsulTest(){
        return restTemplate.getForObject(serviceURL+"/consul/consulTest",String.class);
    }
}
