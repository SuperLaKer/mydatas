package mydatas.sql.webs.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 *
 * </p>
 *
 * @author lla
 * @since 2021-09-18
 */
@Data
@EqualsAndHashCode()
@TableName("the_order")
@NoArgsConstructor
public class TheOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField("the_describe")
    private String theDescribe;
    @TableField("the_money")
    private Integer theMoney;
    @TableField("user_id")
    private Long userId;
    @TableField("product_id")
    private Long productId;
    @TableField("product_num")
    private Integer productNum;
    @TableField("create_time")
    private Timestamp createTime;
    @TableLogic(value = "0", delval = "1")
    @TableField("is_deleted")
    private Boolean isDeleted;
    @TableField("union_a")
    private Integer unionA;
    @TableField("union_b")
    private Integer unionB;

    public TheOrder(Long userId, Long productId, Boolean isDeleted) {
        this.userId = userId;
        this.productId = productId;
        this.isDeleted = isDeleted;
    }
}
