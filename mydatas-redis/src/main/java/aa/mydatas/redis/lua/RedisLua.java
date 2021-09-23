package aa.mydatas.redis.lua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 使用Lua脚本执行减库存操作
 * 模拟的场景：
 * last = (store库存数量 - 购买数量)。last > 0，last == 0, last < 0;
 * Lua返回三个返回值，每个返回值代表不同的情况。1库存足够，0刚好卖完了，-1库存不足
 * <p>
 * 1: 生成订单
 * 0: 添加售罄标志、生成订单
 * -1: throw(库存不足)。(注意：：：：不要添 "加售罄标志"，对于其他人来说库存可能足够！！！)
 * <p>
 * 少卖率 <= (单人单次最大购买量 / 库存总量)。发生少卖的情况很低，但是并发量够的话不会发生少买。
 */
@Component
public class RedisLua {

    @Autowired
    @SuppressWarnings("ALL")
    RedisTemplate redisTemplate;

    public void useLua() {
        StringBuilder stringBuilder = new StringBuilder()
                // 变量
                .append("local store = tonumber(redis.call('get', KEYS[1])) ")
                .append("local needNum = tonumber(ARGV[1]) ")
                .append("local last = (store - needNum) ")
                // 0正好买完
                .append("if last == 0 then ")
                .append("redis.call('set', KEYS[1], last) ")
                .append("return 0 end ")
                // 还有库存
                .append("if last > 0 then ")
                .append("redis.call('set', KEYS[1], last) ")
                .append("return 1 end ")
                // 本次库存不足, 不执行减库存操作了
                .append("if last < 0 then return -1 end ");

        // Long：返回值类型
        DefaultRedisScript<Long> defaultScript = new DefaultRedisScript<>();
        defaultScript.setScriptText(new String(stringBuilder));
        defaultScript.setResultType(Long.class);

        // 购买的数量
        List<Integer> integerList = new ArrayList<>();
        integerList.add(100);
        integerList.add(100);
        integerList.add(100);

        // 库存200, 分别测试 1， 0， -1
        // 第一次：1, 第二次：0, 第三次：-1
        // 正常逻辑下：第三次直接"售罄"。不会访问redis
        for (int i = 0; i < 3; i++) {
            // 第一个参数：Lua脚本，第二个参数：脚本用到的keysList，第三个参数：就是一些变量
            // 坐标都是从 1 开始的
            Object execute = redisTemplate.execute(defaultScript, Collections.singletonList("key_123"), integerList.get(i));
            System.out.println(execute);
        }
    }
}