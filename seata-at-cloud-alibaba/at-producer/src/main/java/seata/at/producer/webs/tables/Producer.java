package seata.at.producer.webs.tables;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("my_producer")
@NoArgsConstructor
public class Producer implements Serializable {
    @TableId(type= IdType.ASSIGN_ID)
    private Long id;
    @TableField("the_describe")
    private String the_describe;
    private BigDecimal the_money;

    public Producer(String the_describe, BigDecimal the_money) {
        this.the_describe = the_describe;
        this.the_money = the_money;
    }
}
