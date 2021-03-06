package com.bj.web.controller;

import io.swagger.annotations.ApiModelProperty;

/**
 * Query方法的参数组装
 * Created by neko on 2018/3/2.
 */
public class UserQueryCondition {

    private String username;

    @ApiModelProperty(value = "年龄")
    private int age;

    private int ageTo;

    private String otherSetting;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }

    public String getOtherSetting() {
        return otherSetting;
    }

    public void setOtherSetting(String otherSetting) {
        this.otherSetting = otherSetting;
    }
}
