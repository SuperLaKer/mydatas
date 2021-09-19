package mydatas.sql.webs;

import aa.slkenv.dataBase.anno.EnableSpringDataSource;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import mydatas.sql.webs.KeyGen.GenNum;
import mydatas.sql.webs.entity.TheOrder;
import mydatas.sql.webs.mapper.TheOrderMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
// @Import({ShowSql.class})
@MapperScan(basePackages = {"mydatas.sql.webs.mapper"})
@EnableSpringDataSource(dataBaseName = "all_in_one")
public class WebsMain {

    // 0-20万用户
    static int userIdRange30 = 30000;
    // 商品信息 450万90万
    static int productIdRange5 = 10000;

    static List<Integer> list1 = new LinkedList<>();
    static List<TheOrder> orderList = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ac = SpringApplication.run(WebsMain.class, args);
        System.out.println(ac.getBeanDefinitionNames()[0]);
        List<Integer> queue = GenNum.getQueue(500000);
        // 100万
        for (int i = 0; i < 20; i++) {
            list1.clear();
            for (int j = 0; j < 10000; j++) {
                Integer integer = queue.remove(0);
                list1.add(integer);
            }
            createTheOrder(ac);
        }
        ac.close();
    }

    private static void createTheOrder(ConfigurableApplicationContext ac) {
        TheOrderMapper orderMapper = ac.getBean(TheOrderMapper.class);
        Random random = new Random();
        DefaultIdentifierGenerator generator = new DefaultIdentifierGenerator();

        orderList.clear();
        while (list1.size() > 0) {

            TheOrder theOrder = new TheOrder();

            // 联合唯一
            Integer onlyId = list1.remove(0);
            theOrder.setUnionA(onlyId);
            theOrder.setUnionB(onlyId + onlyId);

            long userId = random.nextInt(userIdRange30);
            long productId = random.nextInt(productIdRange5);

            theOrder.setId(generator.nextId(theOrder));
            theOrder.setUserId(userId);
            theOrder.setProductId(productId);
            theOrder.setIsDeleted(userId > 28000);

            theOrder.setProductNum(random.nextInt(50));
            int i1 = random.nextInt(299999999) + 299999999;

            theOrder.setCreateTime(new Timestamp(System.currentTimeMillis() - i1 * 100L));
            orderList.add(theOrder);
        }
        try {
            int i = orderMapper.insertBatch(orderList);
        } catch (Exception ignored) {
        }
    }
}
