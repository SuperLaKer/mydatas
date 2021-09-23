package aa.mydatas.jpa.repositories;


import aa.mydatas.jpa.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.List;

/**
 * JpaRepository：提供了一些基础的方法，底层还是调用了 entityManager.find(),merge(),persist()等方法
 * JpaSpecificationExecutor：提供了一些复杂查询的方法。。。
 */
public interface UsersRepository extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

    // spring测试环境，更改unit4的运行器
    List<Users> findAll();
}
