package com.bj.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by neko on 2018/3/7.
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名："+username);
        //根据用户名查找用户信息
        //return new User(username,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        //根据查找到的用户信息，判断用户是否被冻结
        return new User(username,passwordEncoder.encode("123456"),
                true, //账号是否可用
                true, //账号是否过期
                true, //账号密码是否过期
                true, //账号是否被锁定
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

}
