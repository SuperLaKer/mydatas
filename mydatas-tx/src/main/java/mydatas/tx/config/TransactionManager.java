package mydatas.tx.config;


import java.sql.SQLException;

/**
 * 事务管理器：开启事务、提交事务、回滚事务
 *
 * 事务的传播行为：propagation
 * 传播行为：一个方法调用栈中有多个@Transactional
 *
 * required: 默认的，存在事务就加入不存在事务就开启事务。
 * supports：使用之前的事务，如果没有就不使用事务。
 * mandatory：使用当前事务，如果没有抛异常。不能用在方法调用链第一个方法
 * requires_new：新建事务，如果当前存在事务就把当前事务挂起。大事务套小事务
 * not supported：以非事务的方式运行，如果存在当前事务，就把当前事务挂起
 * never：以非事务的方式运行，如果存在当前事务就抛出异常
 * nested：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作
 *
 *
 */
public class TransactionManager {


    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    public void beginTransaction() throws SQLException {
        connectionUtils.getThreadConnection().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connectionUtils.getThreadConnection().commit();
    }

    public void rollback() throws SQLException {
        connectionUtils.getThreadConnection().rollback();
    }

    public void release(){
        connectionUtils.removeConnection();
    }
}
