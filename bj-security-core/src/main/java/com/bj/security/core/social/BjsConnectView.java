package com.bj.security.core.social;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 处理绑定成功逻辑的View
 * Created by neko on 2018/3/16.
 */
public class BjsConnectView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception {

        httpServletResponse.setContentType("text/html;charset=UTF-8");
        //map中如果有connection是一个绑定操作
        if (map.get("connection") != null){
            //绑定成功
            httpServletResponse.getWriter().write("<h3>Bind Success</h3>");
        }else {
            //解绑成功
            httpServletResponse.getWriter().write("<h3>Unbind Success</h3>");
        }

    }

}
