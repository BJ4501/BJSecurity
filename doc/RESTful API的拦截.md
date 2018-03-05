### RESTful API的拦截
- 过滤器(Filter)
    - 继承接口Filter即可
    ```
    @Component
    public class TimeFilter implements Filter{
    }
    ```
- 拦截器(Interceptor)
    - 局限性：无法获得对象、参数
    - 继承HandlerInterceptor接口来使用
    ```
    @Component
    public class TimeInterceptor implements HandlerInterceptor {
    }
    ```
    - 需要注册拦截器才可使用
    ```
    @Configuration
    public class WebConfig extends WebMvcConfigurerAdapter{
    
        @Autowired
        private TimeInterceptor timeInterceptor;

        //拦截器注册器
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            super.addInterceptors(registry);
            registry.addInterceptor(timeInterceptor);
        }
    }
    ```
    
- 切片(Aspect)
    - 需要引入AOP包
    - @Aspect 开启AOP
    - @Before 在方法之前
    - @After 在方法之后
    - @AfterThrowing 在方法抛出某些异常的时候会被调用
    - @Around 包围：@Before，@After，@AfterThrowing都可以在这个方法里实现
        - 表达式
        ```
        execution(* com.bj.web.controller.UserController.*(..))
        执行    *：任何返回值          .*(..) : UserController里任何一个方法，方法包含任何一个参数
        ```
    - 切面语法详见：
    https://docs.spring.io/spring/docs/4.3.15.BUILD-SNAPSHOT/spring-framework-reference/htmlsingle/#aop-pointcuts
        - 11.2.3 定义切点
    
- 补充：拦截顺序
    1. Filter 过滤器：
        - 可以拿到原始Http请求与响应的信息，但是拿不到真正处理请求的方法的信息
    2. Interceptor 拦截器：
        - 可以拿到原始Http请求与响应的信息，也可以拿到
          真正处理请求的方法的信息，但是拿不到方法被调用时参数的值
    3. ControllerAdvice(拦截异常，在出现异常时才会启用) 
    4. Aspect 切片
        - 可以拿到方法被真正调用时，传进来参数的值，但是拿不到原始Http请求与响应的信息
    - 三种拦截机制各有特点，需要根据实际业务需要来选择。
    - 当异常出现时，是通过4-3-2-1顺序去捕获，最先拿到异常的是Aspect