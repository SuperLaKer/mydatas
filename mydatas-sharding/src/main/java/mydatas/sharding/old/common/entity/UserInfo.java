package mydatas.sharding.old.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
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
@TableName("user_info_all")
@NoArgsConstructor
public class UserInfo implements Serializable {

    private Long id;
    private String address;
    private Long user_id;

}
