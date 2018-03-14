package com.bj.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Created by neko on 2018/3/14.
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
