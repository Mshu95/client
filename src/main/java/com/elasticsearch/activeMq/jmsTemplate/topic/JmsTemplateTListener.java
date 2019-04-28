package com.elasticsearch.activeMq.jmsTemplate.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Author:zhuiqiu95@foxmial.com
 * @params:elastichSearchDemo
 * @description:
 * @date: 2019/4/26 16:26
 */
@Component
public class JmsTemplateTListener implements MessageListener {

    Logger logger =  LoggerFactory.getLogger(JmsTemplateTListener.class);

    @Override
    public void onMessage(Message message) {
        final TextMessage tm = (TextMessage) message;
        try {
            logger.info("TOPIC接收信息==="+tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
