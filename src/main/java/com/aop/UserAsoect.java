package com.aop;

import java.text.MessageFormat;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;    
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class UserAsoect {
	
	private  Logger logger =Logger.getLogger(getClass());
	
	//配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
//	@Pointcut("execution(public * com.Controller..*.*(..))")
//	public void allfunction(){}
//	
//	@Pointcut("execution(public * com.dao.impl..*.*(..))")
//	public void Userdao(){}
	
	@Before("execution(public * com.dao.impl..*.*(..))")
	public void test(JoinPoint joinPoint){
		System.out.println(joinPoint.getTarget().toString());
		System.out.println(joinPoint.getArgs().toString());
		System.out.println(joinPoint.getSignature().getName());
		String methodName = joinPoint.getSignature().getName();
		String message = MessageFormat.format("method:{0}", methodName);
		logger.info(message);
	}
	
	
	
}
