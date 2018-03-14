package com.bj.security.core.social.qq.connect;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * Created by neko on 2018/3/14.
 */
public class QQOAuth2Template extends OAuth2Template {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        //true:发送请求时会携带client_id,client_secret
        setUseParametersForClientAuthentication(true);
    }

    //对QQ的请求做自定义解析
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String response = getRestTemplate().postForObject(accessTokenUrl,parameters,String.class);
        logger.info("获取accessToken的响应：" + response);

        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(response,"&");

        String accessToken = StringUtils.substringAfterLast(items[0],"=");
        Long expiresIn = Long.valueOf(StringUtils.substringAfterLast(items[1],"="));
        String refreshToken = StringUtils.substringAfterLast(items[2],"=");

        return new AccessGrant(accessToken,null,refreshToken,expiresIn);
    }

    //复写父类方法，添加对text/html支持,解决ContentType问题
    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

}
