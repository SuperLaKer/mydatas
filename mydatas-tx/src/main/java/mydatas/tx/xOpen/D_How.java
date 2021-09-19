package mydatas.tx.xOpen;

/**
 * 分布式事务控制几种实施方案：
 * 1、对connection连接进行代理，控制事务
 * 2、逆向SQL方式控制分布式事务。每个事务场景都有与其对应的反向SQL
 * 3、TCC业务补偿。日志方式补偿、业务补偿方式
 * <p>
 * 1、connection{
 *      AP：commit
 *      RM1执行业务然后加入TM事务组
 *      RM2执行业务然后加入TM事务组
 *      AP：等TM通知commit、rollback
 *
 *      对AP和RM之间的JDBC连接代理，模仿2PC。强一致性
 * }
 *
 * 2、逆向SQL{
 *      原理：执行SQL之前，查询该SQL影响的"数据"，保存"数据"。当需要回滚的时候根据"数据"生成逆向SQL
 *      拦截SQL，解析SQL，生成数据，执行回滚SQL
 *
 *      适用于数据库，强一致性
 * }
 *
 * 3、TCC业务补偿{
 *     实现补偿接口：try(), confirm(), cancel()
 *     3.1、日志补偿机制{
 *          所有的AP都需要实现三个接口，有TM统一管理（执行confirm或cancel）
 *          try{
 *              记录日志;
 *              执行业务（本地事务）
 *          }
 *          confirm{
 *              删除日志;
 *          }
 *          cancel{
 *              加载日志;
 *              逆向SQL。
 *              强一致性：删除cancel方法，使用定时任务扫描try中的日志。没删除肯定需要cancel
 *          }
 *     }
 *     3.2、业务补偿{
 *
 *     }
 *
 * }
 */
public class D_How {
}















