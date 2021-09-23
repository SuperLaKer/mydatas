package aa.mydatas.elastic.demos.A创建;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 高级客户端内部会创建低级客户端用于基于提供的builder执行请求。
 * 低级客户端维护一个连接池，并启动一些线程，
 * 因此当你用完以后应该关闭高级客户端，并且在内部它将会关闭低级客户端，以释放这些资源
 */
@SuppressWarnings("all")
public class AA创建Index {

    RestHighLevelClient highLevelClient;

    /**
     * json和map
     */
    @Test
    public void jsonCreateIndex() throws IOException {

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", 1);
        jsonMap.put("postDate", new Date(System.currentTimeMillis()));
        jsonMap.put("message", "trying out Elasticsearch");

        // 添加数据
        IndexRequest indexRequest = new IndexRequest("books");
        indexRequest.id();
        indexRequest.source(jsonMap);
        // 执行
        IndexResponse response = highLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response);
    }


    @Before
    public void setUp() {
        highLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }

    @After
    public void tearDown() throws Exception {
        highLevelClient.close();
    }
}
