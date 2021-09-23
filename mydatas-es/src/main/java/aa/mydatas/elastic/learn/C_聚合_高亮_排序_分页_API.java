package aa.mydatas.elastic.learn;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import aa.mydatas.elastic.web.model.BookModel;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * SearchRequest: 将要执行搜索请求
 * SearchSourceBuilder: 相当于searchRequest的请求体, 完善搜索条件如对结果排序、结果高亮等
 * QueryBuilder: 构造查询条件(MatchQueryBuilder)
 * QueryBuilders: QueryBuilder的构造工具类，可以直接new QueryBuilder的实现类
 */
public class C_聚合_高亮_排序_分页_API {
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
            MatchQueryBuilder termQueryBuilder = QueryBuilders.matchQuery("author", "金庸");
            searchSourceBuilder.query(termQueryBuilder);
            fieldsHighLight("author");
            searchRequest.source(searchSourceBuilder);
            Tools.getResult(highLevelClient, searchRequest);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tearDown();
        }

    }

    private static void termAggregation() throws IOException {
        // 分组terms("别名").field("字段类型_keyword")
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("alias_by_author").field("author");
        AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg("alias_average_price").field("price");
        termsAggregationBuilder.subAggregation(avgAggregationBuilder);

        MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders.max("max_price").field("price");

        searchSourceBuilder.aggregation(termsAggregationBuilder);
        searchRequest.source(searchSourceBuilder);
        getResult(searchRequest);
    }


    private static void fieldsHighLight(String... fields) {
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<tag>").postTags("</tag>");
        for (String field : fields) {  // 支持多个字段高亮
            HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field(field);
            highlightBuilder.fields().add(highlightTitle);
        }
        searchSourceBuilder.highlighter(highlightBuilder);
    }

    /**
     * 返回指定的内容
     */
    private static void sourceFiltering() {
        String[] includeFields = new String[]{"title", "user", "innerObject.*"};
        String[] excludeFields = new String[]{"_type"};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);
    }


    /**
     * termQueryBuilder FieldSortBuilder DESC
     * 降序
     */
    private static void order() throws IOException {

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("author", "金");
        searchSourceBuilder.query(termQueryBuilder);
        // 结果降序
        searchSourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.DESC));
        searchRequest.source(searchSourceBuilder);
        highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }


    /**
     * TermQueryBuilder
     */
    private static void termQuery() throws IOException {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "史诗");
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);
        getResult(searchRequest);
    }

    /**
     * matchAllQueryBuilder
     */
    private static void matchAllQuery() throws IOException {
        // 封装查询条件
        QueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        // 分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(2);

        // 简单分页
        searchSourceBuilder.sort("name", SortOrder.ASC);

        searchSourceBuilder.query(matchAllQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        getResult(searchRequest);
    }


    private static void getResult(SearchRequest searchRequest) throws IOException {
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

    public static void tearDown() throws Exception {
        highLevelClient.close();
    }
}
