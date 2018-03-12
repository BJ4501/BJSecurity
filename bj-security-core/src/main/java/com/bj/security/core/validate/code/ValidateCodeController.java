package com.bj.security.core.validate.code;

import com.bj.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by neko on 2018/3/9.
 */
@RestController
public class ValidateCodeController {

    private static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    //验证码生成器
    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    //请求级配置
    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ImageCode imageCode = imageCodeGenerator.generate(new ServletWebRequest(request));

        //操作添加到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);

        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());

    }



}
