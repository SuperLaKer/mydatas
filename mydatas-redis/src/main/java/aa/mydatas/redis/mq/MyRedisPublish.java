package aa.mydatas.redis.mq;

import aa.slkenv.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyRedisPublish {

    @Autowired
    RedisUtils redisUtils;

    @Value("${redis.mqchannel.name}")
    String channelName;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendMsg() {
        redisUtils.publish(channelName, "redis_msg: " + System.currentTimeMillis());
    }
}
