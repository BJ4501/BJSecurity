package com.bj.security.core.validate.code;

import com.bj.security.core.properties.SecurityProperties;
import com.bj.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
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

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    //请求级配置
    @Autowired
    private SecurityProperties securityProperties;

    //发送图片验证码接口
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //生成
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
        //操作添加到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        //发送
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());

    }

    //发送短信验证码接口
    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {

        ValidateCode smsCode = imageCodeGenerator.generate(new ServletWebRequest(request));
        //操作添加到session中
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,smsCode);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request,"mobile");
        smsCodeSender.send(mobile,smsCode.getCode());

    }



}
