package mydatas.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author slk
 * @since 2021-07-28
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("meta")
public class Meta implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("title")
    private String title;

    @TableField("icon")
    private String icon;

    @TableField(exist = false)
    // static  transient: 不参与序列化
    private List<Roles> roleList = new ArrayList<>();

    @Override
    public String toString() {
        return "Meta{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
