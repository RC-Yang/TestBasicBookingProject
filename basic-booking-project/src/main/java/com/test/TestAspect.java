package com.test;

import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
	
	Logger logger= LoggerFactory.getLogger(TestAspect.class);

	@Before("execution(* com.test..*(..))")
	public void beforeMethod(JoinPoint point) {
		//logger.info("即將執行"+point.getSignature().getName()+"：");
		logger.info("即將執行"+point.getSignature().toShortString()+"：");
	}
	
	@AfterReturning("execution(* com.test..*(..))")
	public void afterMethod(JoinPoint point) {
		logger.info("完成執行"+point.getSignature().toShortString()+"：");
	}
}
