package mydatas.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mydatas.mybatis.entity.Meta;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author slk
 * @since 2021-07-28
 */

public interface MetaMapper extends BaseMapper<Meta> {

    // 根据id查询meta包括roleList
    @Select({"select * from meta where id = #{id}"})
    @Results(id = "metaWithRolesMap",
            value = {@Result(column = "id", property = "roleList", many =
            @Many(select = "mydatas.mybatis.mapper.RolesMapper.findRoleListByMetaId",
                    fetchType = FetchType.LAZY))})
    Meta selectMetaAndRolesByMetaId(@Param("id") Integer id);

}
