package aa.mydatas.jpa.learnSpring.queries.queryRelation;


import aa.mydatas.jpa.entity.Account;
import aa.mydatas.jpa.entity.Users;
import aa.mydatas.jpa.learnSpring.ApplicationStarter;
import aa.mydatas.jpa.repositories.AccountRepository;
import aa.mydatas.jpa.repositories.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;
import java.util.Optional;

/**
 * 多对一：应该可以看作一对一
 * Users  1--->*  Account
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationStarter.class)
public class UsersAccounts {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void getUsersByAccount() {
        Optional<Account> optional = accountRepository.findById(1L);
        assert optional.isPresent();
        Account account = optional.get();
        Users users = account.getUsers();
        System.out.println(users);
    }

    @Test
    public void getAccountsByUsers() {
        Optional<Users> optional = usersRepository.findById(1);
        assert optional.isPresent();
        Users users = optional.get();
        List<Account> accountList = users.getAccounts();
        for (Account account : accountList) {
            System.out.println(account.getAccountName());
        }
    }
}
