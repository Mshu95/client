package com.elasticsearch.activeMq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Author:zhuiqiu95@foxmial.com
 * @params:elastichSearchDemo
 * @description:
 * @date: 2019/4/24 16:49
 */
public class MyMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("接收订阅主题：" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
