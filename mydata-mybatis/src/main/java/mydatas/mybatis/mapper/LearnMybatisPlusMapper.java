package mydatas.mybatis.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import mydatas.mybatis.entity2.LearnMybatisPlus;
import mydatas.mybatis.entity2.LearnMybatisPlusVo;
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

public interface LearnMybatisPlusMapper extends BaseMapper<LearnMybatisPlus> {


    /**
     * 引入官方的插件就行了注意版本3.4.0改了
     */
    @Select("select * from learn_mybatis_plus where state = #{state}")
    List<LearnMybatisPlusVo> selectPageVo(IPage<LearnMybatisPlusVo> page, Integer state);


    @Select("select * from learn_mybatis_plus")
    List<LearnMybatisPlus> selectAll();

    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from learn_mybatis_plus")
    List<LearnMybatisPlus> selectAllIgnore();


}
