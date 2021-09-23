package mydatas.sharding.old.jdbcWithMybatis;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import mydatas.sharding.old.common.entity.Users;
import mydatas.sharding.old.common.mapper.UserMapper;

import javax.sql.DataSource;
import java.util.List;

//@EnableSpringDataSource(dataBaseName = "sharding_table")
@EnableAspectJAutoProxy
@ComponentScan("aa.sharding.orm")
@MapperScan("aa.sharding.orm.web.mapper")
public class ApplicationMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationMain.class);
        DataSource dataSource = ac.getBean(DataSource.class);
        System.out.println(dataSource);
        UserMapper userMapper = ac.getBean(UserMapper.class);
        insertIntoUser(userMapper);
//         selectFromUser(userMapper);
    }

    private static void selectFromUser(UserMapper userMapper) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id", 602938044063940609L, 602936657670307841L, 602936588774670336L, 602928181665595393L);
        // queryWrapper.gt("user_id", 602938044063940609L);
        List<Users> usersList = userMapper.selectList(queryWrapper);
        System.out.println(usersList.size());
    }

    private static void insertIntoUser(UserMapper userMapper) {
        // 获取新插入数据的id
        for (int i = 0; i < 10; i++) {
            Users users = new Users("long" + i);
            int ming = userMapper.insert(users);
            System.out.println(ming);
        }
    }
}
