package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.MyLoadBalance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {
    private static final String URL="http://CLOUD-PAYMENT-SERVICE/payment";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private MyLoadBalance myLoadBalance;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("******消费者调用create*********");
        CommonResult commonResult = restTemplate.postForObject(URL + "/create", payment, CommonResult.class);
        return commonResult;
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){
        log.info("******消费者调用getPaymentById*********");
        return restTemplate.getForObject(URL+"/getPaymentById/"+id,CommonResult.class);
    }

    @GetMapping("/createEntity")
    public CommonResult<Payment> createEntity(Payment payment){
        log.info("******消费者调用create*********");
        ResponseEntity<CommonResult> commonResultResponseEntity = restTemplate.postForEntity(URL + "/create", payment, CommonResult.class);
        if(commonResultResponseEntity.getStatusCode().is2xxSuccessful()){
            log.info("*********statusCode:"+commonResultResponseEntity.getStatusCode());
            log.info("*********headers:"+commonResultResponseEntity.getHeaders());
            return commonResultResponseEntity.getBody();
        }else{
            return new CommonResult<>(444,"创建失败");
        }
    }

    @GetMapping("/getEntityPaymentById/{id}")
    public CommonResult<Payment> getEntityPaymentById(@PathVariable Long id){
        log.info("******消费者调用getPaymentById*********");
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(URL + "/getPaymentById/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else{
            return new CommonResult<>(444,"查询失败");
        }
    }

    @GetMapping("/getPaymentLB")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances ==null || instances.size()<=0){
            return null;
        }
        ServiceInstance instance = myLoadBalance.instance(instances);
         return restTemplate.getForObject(instance.getUri()+"/payment/getPaymentLB",String.class);
    }
}
