package com.bj.web.config;

import com.bj.web.filter.TimeFilter;
import com.bj.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 加载第三方过滤器方法
 * Created by neko on 2018/3/5.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private TimeInterceptor timeInterceptor;

    //用于配置异步支持
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //如果是异步请求，需要单独注册拦截器
        configurer.registerCallableInterceptors();
        configurer.registerDeferredResultInterceptors();
        //异步请求默认超时时间
        configurer.setDefaultTimeout(1);
        //设置自己可重用的线程池，替代默认线程池(Spring默认提供，不重用)
        configurer.setTaskExecutor(null);
    }

    //@Bean
    public FilterRegistrationBean timeFilter(){

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*"); //配置什么路径起作用
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }

    //拦截器注册器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        //registry.addInterceptor(timeInterceptor);
    }
}
