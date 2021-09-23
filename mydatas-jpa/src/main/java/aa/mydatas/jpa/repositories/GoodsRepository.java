package aa.mydatas.jpa.repositories;


import aa.mydatas.jpa.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JpaRepository：提供了一些基础的方法，底层还是调用了 entityManager.find(),merge(),persist()等方法
 * JpaSpecificationExecutor：提供了一些复杂查询的方法。。。
 */
public interface GoodsRepository extends JpaRepository<Goods, Integer>, JpaSpecificationExecutor<Goods> {

}
