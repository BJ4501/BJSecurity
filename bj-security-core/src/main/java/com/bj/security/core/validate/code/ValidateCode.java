package com.bj.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Created by neko on 2018/3/9.
 */
public class ValidateCode {

    //随机数
    private String code;

    //过期时间
    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
