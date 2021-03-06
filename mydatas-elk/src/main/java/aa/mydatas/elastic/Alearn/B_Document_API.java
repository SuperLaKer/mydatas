package aa.mydatas.elastic.Alearn;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.Map;

import static java.util.Collections.singletonMap;

public class B_Document_API {
    static RestHighLevelClient highLevelClient;
    static GetRequest getRequest;

    static {
        highLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9500, "http")));

        getRequest = new GetRequest("books");
    }

    public static void main(String[] args) throws Exception {

        try {
            // getAPI();
            // existsAPI();
            updateAPI();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tearDown();
        }
    }

    private static void updateAPI() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("books", "vfwOWnkByBoX-wWJgt1P");
        Map<String, Object> parameters = singletonMap("status", 0);
        updateRequest.doc(JSON.toJSONString(parameters), XContentType.JSON);
        UpdateResponse response = highLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    private static void existsAPI() throws IOException {
        getRequest.id("9");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = highLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        highLevelClient.existsAsync(getRequest, RequestOptions.DEFAULT, new ActionListener<Boolean>() {
            @Override
            public void onResponse(Boolean aBoolean) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private static void getAPI() throws IOException {
        // ???static{}????????????getRequest???????????????
        // GetRequest getRequest = new GetRequest();
        // getRequest.index("books");
        // ??????_id??????id???????????????
        getRequest.id("9");
        // ??????????????????
        GetResponse documentFields = highLevelClient.get(getRequest, RequestOptions.DEFAULT);
        // ??????
        highLevelClient.getAsync(getRequest, RequestOptions.DEFAULT, new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse documentFields) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        System.out.println(documentFields.getSource());
    }

    public static void tearDown() throws Exception {
        highLevelClient.close();
    }

}
