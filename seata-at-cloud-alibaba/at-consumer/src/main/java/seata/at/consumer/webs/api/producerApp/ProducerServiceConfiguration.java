package seata.at.consumer.webs.api.producerApp;

import org.springframework.context.annotation.Bean;

public class ProducerServiceConfiguration {
    @Bean
    public ProducerServiceFallback producerServiceFallback(){
        return new ProducerServiceFallback();
    }
}
