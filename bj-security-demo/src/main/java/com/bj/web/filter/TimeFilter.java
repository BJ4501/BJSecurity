package com.bj.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * 过滤器
 * Created by neko on 2018/3/5.
 */
//@Component
public class TimeFilter implements Filter {

    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time Filter init");
    }

    //处理过滤器逻辑
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time Filter start");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("time filter :"+ (new Date().getTime() - start));
        System.out.println("time Filter end");
    }

    //销毁
    @Override
    public void destroy() {
        System.out.println("time Filter destroy");
    }
}
