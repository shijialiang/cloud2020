package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @program: MyLoadBalance
 * @description:
 * @author: 石佳良
 * @create: 2020-10-22 16:10
 **/
public interface MyLoadBalance {
    //获取服务实例
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
