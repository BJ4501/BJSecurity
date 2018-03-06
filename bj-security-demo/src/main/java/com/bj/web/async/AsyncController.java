package com.bj.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * 异步处理样例
 * Created by neko on 2018/3/5.
 */
@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder def;

    private Logger loggger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        loggger.info("Main Thread Start!");
        //1.模拟逻辑耗时操作
        //Thread.sleep(1000);

        //2.单开一个线程
        /*Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                loggger.info("副线程开始!");
                //模拟逻辑耗时操作
                Thread.sleep(1000);
                loggger.info("副线程返回!");
                return "success";
            }
        };*/

        //3.使用DeferredResult
        String orderNumber = RandomStringUtils.randomNumeric(8); //生成8位随机单号
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        def.getMap().put(orderNumber,result);


        loggger.info("Main Thread Return!");
        return result;
    }

}
