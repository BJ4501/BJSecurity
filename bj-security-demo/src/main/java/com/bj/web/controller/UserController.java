package com.bj.web.controller;

import com.bj.dto.User;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BJ on 2018/3/2.
 */
@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    //获取当前用户方法1
    /*@GetMapping("/me")
    public Object getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }*/

    //获取当前用户方法2,返回特定信息
    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

    //注册功能
    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {
        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
        String userId = user.getUsername();
        //将用户信息与Id绑定，插入Social数据库
        //再次使用第三方登录时，就可知道是哪个用户在登录
        providerSignInUtils.doPostSignUp(userId,new ServletWebRequest(request));
    }

    @PostMapping
    //@JsonView(User.UserDetailView.class)
    public User create(@Valid @RequestBody User user, BindingResult errors){
        //如果存在错误，捕获错误
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors){
        //如果存在错误，捕获错误
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String msg = fieldError.getField()+ " : " + error.getDefaultMessage();
                System.out.println(msg);
            });
        }

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        user.setId("1");

        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println(id);
    }

    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    //public List<User> query(UserQueryCondition uqc){
    public List<User> query(@RequestParam String username,@PageableDefault Pageable pageable){
        //补充：通过反射对象打印toString
        //System.out.println(ReflectionToStringBuilder.toString(uqc, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    //正则：{id:\\d+} 只能接收一个数字
    //@RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam("用户id") @PathVariable String id) {
        //throw new UserNotExistException(id);
        System.out.println("getInfo IN");
        User user = new User();
        user.setUsername("tom");
        return user;
    }




}
