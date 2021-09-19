package mydatas.mybatis.entity2;


import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("learn_mybatis_plus")
@NoArgsConstructor
@AllArgsConstructor
public class LearnMybatisPlus implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    @TableField("create_time")
    private Date create_time;
    private String name;

    @TableLogic(value = "1", delval = "0")
    @TableField("state")
    private Integer state;

    @Version
    private Integer version;

    @TableField("tenant_id")
    private Integer tenantId;

    public LearnMybatisPlus(Date create_time, String name, Integer state) {
        this.create_time = create_time;
        this.name = name;
        this.state = state;
    }
}
