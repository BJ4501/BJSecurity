### OAuth协议中的授权模式
- 授权码模式 authorization code
- 简化模式 implicit
- 密码模式 resource owner password credentials
- 客户端模式 client credentials

### SpringSocial
- 位于过滤器链上SocialAuthenticationFilter
    - 根据用户信息构建Authentication并放入SecurityContext
- 功能模块
    1. ServiceProvider(AbstractOAuth2ServiceProvider)
        - 每个需要对接的第三方登录(QQ,微信)，都需要实现这个抽象类
        - OAuth2Operations(OAuth2Template)
            - OAuth2协议的相关操作
        - Api(AbstractOAuth2ApiBinding)
            - 调用获取用户信息(每个服务商都不同)
    2. Connection(OAuth2Connection)
        - 封装上一步获取到的用户信息
    3. ConnectionFactory(OAuth2ConnectionFactory)
        - (负责创建Connection实例)
        - ServiceProvider 见1
        - ApiAdapter 见1Api
    4. UserConnectionRepository(JdbcUserConnectionRepository)
        - DB -> UserConnection表
     