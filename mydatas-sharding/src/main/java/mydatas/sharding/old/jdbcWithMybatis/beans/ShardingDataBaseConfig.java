package mydatas.sharding.old.jdbcWithMybatis.beans;


import aa.slkenv.dataBase.ShowSql;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
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
public class ShardingDataBaseConfig {

    @Bean("shardingDataSource")
    public DataSource shardingDataSource() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        MysqlDataSource dataSource0 = new MysqlDataSource();
        dataSource0.setUser("root");
        dataSource0.setUrl("jdbc:mysql://localhost:3306/sharding0?useSSL=false&serverTimezone=UTC");
        dataSource0.setPassword("roottt");
        System.out.println(dataSource0.getConnection());
        dataSourceMap.put("ds0", dataSource0);

        // 配置第一个数据源
        MysqlDataSource dataSource1 = new MysqlDataSource();
        dataSource1.setUser("root");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/sharding1?useSSL=false&serverTimezone=UTC");
        dataSource1.setPassword("roottt");
        System.out.println(dataSource1.getConnection());
        dataSourceMap.put("ds1", dataSource1);

        // 真实表和虚拟表映射(虚拟表: logicTable)
        TableRuleConfiguration userTableConfig =
                new TableRuleConfiguration("user_all", "ds0.user, ds1.user1");
        TableRuleConfiguration userInfoConfig =
                new TableRuleConfiguration("user_info_all", "ds0.user_info0, ds1.user_info1");

        // 分库策略：字段经过策略计算后返回一个数据源的名字
        // ds_0 or ds_1
        userInfoConfig.setDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));


        userTableConfig.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id"
                , (PreciseShardingAlgorithm<Long>) (availableTargetNames, shardingValue) -> {
            for (String availableTargetName : availableTargetNames) {
                if (availableTargetName.endsWith(shardingValue.getValue() % 2 + "")) {
                    return availableTargetName;
                }
            }
            throw new UnsupportedOperationException();
        }
        ));

        // 主键生成策略
        userTableConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "user_id"));
        userInfoConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "id"));
        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        Collection<TableRuleConfiguration> tableRuleConfigs = shardingRuleConfig.getTableRuleConfigs();
        tableRuleConfigs.add(userTableConfig);
        tableRuleConfigs.add(userInfoConfig);

        // 绑定表（可能是这样的）
        // user0和user_info0在同一个数据库
        shardingRuleConfig.getBindingTableGroups().add("user_all, user_info_all");

        // 获取数据源对象
        // ShardingDataSource
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
        System.out.println(dataSource);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(shardingDataSource);
        SqlSessionFactory sqlSessionFactory = sessionFactoryBean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.addInterceptor(new ShowSql());
        return sqlSessionFactory;
    }
}
