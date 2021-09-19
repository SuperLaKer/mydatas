package mydatas.mybatis.entity2;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("learn_mybatis_plus")
@NoArgsConstructor
@AllArgsConstructor
public class LearnMybatisPlusVo implements Serializable {
    private Long id;
    private Date create_time;
    private String name;
    private Integer state;
    public LearnMybatisPlusVo(Date create_time, String name) {
        this.create_time = create_time;
        this.name = name;
    }
}
