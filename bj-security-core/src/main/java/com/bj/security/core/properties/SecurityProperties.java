package com.bj.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by neko on 2018/3/9.
 */
@ConfigurationProperties(prefix = "bj.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
