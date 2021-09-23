package aa.mydatas.elastic.demos.demo;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;

@SuppressWarnings("all")
public class AB添加文档 {

    RestHighLevelClient client;

    @Test
    public void addIndex() throws Exception {
        Class<JsonDate> jsonDateClass = JsonDate.class;
        for (int i = 1; i < 11; i++) {
            Method method = jsonDateClass.getMethod("getJsonMap" + i);
            Map<String, Object> jsonMap = (Map<String, Object>) method.invoke(jsonDateClass.newInstance());
            addBranch(jsonMap);
        }
    }

    /**
     * 书籍相关属性和content建立了mapping
     */
    public void addBranch(Map<String, Object> jsonMap) throws Exception {
        IndexRequest request = new IndexRequest("books");
        // 自动生成id
        request.id();
        // 插入一条document
        request.source(jsonMap);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
    }

    // @Test
    public void addOne() throws Exception {
        JsonDate jsonDate = new JsonDate();
        addBranch(jsonDate.jsonMap10);
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
