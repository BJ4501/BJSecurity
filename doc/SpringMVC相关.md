### SpringMVC文件的上传与下载
- TODO

### 异步处理Rest服务
- 使用Runnable异步处理Rest服务
    - 流程：主线程调用副线程 -> 副线程执行之后返回结果
        - 主线程开始
        - 主线程结束
        - 副线程开始
        - 副线程结束 
```
    Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                loggger.info("副线程开始!");
                //耗时操作
                Thread.sleep(1000);
                loggger.info("副线程返回!");
                return "success";
            }
    };
```
- 使用DeferredResult异步处理Rest服务 
    - 与Runnable不同：Runnable处理复杂情况实现困难。
    ```
    场景:
    1. Http请求 -> 应用1(线程1) -> 发消息 -> 消息队列
    2. 应用2 -> 监听并处理消息 -> 发送处理结果 -> 消息队列
    3. 应用1(线程2) -> 监听处理结果 -> 发送Http响应
    ```
    
    
- 异步处理配置
```
    //用于配置异步支持
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //如果是异步请求，需要单独注册拦截器
        configurer.registerCallableInterceptors();
        configurer.registerDeferredResultInterceptors();
        //异步请求默认超时时间
        configurer.setDefaultTimeout(1);
        //设置自己可重用的线程池，替代默认线程池(Spring默认提供，不重用)
        configurer.setTaskExecutor(null);
    }
```