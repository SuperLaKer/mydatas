package mydatas.sql.haha.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2021-03-31
 */
@Data

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 外键，关联user id
     */
    private Long userId;

    /**
     * 是否启用
     */
    private Integer status;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 创建日期
     */
    private LocalDateTime createDate;

    /**
     * 保留字段a
     */
    private String tempStringA;

    /**
     * 保留字段b
     */
    private String tempStringB;


}
