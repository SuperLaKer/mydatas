package mydatas.tx.spring;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring事务的实现
 * TransactionManager封装了几个方法
 */
@Configuration
@EnableTransactionManagement
public class SpringTx {
    public static void main(String[] args) {

    }
}
