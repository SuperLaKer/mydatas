package aa.mydatas.jpa.learnSpring.queries.test;


import aa.mydatas.jpa.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aa.mydatas.jpa.learnSpring.ApplicationStarter;
import aa.mydatas.jpa.repositories.C_SQL;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationStarter.class)
public class CSQLTest {

    @Autowired
    C_SQL cSQL;

    @Test
    public void findByIdUseSQL() {
        Users Users = cSQL.findByIdUseSQL(2);
        System.out.println(Users);
    }

    @Test
    public void likeNameQuery() {

        List<Users> userList = cSQL.likeNameQuery("小%");
        userList.stream().forEach(Users -> System.out.println(Users));

    }
}