package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: MySelfRule
 * @description:
 * @author: 石佳良
 * @create: 2020-10-21 14:55
 **/
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new RoundRobinRule();
    }
}
