package com.bj.security.core.validate.code.sms;

/**
 * 默认的短信发送功能，会被复写
 * Created by neko on 2018/3/12.
 */
public class DefaultSmsCodeSender implements SmsCodeSender{

    //这里可以接入第三方短信发送平台
    @Override
    public void send(String mobile, String code) {
        //模拟短信发送功能
        System.out.println("向手机 " + mobile + " 发送短信验证码: " + code);
    }

}
