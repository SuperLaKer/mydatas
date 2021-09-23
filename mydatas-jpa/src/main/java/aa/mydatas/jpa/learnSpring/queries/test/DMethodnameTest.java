package aa.mydatas.jpa.learnSpring.queries.test;


import aa.mydatas.jpa.entity.Users;
import aa.mydatas.jpa.learnSpring.ApplicationStarter;
import aa.mydatas.jpa.repositories.D_method_name;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationStarter.class)
public class DMethodnameTest {

    @Autowired
    D_method_name dMethodname;

    // 精准查询
    @Test
    public void findByName() {
        Users Users = dMethodname.findByUserName("小三");
        System.out.println(Users);
        // select * from Users  where username=?
    }

    // 模糊查询
    @Test
    public void findByNameLike() {
        List<Users> userList = dMethodname.findByUserNameLike("小_");
        userList.forEach(System.out::println);
    }

    // 多条件, id > 5 and username like "小%";
    @Test
    public void findByIdGreaterThanAndNameLike() {
        List<Users> userList = dMethodname.findByIdGreaterThanAndUserNameLike(5, "小%");
        userList.forEach(System.out::println);
    }

}