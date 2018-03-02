package com.bj.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by BJ on 2018/3/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before//会在每个测试用例执行之前去执行
    public void setup(){
        //构建伪造的MVC环境
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //-----测试用例-----
    @Test
    public void whenQuerySuccess() throws Exception {
        //mockMvc发送模拟请求，判断返回内容是否符合期望
        //例：构建Get请求
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username","neko")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                //对上面请求结果的期望
                .andExpect(MockMvcResultMatchers.status().isOk())
                //解析返回的Json的内容(本次判断是需要返回的是一个集合，长度为3)
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));

        //JsonPath: github.com/json-path/JsonPath
        //
        //

    }

}
