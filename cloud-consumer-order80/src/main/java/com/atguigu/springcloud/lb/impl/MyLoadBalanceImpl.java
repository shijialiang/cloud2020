package com.atguigu.springcloud.lb.impl;

import com.atguigu.springcloud.lb.MyLoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: MyLoadBalance
 * @description:
 * @author: 石佳良
 * @create: 2020-10-22 14:20
 **/
@Component
@Slf4j
public class MyLoadBalanceImpl implements MyLoadBalance {

    private AtomicInteger atomicInteger=new AtomicInteger(0);
    Boolean b=false;

    /**
     * cas 自旋锁
     * @return
     */
    public final int cas(){
        int current,next;
        current=this.atomicInteger.get();
        next= current >= 2147483647 ? 0 :current+1;
        do{
            b=atomicInteger.compareAndSet(current, next);
        }while (!b);
        log.info("*************第{}次访问",next);
        return next;
    }

    /**
     * 获取服务实例
     * @param serviceInstances
     * @return
     */
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int i = cas() % serviceInstances.size();
        log.info("*************服务器实例{}",serviceInstances.get(i));
        return serviceInstances.get(i);
    }
}
