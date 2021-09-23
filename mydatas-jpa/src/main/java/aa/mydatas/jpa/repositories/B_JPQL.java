package aa.mydatas.jpa.repositories;


import aa.mydatas.jpa.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


/**
 * 要同时继承两个接口
 * <p>
 * 将jpql写到 方法上面。@Query("jpql")
 * JPA规范：createQuery方法里面写jpql
 * spring data jpa使用注解实现
 * <p>
 * 使用jpql更新数据：有几个注意事项：
 * *** 1、@Modifying：声明此方法是更新操作，应该和缓存有关系
 * *** 2、@Transactional：添加事物控制。service层
 * *** 3、@Rollback(false): 不自动回滚，如果不添加 执行完jpql 数据库数据不会发生变化。service层
 */
@SuppressWarnings("all")
public interface B_JPQL extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

    // 复杂查询

    // jpql：语法和SQL相似，查询的是类和类中的属性
    // 需要在接口上添加jpql，需要使用注解进行配置，注解@Query

    // findByName
    // jpql: from Users where name = ?
    @Query(value = "from Users where userName = ?1")
    Users findByName(String name);

    // 根据 name 和 id进行查询
    // select * from Users where Usersname = ? and id = ?
    // from Users where name = ?1 and id = ?2
    @Query(value = "from Users where userName = ?1 and id = ?2")
    Users findByNameId(String name, Integer id);


    /**
     * jpql更新操作
     * <p>
     * 根据id更新name
     * update Users set Usersname = ? where id = ?
     * update Users set name = ?1 where id = ?2
     * 声明：声明此方法是更新操作
     * <p>
     * TransactionRequiredException: 添加到service层，相当于测试层
     * 数据库信息未修改：spring Data JPA使用jpql
     * *** 需要手动添加事物的支持
     * *** 默认执行后回滚，而不是提交
     * Rollback(value = false)  不自动回滚
     */
    // @Transactional
    // @Rollback(value = false)
    @Modifying  // 声明此方法是更新操作
    @Query(value = "update Users set userName = ?1 where id = ?2")
    void updateUsers(String name, Integer id);
}
