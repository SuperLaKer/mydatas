package seata.at.producer.webs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seata.at.producer.webs.mappers.ProducerMapper;
import seata.at.producer.webs.service.ProducerService;

import java.math.BigDecimal;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    ProducerMapper producerMapper;

    @Override
    @Transactional
    public String doService(BigDecimal arg) {
        int v = producerMapper.updateMoney(1437933970770661377L, arg);
        return v != 0 ? "doService_success" : "doService_error";
    }
}
