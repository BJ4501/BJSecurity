### SpringSecurity核心功能
- 认证
- 授权
- 攻击防护(防止伪造身份)

#### SpringSecurity过滤器链
- Username Password Authentication Filter 处理表单登录
    - 检查是否为登录请求，请求是否还有用户名密码
    - 如果没有，将请求放过，给下一个过滤器
- Basic Authentication Filter 处理HttpBasic登录
- Exception Translation Filter 捕获下面FSI抛出的异常
    - 根据抛出的异常，进行相应的处理
- Filter Security Interceptor (SS过滤器最后一环)
    - 决定当前请求能否访问后面真正的服务
- REST API (真正的服务)
    
#### 自定义用户认证逻辑
- 处理用户信息获取逻辑
    - 继承接口 UserDetailsService
- 处理用户校验逻辑
    - 继承接口 UserDetails
- 处理密码加密解密
    - 继承接口 PasswordEncoder (crypto包)
        - encode 将密码加密
        - matches 加密的密码，与收到的密码是否匹配
        - 补充：默认的加密方式BCryptPasswordEncoder，每次加密的结果都不相同

#### SpringSecurity开发基于表单的认证
- 个性化用户认证流程
    - 自定义登录页面
        ```
        BrowserSecurityConfig
        .loginPage("/bj-signIn.html") //自定义登录页面
        .antMatchers("/bj-signIn.html").permitAll() //匹配器，记录额外不需要认证的页面
        .loginProcessingUrl("/authentication/form") //将表单登录拦截器的默认地址修改
        ```
    - 自定义登录成功处理
        - AuthenticationSuccessHandler
    - 自定义登录失败处理
        - AuthenticationFailureHandler
    
- 处理不同类型的请求
    - 接到html请求或数据请求
    - 是否需要身份认证(SpringSecurity判断)
    - 跳转到一个自定义的Controller方法上
        - 方法内判断：是否是html请求引发的跳转
            - 是：返回登录页面
            - 否：返回401状态码和错误信息
            
```
Authentication 信息 JSON
{
    authorities: [
        {
        authority: "admin"
        }
    ],
    details: {
        remoteAddress: "0:0:0:0:0:0:0:1",
        sessionId: null
    },
    authenticated: true,
    principal: {
        password: null,
        username: "qqq",
        authorities: [
            {
            authority: "admin"
            }
        ],
        accountNonExpired: true,
        accountNonLocked: true,
        credentialsNonExpired: true,
        enabled: true
    },
    credentials: null,
    name: "qqq"
}
```
- 认证流程的源码详解
    - 认证处理流程说明
        - 认证流程:
        - UsernamePasswordAuthenticationFilter
            - Authentication(未认证)
        - AuthenticationManager 用于管理下面的Provider
        - AuthenticationProvider 校验逻辑(不同的登录方式，校验的逻辑不同)
            - 包含多个校验逻辑：用户密码校验，第三方校验等
            - 校验四种情况，账户是否过期，是否超时，是否被禁用等
        - UserDetailsService
        - UserDetails
        - Authentication(已认证)
    - 认证结果如何在多个请求之间共享
        - 流程接续认证：
        - Authentication(已认证)
            > 将认证成功后的Authentication，放入一个SecurityContext中，再放入SCHolder中。
        - SecurityContext
        - SecurityContextHolder
            > ThreadLocal的一个封装，注：ThreadLocal可以理解为线程级的全局变量。
        - SecurityContextPersistenceFilter
            > 位于SS过滤器的最前端。作用：当请求进来时，
            检查session是否有SecurityContext，如果有就放入线程里，
            没有就跳过。当请求完成相应返回时，如果线程中有SecurityContext，
            就取出放入session中。
    - 获取认证用户信息
