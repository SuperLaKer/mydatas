package aa.mydatas.elastic.demos.demo;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("all")
public class AA创建索引和映射 {

    RestHighLevelClient client;

    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("books");
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0)
        );
        Map<String, Object> book_id = new HashMap<>();
        book_id.put("type", "integer");  // 类型

        Map<String, Object> name = new HashMap<>();
        name.put("type", "text");
        name.put("analyzer", "ik_smart");
        name.put("search_analyzer", "ik_smart");

        Map<String, Object> author = new HashMap<>();
        author.put("type", "keyword");

        Map<String, Object> category = new HashMap<>();
        category.put("type", "text");
        category.put("analyzer", "ik_max_word");
        category.put("search_analyzer", "ik_max_word");

        Map<String, Object> status = new HashMap<>();
        status.put("type", "short");

        Map<String, Object> sellReason = new HashMap<>();
        sellReason.put("type", "text");
        sellReason.put("analyzer", "ik_smart");
        sellReason.put("search_analyzer", "ik_smart");

        Map<String, Object> sellTime = new HashMap<>();
        sellTime.put("type", "date");
        sellTime.put("format", "yyyy-MM-dd");

        Map<String, Object> price = new HashMap<>();
        price.put("type", "double");


        Map<String, Object> properties = new HashMap<>();
        properties.put("book_id", book_id);
        properties.put("name", name);
        properties.put("author", author);
        properties.put("category", category);
        properties.put("price", price);
        properties.put("status", status);
        properties.put("sellReason", sellReason);
        properties.put("sellTime", sellTime);

        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        request.mapping(mapping);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response);
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
