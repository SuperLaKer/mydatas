package aa.mydatas.jpa.learnSpring.queries.test;


import aa.mydatas.jpa.entity.Users;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aa.mydatas.jpa.learnSpring.ApplicationStarter;
import aa.mydatas.jpa.repositories.UsersRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 异常：
 * No identifier specified for entity：实体类主键添加 javax.persistence.Id，包别错了
 */

// 引入spring-test依赖
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationStarter.class)
public class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    @BeforeClass
    public static void beforeClass() {
    }

    @Test
    public void findAll() {
        List<Users> userList = usersRepository.findAll();
        userList.forEach(users -> System.out.println(users.getUserName()));
    }

    /**
     * 根据id查询
     */
    @Test
    public void testFindOne() {
        // userDao.findOne(1);
    }

    /**
     * save : 保存或者更新
     * 根据传递的对象是否存在主键id，
     * 如果没有id主键属性：保存
     * 存在id主键属性，根据id查询数据，更新数据
     */
    @Test
    public void testSave() {
        Users Users = usersRepository.save(new Users());
        System.out.println(Users);
    }

    @Test
    public void testUpdate() {
        // UserDao.save(Users);
    }

    @Test
    public void testDelete() {
        Users Users = new Users();
        Users.setId(6);
        usersRepository.delete(Users);
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        List<Users> list = usersRepository.findAll();
        list.forEach(System.out::println);
    }


    /**
     * 测试统计查询：查询客户的总数量
     * count:统计总条数
     */
    @Test
    public void testCount() {
        long count = usersRepository.count(); //查询全部的客户数量
        System.out.println(count);
    }

    /**
     * 测试：判断id为4的客户是否存在
     * 1. 可以查询以下id为4的客户
     * 如果值为空，代表不存在，如果不为空，代表存在
     * 2. 判断数据库中id为4的客户的数量
     * 如果数量为0，代表不存在，如果大于0，代表存在
     */
    @Test
    public void testExists() {
        boolean exists = usersRepository.existsById(5);
        assert exists;
    }


    /**
     * 根据id从数据库查询
     *
     * @Transactional : 因为是在单元测试中使用，需要添加事物。保证getOne正常运行
     * <p>
     * findOne：
     * em.find()           :立即加载
     * getOne：
     * em.getReference     :延迟加载：需要事物支持，开启事物，提交等等。内部使用AOP已经完善
     * * 返回的是一个客户的动态代理对象
     * * 什么时候用，什么时候查询
     */
    @Test
    @Transactional
    public void testGetOne() {
        Users Users = usersRepository.getOne(3);
        System.out.println(Users);
    }
}