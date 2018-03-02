package com.bj.web.controller;

import com.bj.dto.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BJ on 2018/3/2.
 */
@RestController
@RequestMapping("/user")
public class UserController {

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

    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping
    @JsonView(User.UserSimpleView.class)
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
    public User getInfo(@PathVariable String id) {
        User user = new User();
        user.setUsername("tom");
        return user;
    }




}
