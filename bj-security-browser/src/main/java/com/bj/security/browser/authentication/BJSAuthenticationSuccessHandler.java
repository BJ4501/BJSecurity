package com.bj.security.browser.authentication;


import com.bj.security.core.properties.LoginType;
import com.bj.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by neko on 2018/3/9.
 */
@Component("bjsAuthenticationSuccessHandler")
public class BJSAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    //当登录成功后会被调用
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");

        //如果配置的登录方式是Json
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            //将authentication转换为一个字符串
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            //父类逻辑：跳转Redirect
            super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        }

    }

}
