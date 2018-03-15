package com.bj.security.core.properties;

/**
 * Created by neko on 2018/3/9.
 */
public class BrowserProperties {

    private String signUpUrl = "/bj-signUp.html";

    private String loginPage = "/bj-signIn.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 36000;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }
}
