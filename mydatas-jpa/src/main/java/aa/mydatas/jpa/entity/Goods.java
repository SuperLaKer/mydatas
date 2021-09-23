package aa.mydatas.jpa.entity;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OptimisticLock;

import javax.persistence.*;

/**
 * （某些）数据更新时，version发生变化
 * store库存更新时，version发生变化
 */
@Data
@ToString
@Entity
@SuppressWarnings("all")
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

    @OptimisticLock(excluded = true)  // 不会触发版本控制
    @Column(name = "goods_name")
    private String name;

    @Column(name = "goods_num")
    private Integer store;

    @OptimisticLock(excluded = true)
    @Column(name = "goods_desc")
    private String desc;

    @OptimisticLock(excluded = true)
    private String price;

    @Version
    private Integer version;

    public Goods() {
    }

    public Goods(String name, Integer store, String desc, String price) {
        this.name = name;
        this.store = store;
        this.desc = desc;
        this.price = price;
    }
}
