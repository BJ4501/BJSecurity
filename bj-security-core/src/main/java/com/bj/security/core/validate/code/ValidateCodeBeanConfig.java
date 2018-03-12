package com.bj.security.core.validate.code;

import com.bj.security.core.properties.SecurityProperties;
import com.bj.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.bj.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by neko on 2018/3/12.
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    //当Spring容器启动时，会去先寻找名为：imageCodeGenerator的Bean，如果没有，则执行该方法的逻辑
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    //当Spring容器启动时，会去先寻找名为：imageCodeGenerator的Bean，如果没有，则执行该方法的逻辑
    //@ConditionalOnMissingBean(name = "smsCodeGenerator")
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeGenerator(){
        return new DefaultSmsCodeSender();
    }

}
