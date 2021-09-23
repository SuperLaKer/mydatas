package aa.mydatas.common.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@ToString
@Entity
@Table(name = "users")  // 实体类名字和表名的对应关系
public class Users_JPA {

    @Id  // 当前字段是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 指定主键的生成策略
    /**
     * GenerationType.
     *  IDENTITY: 自增 MySQL
     *  SEQUENCE: 序列 Oracle
     *  TABLE: JPA提供的一种机制，通过一张数据库表的形式帮助我们完成主键自增
     *  AUTO: 自动
     */
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;
    @Column(name = "username")
    /**
     * column的属性：
     *  name： 指定数据库表名称
     *  unique： 是否唯一
     *  nullable： 是否可以为空
     *  insertable: 是否可以插入数据
     *  updateable：是否可以更新
     *  columnDefinition: 定义表时创建此列的DLL
     *  secondaryTable: 从表名。如果此列不建立在主表上，该属性定义该列所在从表的名字搭建开发环境
     */
    private String userName;  // 数据库 username
    private Integer gender;  // 数据库 sex
    private String password;  // 数据库 password
    private Integer enabled;

//    @OneToMany(mappedBy = "Users")  // 为本类在另一方的字段名,一对多
//    private List<Account> accounts = new ArrayList<>();


    public Users_JPA(String userName, Integer gender, String password, Integer enabled) {
        this.userName = userName;
        this.gender = gender;
        this.password = password;
        this.enabled = enabled;
    }

    public Users_JPA() {
        System.out.println("Users构造方法...");
    }
}
