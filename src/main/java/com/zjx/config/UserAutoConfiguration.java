package com.zjx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ZjxTest.class)  //该类存在则执行
@EnableConfigurationProperties(UserProperties.class)
@ConditionalOnProperty({"zjx.starter.begin"}) //该类配置存在则执行
public class UserAutoConfiguration {

    @Autowired
    UserProperties userProperties;

    @Bean
    @ConditionalOnMissingBean(ZjxTest.class) //该类不存在 则执行
    public ZjxTest zjxTest(){
        String userName = userProperties.getUserName();
        ZjxTest zjxTest=  new ZjxTest();
        zjxTest.setSpeed("快");
        System.out.println("自动配置生效，初始化zjxTest======================");
        return zjxTest;
    }
}
