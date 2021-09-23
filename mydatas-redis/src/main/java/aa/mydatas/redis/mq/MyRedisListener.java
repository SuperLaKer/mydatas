package aa.mydatas.redis.mq;

import aa.slkenv.utils.redis.defaults.DefaultRedisChannelListener;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;


/**
 * 只需实现DefaultRedisChannelListener就能实现监听。
 * 记得配置 channel的名字  ${redis.mqchannel.name} 发布的时候需要使用
 */
@Component
public class MyRedisListener implements DefaultRedisChannelListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("收到消息了：" + new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
