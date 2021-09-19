package seata.at.consumer.webs.api.producerApp;

import java.math.BigDecimal;

public class ProducerServiceFallback implements ProducerService {
    @Override
    public String doDscMoney(BigDecimal bigDecimal) {
        return "fallback: " + bigDecimal;
    }
}
