package com.bj.security.browser;

import com.bj.security.core.authentication.AbstractChannelSecurityConfig;
import com.bj.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.bj.security.core.properties.SecurityConstants;
import com.bj.security.core.properties.SecurityProperties;
import com.bj.security.core.validate.code.ValidateCodeSecurityConfig;
import com.bj.security.core.validate.code.sms.SmsCodeFilter;
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
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Web 应用安全适配器
 * Created by neko on 2018/3/7.
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

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
    ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer bjsSocialSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy BjsSessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy BjsInvalidSessionStrategy;

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

/*
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
                .sessionManagement()
                    .invalidSessionStrategy(BjsInvalidSessionStrategy)//当session失效时跳转的地址
                    .maximumSessions(secutiryProperties.getBrowser().getSession().getMaximumSessions())//最大session数量
                    .maxSessionsPreventsLogin(secutiryProperties.getBrowser().getSession().isMaxSessionsPreventsLogin()) //当session数量达到最大数量时，阻止后序登录行为(同用户登录
                    .expiredSessionStrategy(BjsSessionInformationExpiredStrategy) //记录session
                    .and()
                    .and()
                .authorizeRequests() //请求授权
                .antMatchers("/authentication/require",
                        secutiryProperties.getBrowser().getLoginPage(),
                        "/code/*",
                        secutiryProperties.getBrowser().getSignUpUrl(),
                        "/user/regist","/session/invalid")
                .permitAll() //匹配器，记录额外不需要认证的页面
                .anyRequest() //任何请求
                .authenticated() //都需要身份验证
                .and()
                .csrf().disable() //CSRF跨站请求防护功能关闭
                .apply(smsCodeAuthenticationSecurityConfig) //加入配置
                .and()
                .apply(bjsSocialSecurityConfig);
*/

        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(bjsSocialSecurityConfig)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                .invalidSessionStrategy(BjsInvalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(BjsSessionInformationExpiredStrategy)
                .and()
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        "/user/regist")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

}
