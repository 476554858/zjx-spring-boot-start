package com.zjx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HelloServiceProperties.class)
@ConditionalOnProperty(prefix = "com.zjx", value = "enabled", matchIfMissing = true)
public class HelloServiceAutoConfiguration {

    @Autowired
    private HelloServiceProperties helloServiceProperties;

    @Bean
    //如果在引入start依赖的项目中已经有了HelloServiceConfiguration这个bean,则不在执行下边的加载bean的逻辑
    @ConditionalOnMissingBean(HelloServiceConfiguration.class)
    public HelloServiceConfiguration helloServiceConfiguration() {
        HelloServiceConfiguration helloService = new HelloServiceConfiguration();
        helloService.setName(helloServiceProperties.getName());
        helloService.setHobby(helloServiceProperties.getHobby());
        return helloService;
    }
}
