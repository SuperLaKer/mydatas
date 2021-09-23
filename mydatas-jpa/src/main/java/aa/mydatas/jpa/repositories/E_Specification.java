package aa.mydatas.jpa.repositories;


import aa.mydatas.jpa.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * 动态查询，高级查询
 * <p>
 * Optional<T> findOne(@Nullable Specification<T> spec);
 * List<T> findAll(@Nullable Specification<T> spec);
 * Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);  // 查询全部并分页，pageable: 分页参数， Page<T>: pageBean, springDATAJPA提供
 * List<T> findAll(@Nullable Specification<T> spec, Sort sort);  // 排序：Sort 排序参数
 * long count(@Nullable Specification<T> spec);
 */
public interface E_Specification extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {

}
