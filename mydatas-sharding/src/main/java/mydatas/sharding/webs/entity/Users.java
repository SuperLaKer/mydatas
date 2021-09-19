package mydatas.sharding.webs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2021-05-22
 */
@Data
@TableName("user_all")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long user_id;
    private String user_name;
    private Integer tenant_id;

    public Users(String user_name, Integer tenant_id) {
        this.user_name = user_name;
        this.tenant_id = tenant_id;
    }

}
