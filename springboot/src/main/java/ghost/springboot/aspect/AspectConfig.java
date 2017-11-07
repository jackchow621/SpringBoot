package ghost.springboot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class AspectConfig {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AspectConfig.class);
	
	@Pointcut("execution(public * ghost.springboot.controller.AccountController.*(..))")
	public void log(){
	}

	@Before("log()")
	public void doBefore(){
		LOGGER.info("切面before...");
		
	}
	
	@After("log()")
	public void doAfter(){
		LOGGER.info("切面after...");
		
	}
	
//	@AfterReturning("log()")
//    public void doAfterReturning() {
//        System.out.println("切面afterReturning执行了...");
//    }
//
//    @AfterThrowing("log()")
//    public void doAfterThrowing() {
//        System.out.println("切面afterThrowing执行了...");
//    }
//    
//    @Around("log()")
//    public Object around(ProceedingJoinPoint thisJoinPoint){
//        Object obj = null;
//        LOGGER.info("切面around before执行了...");
//        try {
//            thisJoinPoint.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace ();
//        }
//        LOGGER.info("切面around after执行了...");
//        return obj;
//    }
}
