package aa.mydatas.jpa.learnSpring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class InterceptorAOP {

    private final String POINT_CUT = "execution(public * com.congrong.service.*.*(..))";

    @Pointcut("execution(public * org.hibernate.SessionFactory.openSession(..))")
    public void pointCut() {
    }


    @Around(value = "pointCut()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("@Around环绕通知开始执行");
        Object obj = null;
        try {
            obj = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("@Around环绕通知结束执行");
        return obj;
    }
}
