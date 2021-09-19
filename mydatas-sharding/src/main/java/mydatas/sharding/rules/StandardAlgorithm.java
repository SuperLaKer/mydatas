package mydatas.sharding.rules;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;

public class StandardAlgorithm implements StandardShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Integer> shardingValue) {
        System.out.println(shardingValue);
        for (String availableTargetName : availableTargetNames) {
            String[] split = availableTargetName.split("_");
            if (split[split.length-1].equals(shardingValue.getValue().toString())) {
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
        return "slk1";
    }
}
