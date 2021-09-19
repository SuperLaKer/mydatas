package mydatas.sql.haha.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2021-03-31
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 是否启用
     */
    private String status;

    /**
     * 用户名（独立索引）
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄（联合索引_1）
     */
    private Integer age;

    /**
     * 测试字段1（联合索引_1）
     */
    private Integer testAInt;

    /**
     * 测试字段2（联合索引_1）
     */
    private Integer testBInt;

    /**
     * 创建日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    public User(String status, String userName, String password, Integer age, Integer testAInt, Integer testBInt, Timestamp createDate) {
        this.status = status;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.testAInt = testAInt;
        this.testBInt = testBInt;
        this.createDate = createDate;
    }

    public User() {
    }
}
