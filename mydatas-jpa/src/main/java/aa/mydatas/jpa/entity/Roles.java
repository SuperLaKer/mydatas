package aa.mydatas.jpa.entity;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@SuppressWarnings("all")
@Entity
@Table(name = "roles")
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Column(name = "rolename")
    private String roleName;
    @Column(name = "roledescribe")
    private String roleDescribe;

    public Roles(String roleName, String roleDescribe) {
        this.roleName = roleName;
        this.roleDescribe = roleDescribe;
    }

    public Roles() {
        System.out.println("Roles构造方法...");
    }
}
