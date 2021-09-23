package mydatas.sharding.old.proxy;


import aa.slkenv.dataBase.ShowSql;
import aa.slkenv.dataBase.anno.EnableSpringDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import mydatas.sharding.old.common.entity.Users;
import mydatas.sharding.old.common.mapper.UserMapper;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan("aa.sharding.proxy.web.mapper")
@ComponentScan
@EnableSpringDataSource(dataBaseName = "vDB", password = "root",
        url = "jdbc:mysql://localhost:3309/dataBaseName?serverTimezone=UTC&characterEncoding=utf-8")
public class ProxyApplication {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProxyApplication.class);
        System.out.println(ac.getBeanDefinitionNames()[0]);

        DataSource dataSource = ac.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());

        UserMapper userMapper = ac.getBean(UserMapper.class);
        insertIntoUser(userMapper);
    }

    private static void insertIntoUser(UserMapper userMapper) {
        // 获取新插入数据的id
        for (int i = 0; i < 10; i++) {
            Users users = new Users("long" + i);
            int ming = userMapper.insert(users);
            System.out.println(ming);
        }
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(shardingDataSource);
        SqlSessionFactory sqlSessionFactory = sessionFactoryBean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.addInterceptor(new ShowSql());
        return sqlSessionFactory;
    }
}
