package aa.mydatas.elastic.demos.A创建;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
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
public class AC结果处理 {

    RestHighLevelClient client;

    @Test
    public void sendData() throws IOException {
        IndexRequest indexRequest = new IndexRequest("posts");
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date(System.currentTimeMillis()));
        jsonMap.put("message", "trying out Elasticsearch");
        indexRequest.source(jsonMap);

        // 多次创建: 覆盖，版本号加一
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        // 结果处理
        String index = indexResponse.getIndex();
        String id = indexResponse.getId();

        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {

        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {

        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {

        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
            }
        }


    }


    @Before
    public void setUp() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }

    @After
    public void tearDown() throws Exception {
        client.close();
    }
}
