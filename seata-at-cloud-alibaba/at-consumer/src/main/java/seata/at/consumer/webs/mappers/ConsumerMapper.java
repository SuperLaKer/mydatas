package seata.at.consumer.webs.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import seata.at.consumer.webs.tables.Consumer;

import java.math.BigDecimal;

public interface ConsumerMapper extends BaseMapper<Consumer> {

    @Update({
            "update my_consumer set the_money = the_money + #{money} where id = #{id}"
    })
    int updateMoney(@Param("id") Long id, @Param("money") BigDecimal money);
}
