package com.elasticsearch.client;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @Author:zhuiqiu95@foxmial.com
 * @params:client
 * @description:
 * @date: 2019/2/21 17:11
 */
public class ELClient {

    public static TransportClient createClient(){
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();

        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static void test() {
        TransportClient client = createClient();
        RangeQueryBuilder rqb = new RangeQueryBuilder("year");
        rqb.gte(0).lte(21929);

        final BoolQueryBuilder query = QueryBuilders.boolQuery().must(rqb);
        System.out.println(query);
        SearchRequestBuilder search = client.prepareSearch("library")
                .setQuery(query);

        //搜索返回搜索结果
        SearchResponse response = search.get();
        //命中的文档
        SearchHits hits = response.getHits();
        //命中总数
        Long total = hits.getTotalHits();
        System.out.println(total);
        //循环查看命中值
        for(SearchHit hit:hits.getHits()){
            //文档元数据
            String index = hit.getIndex();
            //文档的_source的值
            Map<String, Object> sourceMap = hit.getSourceAsMap();
            System.out.println(sourceMap);
        }
    }

    public static void test1() {
        TransportClient client = createClient();
        RangeQueryBuilder rqb = new RangeQueryBuilder("year");
        rqb.gte(0).lte(21929);

        final BoolQueryBuilder query = QueryBuilders.boolQuery().must(rqb);
        System.out.println(query);
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client).filter(query).execute();
    }

    public static void test2() {
        TransportClient client = createClient();
        GetResponse response = client.prepareGet("library", "book", "1").execute().actionGet();
        System.out.println(response.getSourceAsString());

    }
    public static void test3() {
        TransportClient client = createClient();
        RangeQueryBuilder rqb = new RangeQueryBuilder("year");
        rqb.gte(0).lte(21929);

        final BoolQueryBuilder query = QueryBuilders.boolQuery().must(rqb);
        BulkByScrollResponse response =
                DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                        .filter(query)
                        .source("library")
                        .get();
        long deleted = response.getDeleted();
        System.out.println(deleted);
    }



    public static void main(String[] args) {

        test();
    }

}
