package mydatas.mybatis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author slk
 * @since 2021-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MetaRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * meta_id
     */
    private Integer metaId;

    /**
     * roles_id 
     */
    private Integer rolesId;


}
