package mydatas.sharding.old.jdbcWithMybatis.shardingRules;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 复合分片算法配合复合策略使用，支持范围与精确查询
 **/
public class ComplexAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    /**
     * @param collection               可用的数据源或表的名称（分库：所有的数据源名称，分表：所有的表名称）
     * @param complexKeysShardingValue 分片值(根据id分片默认类型Long)
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
        // table_0, table_1
        collection.forEach((item) -> System.out.println("item:)" + item));
        List<String> list = new ArrayList<>();
        // 范围查询
        Range<Long> range = complexKeysShardingValue.getColumnNameAndRangeValuesMap().get("id");

        // 让所有1<= X <= 10000的数据落到table_0表上,分库分表规则就这么简单制定好了
        Range<Long> rangeBase = Range.closed(1L, 10000L);
        if (rangeBase.encloses(range)) {
            list.add("table_0");
        }
        return list;
    }


}
