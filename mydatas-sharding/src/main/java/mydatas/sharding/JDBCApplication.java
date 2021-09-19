package mydatas.sharding;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import mydatas.sharding.jdbc.rules.ShardingTableOnly;
import mydatas.sharding.webs.entity.Users;
import mydatas.sharding.webs.mapper.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Configuration
@MapperScan("mydatas.sharding.webs.mapper")
@ComponentScan
public class JDBCApplication {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(JDBCApplication.class);
        ac.refresh();
        try {
            insertIntoUsers(ac);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TimeUnit.SECONDS.sleep(3);
            ac.close();
        }
    }

    private static void insertIntoUsers(AnnotationConfigApplicationContext ac) {
        UserMapper userMapper = ac.getBean(UserMapper.class);
        Random random = new Random();
        for (int i = 1; i < 20; i++) {
            Users users = new Users("小明" + i, random.nextInt(3) + 1);
            userMapper.insert(users);
        }
    }


}
