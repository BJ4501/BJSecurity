package com.bj.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by neko on 2018/3/12.
 */
public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request);
}
