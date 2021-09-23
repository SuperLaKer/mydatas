package aa.mydatas.jpa.learnSpring.queries.test;


import aa.mydatas.jpa.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aa.mydatas.jpa.learnSpring.ApplicationStarter;
import aa.mydatas.jpa.repositories.E_Specification;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

/**
 * 自定义查询条件
 * 1.实现Specification接口（提供泛型：查询的对象类型）
 * 2.实现toPredicate方法（构造查询条件）
 * 3.需要借助方法参数中的两个参数（
 * root：查询根对象，可以获取需要查询的对象属性
 * CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
 * ）
 * 案例：根据客户名称查询，查询客户名为传智播客的客户
 * 查询条件
 * 1.查询方式
 * cb对象
 * 2.比较的属性名称
 * root对象
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationStarter.class)
public class ESpecificationTest {


    @Autowired
    E_Specification eSpecification;

    /**
     * 自定义查询条件，findOne精确
     */
    @Test
    public void testSpec() {

        Specification<Users> spec = new Specification<Users>() {  // 匿名内部类
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                // 获取比较的属性
                Path<Object> name = root.get("name");
                // 构造查询条件: select * from Users where username = '小果果';
                /**
                 * 第一个参数：需要比较的属性（path对象）
                 * 第二个参数：当前需要比较的取值
                 *  criteria: 标准，
                 *  criteriaBuilder：构建查询标准，自动以查询条件
                 */
                return criteriaBuilder.equal(name, "小果果");
            }
        };
        Optional<Users> optional = eSpecification.findOne(spec);

        // 判断是存在的
        assert optional.isPresent();
        Users Users = optional.get();
        System.out.println(Users);

    }

    /**
     * 多条件查询
     * 案例：根据 用户名（name） 和 用户性别(gender)
     * 返回值：如果用户名唯一：则是findOne
     */
    @Test
    public void testSpec1() {
        /**
         *  root:获取属性
         *  cb：构造查询，将以上两个查询联系起来
         */
        Specification<Users> spec = new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> name = root.get("name");  // 用户名
                Path<Object> gender = root.get("gender");//所属行业

                // 构造查询
                Predicate p1 = cb.equal(name, "小王");  // 第一个参数，path（属性），第二个参数，属性的取值
                Predicate p2 = cb.equal(gender, "1");


                // 组合查询条件（ and || or）
                // cb.and, cb.or
                return cb.and(p1, p2);
            }
        };
        List<Users> userList = eSpecification.findAll(spec);
        userList.forEach(System.out::println);
    }


    /**
     * 模糊匹配
     * equal:直接比较
     * criteriaBuilder.equal(name, "小果果");
     * <p>
     * gt，lt,ge,le,like : 得到path对象，根据path指定比较的参数类型，再去进行比较
     * 指定参数类型：path.as(类型的字节码对象)
     * cb.like(name.as(String.class), "小%");
     */
    @Test
    public void testSpec3() {

        Specification<Users> spec = new Specification<Users>() {
            @Override
            public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Path<Object> name = root.get("name");

                // equal:直接比较，
                return cb.like(name.as(String.class), "小%");
            }
        };
        List<Users> list = eSpecification.findAll(spec);
        System.out.println("排序前：");
        list.forEach(System.out::println);

        // 添加排序
        // 创建排序对象,需要调用构造方法实例化sort对象
        // 第一个参数：排序的顺序（倒序，正序）
        // Sort.Direction.DESC:  // 倒序
        // Sort.Direction.ASC ： 升序
        // 第二个参数：排序的属性名称

        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        List<Users> users = eSpecification.findAll(spec, sort);
        System.out.println("排序前：");
        users.forEach(System.out::println);
    }

    /**
     * 分页查询
     * Specification: 查询条件
     * Pageable：分页参数
     * 分页参数：查询的页码，每页查询的条数
     * findAll(Specification,Pageable)：带有条件的分页
     * findAll(Pageable)：没有条件的分页
     * 返回：Page（springDataJpa为我们封装好的pageBean对象，数据列表，共条数）
     */
    @Test
    public void testSpec4() {

        Specification<Users> spec = null;

        //PageRequest对象是Pageable接口的实现类
        /**
         * 创建PageRequest的过程中，需要调用他的构造方法传入两个参数
         *      第一个参数：当前查询的页数（从0开始）
         *      第二个参数：每页查询的数量
         */
        Pageable pageable = PageRequest.of(0, 2);
        //分页查询
        Page<Users> page = eSpecification.findAll(pageable);
        System.out.println(page.getContent()); //得到数据集合列表
        System.out.println(page.getTotalElements());//得到总条数
        System.out.println(page.getTotalPages());//得到总页数
    }

    @Test
    public void exampleQuery() {
        Users users = new Users();
        users.setEnabled(1);
        Example<Users> example = Example.of(users);
        List<Users> usersList = eSpecification.findAll(example);
        System.out.println("*************************" + usersList.size());
    }

}