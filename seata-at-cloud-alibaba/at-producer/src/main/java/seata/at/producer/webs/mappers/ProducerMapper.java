package seata.at.producer.webs.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import seata.at.producer.webs.tables.Producer;

import java.math.BigDecimal;

@Mapper
public interface ProducerMapper extends BaseMapper<Producer> {

    @Update({
            "update my_producer set the_money = the_money - #{money} where id = #{id}"
    })
    int updateMoney(@Param("id") Long id, @Param("money") BigDecimal money);
}
