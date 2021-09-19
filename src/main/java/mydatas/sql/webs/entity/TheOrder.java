package mydatas.sql.webs.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lla
 * @since 2021-09-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TheOrder extends Serializable {

    private static final long serialVersionUID = 1L;

    private String theDescribe;

    private Integer theMoney;

    private Boolean isDeleted;

    /**
     * 用户id
     */
    private Long userId;


}
