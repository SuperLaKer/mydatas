package mydatas.jta;

import mydatas.jta.webs.mappers.ConsumerMapper;
import mydatas.jta.webs.mappers.ProducerMapper;
import mydatas.jta.webs.tables.Consumer;
import mydatas.jta.webs.tables.Producer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * (exclude = {DataSourceAutoConfiguration.class,
 * DataSourceTransactionManagerAutoConfiguration.class,
 * MybatisPlusAutoConfiguration.class})
 */
@SpringBootApplication
public class JTAMainApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(JTAMainApp.class, args);
        System.out.println(ac.getBeanDefinitionNames()[0]);
        ConsumerMapper consumerMapper = ac.getBean(ConsumerMapper.class);
        ProducerMapper producerMapper = ac.getBean(ProducerMapper.class);
        Consumer consumer = consumerMapper.selectById(1437946122223054849L);
        Producer producer = producerMapper.selectById(1437933970770661377L);
        System.out.println(consumer);
        System.out.println(producer);
        // Producer producer = producerMapper.selectById("");
        // System.out.println(consumer.getId() + producer.getId());
    }
}
