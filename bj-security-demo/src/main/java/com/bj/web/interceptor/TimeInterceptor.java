package com.bj.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by neko on 2018/3/5.
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    //控制器方法被调用前
    //@return boolean 表示之后的方法是否需要执行
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("preHandle");
        System.out.println(((HandlerMethod)o).getBean().getClass().getName());
        System.out.println(((HandlerMethod)o).getMethod().getName());
        httpServletRequest.setAttribute("startTime",new Date().getTime());
        return true;
    }

    //控制器方法被调用之后
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        Long start = (Long)httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor postHandle :"+ (new Date().getTime() - start));
    }

    //最终都会调用这个方法
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
        Long start = (Long)httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor afterCompletion :"+ (new Date().getTime() - start));
        System.out.println("exception : "+e);
    }
}
