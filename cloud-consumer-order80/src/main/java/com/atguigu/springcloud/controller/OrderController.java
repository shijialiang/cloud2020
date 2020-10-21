package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {
    private static final String URL="http://CLOUD-PAYMENT-SERVICE/payment";
    @Resource
    private RestTemplate restTemplate;

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
}
