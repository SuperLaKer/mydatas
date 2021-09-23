package aa.mydatas.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author slk
 * @since 2021-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 账户名
     */
    private String accountName;

    /**
     * 外键，关联到users表的id
     */
    private Integer uid;

    /**
     * 余额
     */
    private String money;

    /**
     * 联系地址
     */
    private String address;


    public Account(String accountName, Integer uid, String money) {
        this.accountName = accountName;
        this.uid = uid;
        this.money = money;
    }
}
