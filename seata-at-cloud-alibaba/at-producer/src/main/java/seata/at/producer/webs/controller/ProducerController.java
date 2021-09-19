package seata.at.producer.webs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seata.at.producer.webs.service.ProducerService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    ProducerService producerService;

    @GetMapping("/do/{bigDecimal}")
    public String doDscMoney(@PathVariable BigDecimal bigDecimal) {
        return "producer_" + producerService.doService(bigDecimal);
    }
}
