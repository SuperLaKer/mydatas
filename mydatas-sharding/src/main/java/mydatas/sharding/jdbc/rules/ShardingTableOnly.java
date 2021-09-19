package mydatas.sharding.jdbc.rules;

import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Properties;

/**
 * 同一个数据库，仅仅分表
 * table: shardingTableOnly
 */
@Configuration
public class ShardingTableOnly {
    @Bean
    public ShardingRuleConfiguration getRuleConfiguration() {
        // 全局分片配置
        ShardingRuleConfiguration globalConfig = new ShardingRuleConfiguration();
        // user表映射，逻辑表和数据节点映射
        // 数据节点命名和DataSourceMap的key有关
        ShardingTableRuleConfiguration userTableConfig =
                new ShardingTableRuleConfiguration("user_all", "sharding_all_${1..2}.user_${1..3}");

        // 任何表的算法
        Map<String, ShardingSphereAlgorithmConfiguration> shardingAlgorithms = globalConfig.getShardingAlgorithms();
        shardingAlgorithms.put("byTenantId", new ShardingSphereAlgorithmConfiguration("slk1", null));
        shardingAlgorithms.put("byUserId", new ShardingSphereAlgorithmConfiguration("slk2", null));

        userTableConfig.setTableShardingStrategy(
                new StandardShardingStrategyConfiguration("tenant_id", "byTenantId"));
        userTableConfig.setDatabaseShardingStrategy(
                new StandardShardingStrategyConfiguration("user_id", "byUserId"));

        // 其他数据库数据表
        globalConfig.getTables().add(userTableConfig);
        return globalConfig;
    }

    /**
     * 添加算法：分库或分表的算法
     *
     * @param globalConfig  ShardingRuleConfiguration 全局配置
     * @param algorithmName 算法名字
     * @param expression    算法表达式
     */
    private void addAlgorithm(ShardingRuleConfiguration globalConfig, String algorithmName, String expression) {
        //addAlgorithm(globalConfig, "shardingTableByTenantId", "user_${tenant_id}");
        //addAlgorithm(globalConfig, "shardingDBByUserId", "sharding_all_${ user_id%2 + 1}");
        Map<String, ShardingSphereAlgorithmConfiguration> shardingAlgorithms = globalConfig.getShardingAlgorithms();
        Properties properties = new Properties();
        properties.setProperty("algorithm-expression", expression);
        shardingAlgorithms.put(algorithmName, new ShardingSphereAlgorithmConfiguration("INLINE", properties));
    }
}
