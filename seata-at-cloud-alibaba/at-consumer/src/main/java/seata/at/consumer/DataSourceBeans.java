package seata.at.consumer;

import io.seata.rm.datasource.xa.DataSourceProxyXA;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * 一个注解搞定：@EnableAutoDataSourceProxy(dataSourceProxyMode = "XA")
 */
public class DataSourceBeans {

    public DataSource dataSourceProxy(DataSource dataSource) {
        // DataSourceProxy for AT mode
        // return new DataSourceProxy(druidDataSource);
        // DataSourceProxyXA for XA mode
        return new DataSourceProxyXA(dataSource);
    }

    public JdbcTemplate jdbcTemplate(DataSource dataSourceProxy) {
        return new JdbcTemplate(dataSourceProxy);
    }

    public PlatformTransactionManager txManager(DataSource dataSourceProxy) {
        return new DataSourceTransactionManager(dataSourceProxy);
    }
}
