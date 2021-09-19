package mydatas.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mydatas.mybatis.entity.Roles;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author slk
 * @since 2021-07-28
 */
public interface RolesMapper extends BaseMapper<Roles> {

    /**
     * 一对多查询，根据meta_id查询roleList。此方法别roles引用了
     *
     * @param metaId meta主键
     * @return roles列表
     */
    @Select({
            "select roles.* from meta_roles",
            "left join roles",
            "on meta_roles.roles_id = roles.id",
            "where meta_roles.meta_id = #{metaId}"})
    List<Roles> findRoleListByMetaId(@Param("metaId") Integer metaId);

}


