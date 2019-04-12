package com.elasticsearch.fun;

import com.elasticsearch.client.ELClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @Author:zhuiqiu95@foxmial.com
 * @params:client
 * @description:
 * @date: 2019/2/28 14:29
 */
public class IndexApi {
     static  TransportClient client =  ELClient.createClient();

    /**
     * field属性插入数据
     */
    public static void addDocByField(){

        try {
            client.prepareIndex("people", "sex", "1")
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("name", "mashu")
                            .field("birthday", new Date())
                            .field("age", 24)
                            .endObject()
                    )
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Json 格式插入文档
     */
    public  static void  addDocByJson(){
        String json =
                 "{" +
                "\"name\":\"小明\"," +
                "\"birthday\":\"1996-01-30\"," +
                "\"age\":\"23\"" +
                "}";
        client.prepareIndex("biology", "people")
                .setSource(json, XContentType.JSON)
                .get();
    }

    /**
     * Map 格式插入数据
     */
    public  static void  addDocByMap(){

        Map<String, Object> json = new HashMap<>();
        json.put("name","马云");
        json.put("birthday",new Date().getTime());
        json.put("age",0);
        json.put("id",1);
        client.prepareIndex("biology", "people")
                .setSource(json, XContentType.JSON)
                .get();
    }

    public static void main(String[] args) {
        addDocByMap();
    }
}
