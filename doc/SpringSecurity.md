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
        