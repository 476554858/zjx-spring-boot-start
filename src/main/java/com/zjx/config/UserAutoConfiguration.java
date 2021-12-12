package com.zjx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserProperties.class)
@ConditionalOnProperty({"zjx.starter.begin"}) //该类配置存在则执行
public class UserAutoConfiguration {

    @Autowired
    UserProperties userProperties;

    @Bean
    //如果在引入start依赖的项目中已经有了User这个bean,则不在执行下边的加载bean的逻辑
    @ConditionalOnMissingBean(User.class)
    public User user(){
        User user = new User();
        user.setUserName(userProperties.getUserName());
        user.setAge(userProperties.getAge());
        System.out.println("自动配置生效，初始化User======================");
        return user;
    }
}
