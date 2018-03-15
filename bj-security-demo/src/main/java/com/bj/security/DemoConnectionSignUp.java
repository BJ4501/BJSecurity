package com.bj.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * Created by neko on 2018/3/15.
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户，并返回用户唯一标识

        //此处填写真正的业务逻辑

        return connection.getDisplayName();
    }

}
