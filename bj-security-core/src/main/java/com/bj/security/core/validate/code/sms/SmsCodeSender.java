package com.bj.security.core.validate.code.sms;

/**
 * Created by neko on 2018/3/12.
 */
public interface SmsCodeSender {

    void send(String mobile, String code);

}
