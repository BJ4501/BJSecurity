package com.bj.security.core.validate.code;

import com.bj.security.core.properties.SecurityConstants;

/**
 * Created by neko on 2018/3/13.
 */
public enum ValidateCodeType {

    /**
     * 短信验证码
     */
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    public abstract String getParamNameOnValidate();


}
