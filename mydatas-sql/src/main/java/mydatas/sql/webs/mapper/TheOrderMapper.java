package mydatas.sql.webs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import mydatas.sql.webs.entity.TheOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lla
 * @since 2021-09-18
 */
public interface TheOrderMapper extends BaseMapper<TheOrder> {
    @Insert({
            "<script>\t",
            "insert into the_order (id, user_id, product_id,is_deleted, union_a,union_b,create_time,product_num) values\t",
            "<trim suffixOverrides=','>\t",
            "<foreach item='item' index='index' collection='theOrders' open='' separator=',' close=''>\t",
            "(#{item.id},#{item.userId},#{item.productId},#{item.isDeleted},#{item.unionA},#{item.unionB},#{item.createTime},#{item.productNum})\t",
            "</foreach>\t",
            "</trim>\t",
            "</script>\t"
    })
    int insertBatch(@Param("theOrders") List<TheOrder> theOrders);
}
