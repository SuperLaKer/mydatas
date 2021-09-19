import org.apache.commons.dbcp2.BasicDataSource;
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
 * 先按照 user_addr 分库， 再按照user_id分表
 */
public class B_分库分表_user表 {

    static Connection conn = null;

    public static void main(String[] args) throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/sharding_db_0?useSSL=false&serverTimezone=UTC");
        dataSource1.setUsername("root");
        dataSource1.setPassword("roottt");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第二个数据源
        BasicDataSource dataSource2 = new BasicDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://localhost:3306/sharding_db_1?useSSL=false&serverTimezone=UTC");
        dataSource2.setUsername("root");
        dataSource2.setPassword("roottt");
        dataSourceMap.put("ds1", dataSource2);

        // 表对应关系
        TableRuleConfiguration orderTableRuleConfig =
                new TableRuleConfiguration("userx", "ds${0..1}.user${0..1}");

        // 根据user_addr分库
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_addr", "ds${user_addr % 2}"));

        // 根据user_id分表
        orderTableRuleConfig.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "user${user_id % 2}"));


        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);


        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());

        @SuppressWarnings("all")
        String insert = "insert into userx (user_id, user_name, user_addr) value (?, ?, ?)";
        @SuppressWarnings("all")
        String query = "select * from userx";
        conn = dataSource.getConnection();
        // query(query);
        insert(insert);
    }

    private static void insert(String sql) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 211; i < 230; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "name" + i);
                preparedStatement.setInt(3, 301);
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
