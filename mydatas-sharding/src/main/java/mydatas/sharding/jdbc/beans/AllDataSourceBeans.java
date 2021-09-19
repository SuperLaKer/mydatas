package mydatas.sharding.jdbc.beans;


import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 仅仅分库:
 * ds0: user0(主键user_id), user_info0(user_id外键)
 * ds1: user1(主键user_id), user_info1(user_id外键)
 * 两张表都根据user_id分库，这样有关联关系的数据就会存放到同一张数据库
 */
@Configuration
public class AllDataSourceBeans {

    DataSource dataSource0() {
        MysqlDataSource dataSource0 = new MysqlDataSource();
        dataSource0.setUrl("jdbc:mysql://localhost:3306/sharding0?useSSL=false&serverTimezone=UTC");
        dataSource0.setUser("root");
        dataSource0.setPassword("roottt");
        return dataSource0;
    }

    DataSource dataSource1() {
        MysqlDataSource dataSource1 = new MysqlDataSource();
        dataSource1.setUrl("jdbc:mysql://localhost:3306/sharding1?useSSL=false&serverTimezone=UTC");
        dataSource1.setUser("root");
        dataSource1.setPassword("roottt");
        return dataSource1;
    }

    /**
     * 方法名字是数据库的名字
     */
    DataSource shardingAll1() {
        MysqlDataSource shardingTableOnly = new MysqlDataSource();
        shardingTableOnly.setUrl("jdbc:mysql://localhost:3306/sharding_all_1?useSSL=false&serverTimezone=UTC");
        shardingTableOnly.setUser("root");
        shardingTableOnly.setPassword("roottt");
        return shardingTableOnly;
    }

    DataSource shardingAll2() {
        MysqlDataSource shardingTableOnly = new MysqlDataSource();
        shardingTableOnly.setUrl("jdbc:mysql://localhost:3306/sharding_all_2?useSSL=false&serverTimezone=UTC");
        shardingTableOnly.setUser("root");
        shardingTableOnly.setPassword("roottt");
        return shardingTableOnly;
    }

    Map<String, DataSource> dataSourceMap() {
        HashMap<String, DataSource> dataSourceHashMap = new HashMap<>();
        dataSourceHashMap.put("dataSource0", dataSource0());
        dataSourceHashMap.put("dataSource1", dataSource1());
        dataSourceHashMap.put("sharding_all_1", shardingAll1());
        dataSourceHashMap.put("sharding_all_2", shardingAll2());
        return dataSourceHashMap;
    }

    @Autowired
    @SuppressWarnings("all")
    ShardingRuleConfiguration ruleConfiguration;

    @Bean
    DataSource dataSource() throws SQLException {
        return ShardingSphereDataSourceFactory
                .createDataSource(dataSourceMap(), Collections.singleton(ruleConfiguration), new Properties());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean.getObject();
    }
}
