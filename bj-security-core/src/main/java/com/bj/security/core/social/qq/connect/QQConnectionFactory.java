package com.bj.security.core.social.qq.connect;

import com.bj.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * Created by neko on 2018/3/14.
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ>{

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }

}
