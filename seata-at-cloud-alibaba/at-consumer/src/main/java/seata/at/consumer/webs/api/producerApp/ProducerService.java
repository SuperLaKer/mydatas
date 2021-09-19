package seata.at.consumer.webs.api.producerApp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/producer")
@FeignClient(name = "xaProducerApp",
        configuration = ProducerServiceConfiguration.class,
        fallback = ProducerServiceFallback.class)
public interface ProducerService {
    @GetMapping("/do/{bigDecimal}")
    String doDscMoney(@PathVariable BigDecimal bigDecimal);
}
