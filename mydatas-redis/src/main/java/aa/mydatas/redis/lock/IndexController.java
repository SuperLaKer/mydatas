package aa.mydatas.redis.lock;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
public class IndexController {


    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/deduct_stock")
    public String deductStock() throws InterruptedException {
        String lockKey = "product_001";
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            // 分布式锁并实现锁续命功能，健壮性可以
            // 默认30秒，每个10秒锁续命
            redissonLock.lock();
            int stock = Integer.parseInt(Objects.requireNonNull(stringRedisTemplate.opsForValue().get("stock")));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存:" + realStock + "");
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            redissonLock.unlock();
        }
        return "end";
    }


    @RequestMapping("/readLock")
    public String readLock() throws InterruptedException {
        String lockKey = "product_001";
        // 这里需要自己实例化不同redis实例的redisson客户端连接，这里只是伪代码用一个redisson客户端简化了
        RLock lock1 = redisson.getLock(lockKey);
        RLock lock2 = redisson.getLock(lockKey);
        RLock lock3 = redisson.getLock(lockKey);

        /*
          根据多个 RLock 对象构建 RedissonRedLock （最核心的差别就在这里）
         */
        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);
        try {
            /*
              4.尝试获取锁
              waitTimeout 尝试获取锁的最大等待时间，超过这个值，则认为获取锁失败
              leaseTime   锁的持有时间,超过这个时间锁会自动失效（值应设置为大于业务处理的时间，确保在锁有效期内业务能处理完）
             */
            boolean res = redLock.tryLock(10, 30, TimeUnit.SECONDS);
            if (res) {
                //成功获得锁，在这里处理业务
            }
        } catch (Exception e) {
            throw new RuntimeException("lock fail");
        } finally {
            //无论如何, 最后都要解锁
            redLock.unlock();
        }
        return "end";
    }

}