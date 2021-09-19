package seata.at.consumer;

import aa.slkenv.dataBase.ShowSql;
import aa.slkenv.dataBase.anno.EnableSpringDataSource;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@Import({ShowSql.class})
@EnableTransactionManagement
@EnableSpringDataSource(dataBaseName = "test_seata")
@EnableAutoDataSourceProxy(dataSourceProxyMode = "AT")
@MapperScan(basePackages = {"seata.at.consumer.webs.mappers"})
public class ATConsumerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(ATConsumerApp.class, args);
    }
}
