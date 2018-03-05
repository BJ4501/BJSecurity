package com.bj.web.controller;

import com.bj.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by neko on 2018/3/5.
 */
@ControllerAdvice //该注解作用是处理其他Controller抛出的异常
public class ControllerExceptionHandler {

    //之后遇到UserNotExistException的异常都会转到这个方法去处理
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleUserNotExistException(UserNotExistException ex){
        Map<String,Object> result = new HashMap<>();
        result.put("id",ex.getId());
        result.put("message",ex.getMessage());
        return result;
    }


}
