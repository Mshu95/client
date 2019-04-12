package com.elasticsearch.fun;

import com.elasticsearch.client.ELClient;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;

import java.util.Map;

/**
 * @Author:zhuiqiu95@foxmial.com
 * @params:client
 * @description:
 * @date: 2019/2/28 15:44
 */
public class GetApi {
    static TransportClient client =  ELClient.createClient();

    /**
     * 根据index,type,id:查文档
     */
    public static void getById(){
        GetResponse response =  client.prepareGet("biology","people","wzv0MmkBYc3Cart2HMLk")
                                      .get();
        Map<String,Object> result = response.getSource();
        System.out.println(result.get("name"));
    }

    public static void main(String[] args) {
        getById();
    }
}
