package com.bj.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 队列监听器
 * Created by neko on 2018/3/5.
 */
@Component //ApplicationListener<ContextRefreshedEvent> 该事件是Spring初始化完毕的事件
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{

    private Logger loggger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        new Thread(() -> {
            while (true){
                //当mockQueue有值情况
                if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())){

                    String orderNumber = mockQueue.getCompleteOrder();
                    loggger.info("返回订单处理结果："+orderNumber);
                    deferredResultHolder.getMap().get(orderNumber).setResult("place order success");

                    mockQueue.setCompleteOrder(null);
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }




}
