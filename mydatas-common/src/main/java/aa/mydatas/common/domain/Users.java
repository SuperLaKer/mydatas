package aa.mydatas.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@ToString
@Entity
@Table(name = "users")  // 实体类名字和表名的对应关系
public class Users {

    @TableId(type = IdType.INPUT)
    private Integer id;
    private String userName;  // 数据库 username
    private Integer gender;  // 数据库 sex
    private String password;  // 数据库 password
    private Integer enabled;
}
