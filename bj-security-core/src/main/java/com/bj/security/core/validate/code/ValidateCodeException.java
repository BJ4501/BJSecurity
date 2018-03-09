package com.bj.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by neko on 2018/3/9.
 */
public class ValidateCodeException extends AuthenticationException{

    //AuthenticationException 校验异常的基类

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
