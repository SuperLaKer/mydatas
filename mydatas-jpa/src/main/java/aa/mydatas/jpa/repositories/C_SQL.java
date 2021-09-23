package aa.mydatas.jpa.repositories;


import aa.mydatas.jpa.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

/**
 * spring DATA JPA使用SQL查询
 */
public interface C_SQL extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

    @Query(value = "select  * from Users where id = ?1", nativeQuery = true)
        // 本土化，原生查询
    Users findByIdUseSQL(Integer id);


    @Query(value = "select * from Users where username like ?1", nativeQuery = true)
    List<Users> likeNameQuery(String name);
    // likeNameQuery("小%");
    // likeNameQuery("小_");
}
