package com.elasticsearch.activeMq.jmsTemplate.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @Author:zhuiqiu95@foxmial.com
 * @params:elastichSearchDemo
 * @description:
 * @date: 2019/4/24 17:16
 */
@Component
public class JmsSenderT {

    Logger logger = LoggerFactory.getLogger(JmsSenderT.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Qualifier("JmsSenderTDestination")
    @Autowired
    protected Destination destination;

    /**
     * 向指定队列发送消息
     * @param
     * @param msg
     */
    public void sendMessage(final String msg) {

        logger.info("TOPIC destination :" + destination.toString() + ", 发送消息：" + msg);

        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(final Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

}
