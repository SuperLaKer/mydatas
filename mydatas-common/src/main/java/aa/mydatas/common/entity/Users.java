package aa.mydatas.common.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    @Column(name = "username")
    private String userName;
    private Integer gender;
    private String password;
    private Integer enabled;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)  // 为本类在另一方的字段名,一对多
    private List<Account> accounts;


    public Users(String userName, Integer gender, String password, Integer enabled) {
        this.userName = userName;
        this.gender = gender;
        this.password = password;
        this.enabled = enabled;
    }

    public Users() {
        System.out.println("Users构造方法...");
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", enabled=" + enabled +
                '}';
    }
}
