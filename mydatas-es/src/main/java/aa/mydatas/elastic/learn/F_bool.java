package aa.mydatas.elastic.learn;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class F_bool {
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

    public static void main(String[] args) throws IOException {

        try {
            boolQuery();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            highLevelClient.close();
        }
    }

    private static void boolQuery() throws IOException {
        MatchQueryBuilder nameMatchQueryBuilder = QueryBuilders.matchQuery("name", "冰与火之歌");
        MatchQueryBuilder sellReasonMatchQueryBuilder = QueryBuilders.matchQuery("sellReason", "冰与火之歌");

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(nameMatchQueryBuilder);  // name必须包含 "冰与火之歌"

        boolQueryBuilder.must(sellReasonMatchQueryBuilder);  // sellReason必须包含 "冰与火之歌"
        // boolQueryBuilder.should(sellReasonMatchQueryBuilder);  // sellReason可能包含 "冰与火之歌"


        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        Tools.getResult(highLevelClient, searchRequest);
    }
}
