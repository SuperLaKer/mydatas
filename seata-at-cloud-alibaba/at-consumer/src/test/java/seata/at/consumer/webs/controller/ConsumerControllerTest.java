package seata.at.consumer.webs.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import seata.at.consumer.ATConsumerApp;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ATConsumerApp.class)
public class ConsumerControllerTest {

    @Autowired
    ConsumerController consumerController;

    @org.junit.Test
    public void doDscMoney() {
        String s = consumerController.doDscMoney(new BigDecimal(50));
        System.out.println(s);
    }
}