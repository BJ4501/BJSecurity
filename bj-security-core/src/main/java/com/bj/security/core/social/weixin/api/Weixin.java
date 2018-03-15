package com.bj.security.core.social.weixin.api;

/**
 * 微信API调用接口
 * Created by neko on 2018/3/15.
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);

}
