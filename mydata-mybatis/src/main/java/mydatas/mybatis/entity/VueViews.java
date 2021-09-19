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
public class VueViews implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * vue组件名字
     */
    private String name;

    /**
     * 如："@/views/permission/page"
     */
    private String path;


}
