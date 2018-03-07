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
    