package aa.mydatas.jpa.repositories;


import aa.mydatas.jpa.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.List;

/**
 * 是对jpql查询 更加深入的一层封装
 * 安装springDataJpa提供的规则去定义方法的名字，就能实现查询。不在需要使用jpql
 */
public interface D_method_name extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

    /**
     * 精确（完整）查询  ||  findBy + 实体类属性-首字母大写
     *
     * @param name 1
     * @return 1
     */
    Users findByUserName(String name);


    /**
     * 模糊查询 findBy + 首字母大写属性 + 查询方式
     * ** 查询方式：Like，isnull, and so on
     */
    List<Users> findByUserNameLike(String name);


    /**
     * 多条件查询
     * select * from Users where id > 5 and username like "小%"
     * findBy + 属性A + 属性A查询方式 + 多条件连接符( and || or ) + 属性B + 属性B查询方式
     */

    List<Users> findByIdGreaterThanAndUserNameLike(Integer id, String name);

}
