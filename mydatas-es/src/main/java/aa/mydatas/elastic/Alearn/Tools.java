package aa.mydatas.elastic.Alearn;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import aa.mydatas.elastic.web.model.BookModel;

import java.io.IOException;
import java.util.Map;

public class Tools {
    public static void getResult(RestHighLevelClient highLevelClient, SearchRequest searchRequest) throws IOException {
        // 查询、封装结果集
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        System.out.println(hits.getTotalHits().value);
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            BookModel bookModel = JSON.parseObject(searchHit.getSourceAsString(), BookModel.class);
            System.out.println(bookModel);
        }
        System.out.println(searchResponse);
    }

    public static void tearDown(RestHighLevelClient highLevelClient) throws Exception {
        highLevelClient.close();
    }
}
