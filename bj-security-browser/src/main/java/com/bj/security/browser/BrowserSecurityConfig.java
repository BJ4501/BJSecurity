package com.bj.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Web 应用安全适配器
 * Created by neko on 2018/3/7.
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        //这里可以配置成需要的密码加密工具，只要继承PasswordEncoder并实现即可
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //定义一个最简单的安全环境
        http.formLogin() //表单登录  httpBasic()基本Basic登录
                .and()
                .authorizeRequests() //请求授权
                .anyRequest() //任何请求
                .authenticated(); //都需要身份验证
    }

}
