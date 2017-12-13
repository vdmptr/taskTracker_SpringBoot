package vdm.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * debug logging of app
 * @author vdm
 */
@Aspect
@Component
public class AopLog {
    private static final Logger LOGGER = LogManager.getLogger("app.aop.allLog");

    @Pointcut("execution(* vdm.controllers.*.*(..))")
    private void controllerPoints(){}

    @Pointcut("execution(* vdm.rest.*.*(..))")
    private void servicePoints(){}

    @Pointcut("execution(* vdm.service.*.*(..))")
    private void restPoints(){}



    @Pointcut(value = "controllerPoints()"
            + " || servicePoints()"
            + " || restPoints()")
    public void allMethods(){}

    @Before("allMethods() && args(arg)")
    public void doBefore(JoinPoint jp, Object arg){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("call method: " + jp.getTarget()
                    .getClass()
                    .getSimpleName()
                    + "." + jp.getSignature().getName() + "()"
                    + " arguments: " + arg);
        }
    }

    @Before("execution(* vdm.controllers.*.*())"
          + " || execution(* vdm.service.*.*())"
          + " || execution(* vdm.rest.*.*())")
    public void doBefore(JoinPoint jp){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("call method: " + jp.getTarget()
                    .getClass()
                    .getSimpleName()
                    + "." + jp.getSignature().getName() + "()");
        }
    }

    @AfterReturning(pointcut = "allMethods()", returning = "returnValue")
    public void doAfter(JoinPoint jp, Object returnValue){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("return: " + jp.getTarget()
                    .getClass()
                    .getSimpleName()
                    + "." + jp.getSignature().getName()
                    + "() return:  " + returnValue);
        }
    }

    @AfterThrowing(pointcut = "allMethods()", throwing = "ex")
    public void doAfterException(JoinPoint jp, Throwable ex){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("exception:  " + jp.getTarget()
                    .getClass()
                    .getSimpleName()
                    + "." + jp.getSignature().getName()
                    + "() exception:  " + ex);
        }
     }
}
