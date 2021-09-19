package mydatas.tx.xOpen;

/**
 * 本地{
 *     transactionManager
 * }
 *
 * X/OPEN组织制定了分布式事务的处理标准：
 *
 *
 * 1、DTP模型{
 *     (distributed transaction process分布式事务处理模型)
 *     AP: application program，应用程序（开启事务、结束）
 *     RM: resource manager，数据库
 *     TM: transaction manager，监控事务进度负责事务的提交和回滚
 *     CRM: 通信
 * }
 * 2、XA接口：{
 *      RM有很多种MySQL、Oracle、SQLServer等, XA接口制定了RM和TM之间通信的规范
 *      1、XA_reg注册事务
 *      2、XA_start
 *      3、XA_close
 *
 *      JTA实现了XA接口可以和TM交互
 * }
 * 3、TM协调{
 *     TM：可以是一个jar包，TM之间通信共同提交、回滚
 *     TM: 独立的服务如seata，AP和RM注册到TM由一个TM统一管理
 *     无论是多个TM之间通信还是使用独立的TM，这两种方式都是刚性事务（同时成功、同时失败）
 * }
 *
 */
public class B_XOpen_DTP {
}
