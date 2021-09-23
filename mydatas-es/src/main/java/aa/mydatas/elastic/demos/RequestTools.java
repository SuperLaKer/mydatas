package aa.mydatas.elastic.demos;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class RequestTools {

    public static String theIndex = "posts";
    public static RestHighLevelClient highLevelClient;
    public static GetRequest getRequest;
    public static SearchRequest searchRequest = new SearchRequest(theIndex);
    public static SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

    static {
        highLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9500, "http")));
        getRequest = new GetRequest(theIndex);

    }


    private static void getResult(SearchRequest searchRequest) throws IOException {
        // 查询、封装结果集
        SearchResponse searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        System.out.println(hits.getTotalHits().value);
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.getSourceAsString());
        }
        System.out.println(searchResponse);
    }

    public static void tearDown() throws Exception {
        highLevelClient.close();
    }
}
