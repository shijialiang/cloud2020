package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/payment")
@Slf4j
@EnableDiscoveryClient
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int count = paymentService.create(payment);
        log.info("*******开始写入数据********");
        if (count > 0) {
            return new CommonResult(200, "创建成功", count);
        } else {
            return new CommonResult(500, "创建失败", count);
        }
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult(200, "查询成功,端口为：" + serverPort, payment.toString());
        } else {
            return new CommonResult(500, "查询失败，未查到id为" + id + "的数据，端口为：" + serverPort, null);
        }
    }

    @GetMapping("/getDiscoverInfo")
    public Object getDiscoverInfo(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("services:"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("InstanceId:{},Host:{},Port:{},Uri:{}",instance.getInstanceId(),instance.getHost(),instance.getPort(),instance.getUri());
        }
        return discoveryClient;
    }
    @GetMapping("/getPaymentLB")
    public String getPaymentLB() {
        return serverPort;
    }
}
