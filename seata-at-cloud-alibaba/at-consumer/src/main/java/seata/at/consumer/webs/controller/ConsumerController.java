package seata.at.consumer.webs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seata.at.consumer.webs.service.ConsumerService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    @GetMapping("/do/{bigDecimal}")
    public String doDscMoney(@PathVariable BigDecimal bigDecimal) {
        return "consumer_" + consumerService.doService(bigDecimal);
    }
}
