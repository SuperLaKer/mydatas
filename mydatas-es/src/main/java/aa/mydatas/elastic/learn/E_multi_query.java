package aa.mydatas.elastic.learn;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * 同时搜索多个域
 */
public class E_multi_query {
    static RestHighLevelClient highLevelClient;
    static GetRequest getRequest;
    static SearchRequest searchRequest = new SearchRequest("books");
    static SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

    static {
        highLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9500, "http")));
        getRequest = new GetRequest("books");
    }

    public static void main(String[] args) throws Exception {

        try {

            MultiMatchQueryBuilder multiMatchQueryBuilder =
                    QueryBuilders.multiMatchQuery("冰与火之歌", "name", "sellReason");
            multiMatchQueryBuilder.minimumShouldMatch("1%");
            multiMatchQueryBuilder.field("sellReason", 10);
            searchSourceBuilder.query(multiMatchQueryBuilder);
            searchRequest.source(searchSourceBuilder);
            Tools.getResult(highLevelClient, searchRequest);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            highLevelClient.close();
        }

    }
}
