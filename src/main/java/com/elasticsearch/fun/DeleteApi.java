package com.elasticsearch.fun;

import com.elasticsearch.client.ELClient;
import org.elasticsearch.client.transport.TransportClient;

/**
 * @Author:zhuiqiu95@foxmial.com
 * @params:client
 * @description:
 * @date: 2019/2/28 16:12
 */
public class DeleteApi {

    static TransportClient client =  ELClient.createClient();


    /**
     * 根据index,type,id 删除文档
     */
    public static void delById(){
        client.prepareDelete("biology","people","yTsrM2kBYc3Cart26MKs")
                .get();
    }

    public static void main(String[] args) {
        delById();
    }
}
