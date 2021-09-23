package aa.mydatas.elastic.learn;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class D_match_query {
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
            matchQueryOr();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tearDown();
        }

    }

    /**
     * MatchQueryBuilder 前缀模糊
     */
    private static void matchQuery() throws IOException {
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("category", "史诗奇幻");
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);  // 启用模糊匹配
        matchQueryBuilder.prefixLength(3);  // 左前缀精准匹配
        matchQueryBuilder.maxExpansions(10);  // 后面的模糊匹配

        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        Tools.getResult(highLevelClient, searchRequest);
    }

    /**
     * MatchQueryBuilder_OR_百分比
     */
    private static void matchQueryOr() throws IOException {
        // 会对text分词
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("category", "中国");
        // or and
        // matchQueryBuilder.operator(Operator.OR);

        // 分词百分比
        matchQueryBuilder.minimumShouldMatch("100%");

        searchSourceBuilder.query(matchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        Tools.getResult(highLevelClient, searchRequest);
    }

    public static void tearDown() throws Exception {
        highLevelClient.close();
    }
}
