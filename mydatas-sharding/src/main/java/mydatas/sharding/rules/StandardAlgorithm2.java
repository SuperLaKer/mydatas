package mydatas.sharding.rules;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;

public class StandardAlgorithm2 implements StandardShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        Long value = shardingValue.getValue();
        long l = (value % 2) + 1;
        for (String availableTargetName : availableTargetNames) {
            String[] split = availableTargetName.split("_");
            if (split[split.length-1].equals(String.valueOf(l))) {
                return availableTargetName;
            }
        }
        return null;
    }

    @Override
    public Collection<String> doSharding(Collection availableTargetNames, RangeShardingValue shardingValue) {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "slk2";
    }
}
