### Session管理
- Session超时处理
    - 设置Session超时时间
    ```
    #Session失效时间 单位：秒 最低1分钟
    server.session.timeout=10
    ```
    - 相关类：TomcatEmbeddedServletContainerFactory
    - Config配置
    ```
    .sessionManagement()
    //当session失效时跳转的地址
    .invalidSessionUrl("/session/invalid")
    ```
    - Controller处理失效效果
    ```
    //当Session失效
    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED) //返回状态码401未授权
    public SimpleResponse sessionInvalid(){
        String message = "session失效";
        return new SimpleResponse(message);
    }
    ```
- Session并发控制
    - Config配置
    ```
    .sessionManagement()
    .invalidSessionUrl("/session/invalid")//当session失效时跳转的地址
    .maximumSessions(1)//最大session数量
    .maxSessionsPreventsLogin(true) //当session数量达到最大数量时，阻止后序登录行为
    .expiredSessionStrategy(new BjsExpiredSessionStrategy()) //记录session
    .and()
    ```
- 集群Session管理