package aa.mydatas.elastic.demos.demo;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

@SuppressWarnings("ALL")
public class AC查询 {
    RestHighLevelClient client;

    @Test
    public void findById() throws IOException {
        // 那个索引, type默认为_all
        GetRequest getRequest = new GetRequest("books");
        getRequest.id("CqRqVXkBTNQudRzHkeQP");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse);
    }


    @Before
    public void setUp() {
        client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9500, "http")));
    }

    @After
    public void tearDown() throws Exception {
        client.close();
    }
}
