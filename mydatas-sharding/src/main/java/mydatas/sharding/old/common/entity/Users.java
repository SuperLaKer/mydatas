package mydatas.sharding.old.common.entity;

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
@EqualsAndHashCode(callSuper = false)
@TableName("user_all")
@NoArgsConstructor
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long user_id;
    private String user_name;

    public Users(String user_name) {
        this.user_name = user_name;
    }
}
