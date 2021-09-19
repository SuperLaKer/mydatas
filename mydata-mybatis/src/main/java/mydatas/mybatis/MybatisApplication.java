package mydatas.mybatis;

import aa.slkenv.dataBase.ShowSql;
import aa.slkenv.dataBase.anno.EnableSpringDataSource;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import mydatas.mybatis.entity2.LearnMybatisPlus;
import mydatas.mybatis.entity2.LearnMybatisPlusVo;
import mydatas.mybatis.mapper.LearnMybatisPlusMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableSpringDataSource
@Import({ShowSql.class, MybatisSelfConfiguration.class})
@MapperScan(basePackages = {"mydatas.mybatis.mapper"})
public class MybatisApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(MybatisApplication.class, args);
        System.out.println(ac.getBeanDefinitionNames()[0]);
        try {
            LearnMybatisPlusMapper mapper = ac.getBean(LearnMybatisPlusMapper.class);
            List<LearnMybatisPlus> learnMybatisPluses = mapper.selectAllIgnore();
            System.out.println(learnMybatisPluses.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ac.close();
        }
    }

    private static void alertTenlentId(ConfigurableApplicationContext ac) {
        Random random = new Random();
        LearnMybatisPlusMapper mapper = ac.getBean(LearnMybatisPlusMapper.class);
        List<LearnMybatisPlus> learnMybatisPluses = mapper.selectAll();
        for (LearnMybatisPlus learnMybatisPlus : learnMybatisPluses) {
            learnMybatisPlus.setTenantId(random.nextInt(5));
            mapper.updateById(learnMybatisPlus);
        }
    }


    /**
     * 乐观锁的实现
     */
    private static void optimisticLocker(ConfigurableApplicationContext ac) throws InterruptedException {
        // 乐观锁: 先查询然后更新，并发量低
        // 模拟：查询 ---> 睡眠（修改版本）---> 更新数据
        LearnMybatisPlusMapper mapper = ac.getBean(LearnMybatisPlusMapper.class);
        LearnMybatisPlus obj = mapper.selectById(1429810865398870017L);
        System.out.println(obj.getName());
        obj.setName("小明66");
        TimeUnit.SECONDS.sleep(5);
        int i = mapper.updateById(obj);
        System.out.println(i == 1);
    }

    /**
     * 分页查询
     *
     * @param ac ConfigurableApplicationContext
     */
    private static void queryPageable(ConfigurableApplicationContext ac) {
        LearnMybatisPlusMapper learnMapper = ac.getBean(LearnMybatisPlusMapper.class);
        Page<LearnMybatisPlusVo> page = new Page<>(1, 10);
        List<LearnMybatisPlusVo> plusVos = learnMapper.selectPageVo(page, 1);
        plusVos.forEach(item -> System.out.println(item.getId() + ": " + item.getState()));
    }

    /**
     * 生成几条数据
     *
     * @param ac ConfigurableApplicationContext
     */
    private static void insertToLearnMybatisPlusTable(ConfigurableApplicationContext ac) {
        LearnMybatisPlusMapper learnMybatisPlusMapper = ac.getBean(LearnMybatisPlusMapper.class);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {

            LearnMybatisPlus learnMybatisPlus =
                    new LearnMybatisPlus(new Date(System.currentTimeMillis() + i), "小明" + i, random.nextInt(2));
            int success = learnMybatisPlusMapper.insert(learnMybatisPlus);
            System.out.println(success != 0);
            System.out.println(learnMybatisPlus.getId());
        }
    }
}