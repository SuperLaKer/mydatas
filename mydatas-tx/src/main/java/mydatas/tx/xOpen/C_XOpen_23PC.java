package mydatas.tx.xOpen;

/**
 * XOpen组织提供的处理分布式事务的两种方案{
 *  2PC、3PC方案都是强一致性方案
 *
 *     1、2PC方案2 phase commit{
 *         AP: commit(代理XA_COMMIT)
 *         TM: XA_Prepare(dataSource1)、XA_Prepare(dataSource2)
 *          RM: XA_CALLBACK_OK_FAIL、XA_CALLBACK_OK_FAIL
 *         RM: XA_COMMIT、XA_COMMIT
 *         基于XA接口
 *         2PC问题：
 *          1、TM和RM之间的交互采用的是同步阻塞的方案，
 *             TM必须等待RM回应，否则一直等待，prepare之后数据上锁
 *          2、一个commit成功、一个commit失败
 *     }
 *     2、3PC{
 *         canCommit、preCommit、doCommit
 *         超时自动提交容易导致一致性问题
 *         主要是解决了TM和RM之间同步等待的问题，引入了超时机制(释放事务，释放资源)
 *         仍然无法解决commit命令发出之后RM挂了导致的数据一致性的问题
 *     }
 *
 * }
 */
public class C_XOpen_23PC {
}
