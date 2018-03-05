package com.bj.exception;

/**
 * 自定义-用户不存在异常
 * Created by neko on 2018/3/5.
 */
public class UserNotExistException extends RuntimeException{

    private String id;

    public UserNotExistException(String id){
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
