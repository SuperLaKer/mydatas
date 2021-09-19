package seata.at.consumer.webs.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seata.at.consumer.webs.api.producerApp.ProducerService;
import seata.at.consumer.webs.mappers.ConsumerMapper;
import seata.at.consumer.webs.service.ConsumerService;

import java.math.BigDecimal;

@Service
public class ConsumerServiceImpl implements ConsumerService {


    @Autowired
    ConsumerMapper consumerMapper;

    @Autowired
    ProducerService producerService;

    @Override
    @GlobalTransactional
    public String doService(BigDecimal arg) {
        String s = producerService.doDscMoney(arg);
        System.out.println(s);
        System.out.println(1/0);
        int v = consumerMapper.updateMoney(1437946122223054849L, arg);
        return v != 0 ? "doService_success" : "doService_error";
    }
}
