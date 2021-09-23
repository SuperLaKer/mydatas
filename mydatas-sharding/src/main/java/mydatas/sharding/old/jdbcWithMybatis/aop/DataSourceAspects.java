package mydatas.sharding.old.jdbcWithMybatis.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DataSourceAspects {

    @Pointcut("execution(* com.mysql.cj.jdbc.MysqlDataSource.getConnection(..))")
    public void getConnection() {
    }

    @Around("getConnection()")
    public Object aroundDeleteArticle(ProceedingJoinPoint proceedingJoinPoint) {
        //打印方法所有的参数列表
        Object[] args = proceedingJoinPoint.getArgs();
        Object retValue = null;
        System.out.println("xxxxxxxxxx");
        try {
            retValue = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return retValue;
    }
}
