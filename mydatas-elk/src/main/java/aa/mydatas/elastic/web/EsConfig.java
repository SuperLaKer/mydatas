package aa.mydatas.elastic.web;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class EsConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean("highLevelClient")
    public RestHighLevelClient elasticsearchClient() {

        // final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
        //         .connectedTo("localhost:9500")
        //         .build();
        // return RestClients.create(clientConfiguration).rest();

        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9500, "http")));
    }
}