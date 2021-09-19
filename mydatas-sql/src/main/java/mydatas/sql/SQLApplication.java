package mydatas.sql;

import aa.slkenv.dataBase.MissBean;
import aa.slkenv.dataBase.anno.EnableMybatisSpring;
import aa.slkenv.dataBase.anno.EnableSpringDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import mydatas.sql.haha.entity.TimeDate;
import mydatas.sql.haha.service.ITimeDateService;

import javax.sql.DataSource;


/**
 * private String status;
 * private String userName;
 * private String password;
 * private Long age;
 * private Long testAInt;
 * private Long testBInt;
 * private java.sql.Timestamp createDate;
 */
@EnableMybatisSpring
@Configuration
@ComponentScan
@MapperScan("aa.spring.datas.sql.haha.mapper")
@EnableSpringDataSource(dataBaseName = "all_in_one")
public class SQLApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(SQLApplication.class);
        ac.refresh();

        ITimeDateService timeDateService = ac.getBean(ITimeDateService.class);
        TimeDate timeDate = timeDateService.findTimeDateById(1L);
        System.out.println(timeDate);

        ac.close();
    }

    @Bean
    @MissBean(SqlSessionFactory.class)
    public SqlSessionFactory mybatisSqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        SqlSessionFactory sqlSessionFactory = mybatisSqlSessionFactoryBean.getObject();
        assert sqlSessionFactory != null;
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.addInterceptor(new ShowSql());
        return sqlSessionFactory;
    }


    public void addMybatisInterceptors(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.addInterceptor(new ShowSql());
    }
}
