package aa.mydatas.jpa;

import aa.mydatas.jpa.entity.Account;
import aa.mydatas.jpa.repositories.AccountRepository;
import aa.slkenv.dataBase.anno.EnableSpringDataSource;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

@EnableJpaRepositories
@SpringBootApplication
@EnableSpringDataSource
@EntityScan("aa.spring.datas.commons.entity")
public class JpaApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(JpaApplication.class, args);
        System.out.println(ac.getBeanDefinitionNames()[0]);
        AccountRepository accountRepository = ac.getBean(AccountRepository.class);
        List<Account> accountList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int user_id = RandomUtils.nextInt(1, 10);
            Account account = new Account(user_id + "很行", user_id, String.valueOf(user_id * 1000));
            accountList.add(account);
        }
        List<Account> all = accountRepository.saveAll(accountList);
        for (Account account : all) {
            System.out.println(account.getId());
        }
        ac.close();
    }
}