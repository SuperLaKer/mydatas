package aa.mydatas.redis;

import aa.slkenv.utils.redis.RedisMQUtils;
import aa.slkenv.utils.redis.RedisUtils;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import aa.mydatas.redis.lua.RedisLua;

import java.util.concurrent.TimeUnit;

@EnableScheduling
@SpringBootApplication
@Import({RedisUtils.class, RedisMQUtils.class})
public class RedisApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ac = SpringApplication.run(RedisApplication.class, args);
        System.out.println(ac.getBeanDefinitionNames()[0]);

        try {
            RedisLua redisLua = ac.getBean(RedisLua.class);
            redisLua.useLua();
        } catch (BeansException e) {
            e.printStackTrace();
        } finally {
            TimeUnit.SECONDS.sleep(5);
            ac.close();
        }
    }


    /**
     * redisson是redis官方推出的Java客户端，功能非常强大，可以用来实现分布式锁
     */
    @Bean
    public Redisson redisson() {
        // 此为单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
        /*config.useClusterServers()
                .addNodeAddress("redis://192.168.0.61:8001")
                .addNodeAddress("redis://192.168.0.62:8002")
                .addNodeAddress("redis://192.168.0.63:8003")
                .addNodeAddress("redis://192.168.0.61:8004")
                .addNodeAddress("redis://192.168.0.62:8005")
                .addNodeAddress("redis://192.168.0.63:8006");*/
        return (Redisson) Redisson.create(config);
    }
}
