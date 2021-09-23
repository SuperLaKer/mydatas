package mydatas.sharding.old;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 按照user_id分表
 * user_id主键，非数据库自增主键，手动设置
 */
public class A_分表_user表_snowflake {

    static Connection conn = null;

    public static void main(String[] args) throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/sharding_table?useSSL=false&serverTimezone=UTC");
        dataSource1.setUsername("root");
        dataSource1.setPassword("roottt");
        dataSourceMap.put("ds", dataSource1);

        // 表对应关系
        TableRuleConfiguration orderTableRuleConfig =
                new TableRuleConfiguration("userx", "ds.user${0..1}");

        // 分库（同一个数据库），注销没有问题
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "ds"));

        // 分表（根据user_id）
        orderTableRuleConfig.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "user${user_id % 2}"));

        // 雪花算法
        orderTableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("SNOWFLAKE", "user_id"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);


        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());

        @SuppressWarnings("all")
        String insert = "insert into userx (user_id, user_name) value (?, ?)";
        String insert_snowflake = "insert into userx (user_name) value (?)";
        @SuppressWarnings("all")
        String query = "select * from userx";
        conn = dataSource.getConnection();
        // query(query, conn);
        insert(insert_snowflake);
    }

    private static void insert(String ac) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(ac);
            for (int i = 111; i < 130; i++) {
                // preparedStatement.setInt(1, i);  分布式主键snowflake
                preparedStatement.setString(1, "name" + i);
                int t = preparedStatement.executeUpdate();
                System.out.println(t);
            }
        } finally {
            close(preparedStatement, resultSet);
        }
    }

    private static void query(String sql) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1));
                System.out.println("\t" + resultSet.getString(2));
            }

        } finally {
            close(preparedStatement, resultSet);
        }
    }

    private static void close(PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) resultSet.close();
        if (preparedStatement != null) preparedStatement.close();
        if (conn != null) conn.close();
    }
}
