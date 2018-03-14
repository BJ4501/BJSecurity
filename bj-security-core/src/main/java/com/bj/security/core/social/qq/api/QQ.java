package com.bj.security.core.social.qq.api;

import java.io.IOException;

/**
 * Created by neko on 2018/3/13.
 */
public interface QQ {

    QQUserInfo getUserInfo() throws IOException;

}
