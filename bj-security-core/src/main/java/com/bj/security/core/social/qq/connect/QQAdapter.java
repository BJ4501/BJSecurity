package com.bj.security.core.social.qq.connect;

import com.bj.security.core.social.qq.api.QQ;
import com.bj.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * Created by neko on 2018/3/14.
 */
public class QQAdapter implements ApiAdapter<QQ> {

    //测试当前api是否可用
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues connectionValues) {
        QQUserInfo userInfo = api.getUserInfo();

        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(userInfo.getOpenId());

    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    //更新状态，某些网站存在
    @Override
    public void updateStatus(QQ api, String s) {
        //do nothing
    }

}
