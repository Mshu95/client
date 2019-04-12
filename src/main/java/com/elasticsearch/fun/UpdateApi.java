package com.elasticsearch.fun;

import com.elasticsearch.client.ELClient;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @Author:zhuiqiu95@foxmial.com
 * @params:client
 * @description:
 * @date: 2019/2/28 17:02
 */
public class UpdateApi {
    static TransportClient client =  ELClient.createClient();

    /**
     * 根据index,type,id 更改数据
     */
    public static  void update(){
        try {

            XContentBuilder xContentBuilder = jsonBuilder().startObject()
                    .field("name","王健林")
                    .field("age",55)
                    .endObject();

            client.prepareUpdate("biology", "people", "xzscM2kBYc3Cart2R8LV")
                    .setDoc(xContentBuilder)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据index,type,id 更改数据
     */
    public static  void  updateBy2(){

        try {
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.index("biology");
            updateRequest.type("_doc");
            updateRequest.id("1");
            updateRequest.doc(jsonBuilder()
                    .startObject()
                    .field("gender", "male")
                    .endObject());
            client.update(updateRequest).get();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        update();
    }

}
