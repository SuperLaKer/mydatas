package seata.at.producer;

import aa.slkenv.dataBase.ShowSql;
import aa.slkenv.dataBase.anno.EnableSpringDataSource;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import seata.at.producer.webs.mappers.ProducerMapper;

@SpringBootApplication
@EnableDiscoveryClient
@Import({ShowSql.class})
@EnableTransactionManagement
@EnableAutoDataSourceProxy(dataSourceProxyMode = "AT")
@EnableSpringDataSource(dataBaseName = "test_seata_1")
@MapperScan(basePackages = {"seata.at.producer.webs.mappers"})
public class ATProducerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(ATProducerApp.class, args);
        ProducerMapper producerMapper = ac.getBean(ProducerMapper.class);
        // int insert = producerMapper.insert(new Producer("producer1", new BigDecimal("100.00")));
    }
}
