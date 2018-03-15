package com.bj.security.browser;

import com.bj.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.bj.security.core.properties.SecurityProperties;
import com.bj.security.core.validate.code.SmsCodeFilter;
import com.bj.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Web 应用安全适配器
 * Created by neko on 2018/3/7.
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties secutiryProperties;

    @Autowired
    private AuthenticationSuccessHandler bjsAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler bjsAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer bjsSocialSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder(){
        //这里可以配置成需要的密码加密工具，只要继承PasswordEncoder并实现即可
        return new BCryptPasswordEncoder();
    }

    //配置RememberMe功能的持久化功能
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //在启动时创建表
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //自定义图片验证码过滤器
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(bjsAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(secutiryProperties);
        validateCodeFilter.afterPropertiesSet();

        //自定义Sms过滤器
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(bjsAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(secutiryProperties);
        smsCodeFilter.afterPropertiesSet();

        //定义一个最简单的安全环境
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin() //表单登录  httpBasic()基本Basic登录
                    .loginPage("/authentication/require") //自定义登录页面
                    .loginProcessingUrl("/authentication/form") //将表单登录拦截器的默认地址改为这个
                    .successHandler(bjsAuthenticationSuccessHandler) //表单登录成功后，会使用这个成功处理器
                    .failureHandler(bjsAuthenticationFailureHandler) //表单登录失败后，会使用这个失败处理器
                    .and()
                .rememberMe() //"记住我"的配置
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(secutiryProperties.getBrowser().getRememberMeSeconds()) //token有效时间
                    .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests() //请求授权
                .antMatchers("/authentication/require",
                        secutiryProperties.getBrowser().getLoginPage(),
                        "/code/*",
                        secutiryProperties.getBrowser().getSignUpUrl(),
                        "/user/regist")
                .permitAll() //匹配器，记录额外不需要认证的页面
                .anyRequest() //任何请求
                .authenticated() //都需要身份验证
                .and()
                .csrf().disable() //CSRF跨站请求防护功能关闭
                .apply(smsCodeAuthenticationSecurityConfig) //加入配置
                .and()
                .apply(bjsSocialSecurityConfig);
    }

}
