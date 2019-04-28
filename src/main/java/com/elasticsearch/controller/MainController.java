package com.elasticsearch.controller;

import com.elasticsearch.activeMq.jmsTemplate.queue.JmsSender;
import com.elasticsearch.activeMq.jmsTemplate.topic.JmsSenderT;
import com.elasticsearch.client.ELClient;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author:zhuiqiu95@foxmial.com
 * @params:elastichSearchDemo
 * @description:
 * @date: 2019/4/26 11:01
 */
@RestController
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ELClient elClient;

    @Autowired
    private JmsSender jmsSender;
    @Autowired
    private JmsSenderT jmsSenderT;

    @RequestMapping("main")
    private String main(){
        TransportClient client  = elClient.createClients();
        GetResponse response =  client.prepareGet("biology","people","wzv0MmkBYc3Cart2HMLk")
                .get();
        Map<String,Object> result = response.getSource();
        logger.info(result.get("name").toString());
        return "";
    }

    @RequestMapping("jms/{msg}")
    public String jmsTemple(@PathVariable String msg){
        jmsSender.sendMessage(msg);
        return "发送完毕"+msg;
    }
    @RequestMapping("jmsT/{msg}")
    public String jmsTempleT(@PathVariable String msg){
        jmsSenderT.sendMessage(msg);
        return "发送完毕"+msg;
    }
}
