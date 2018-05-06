package org.mycom.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * @Component는 스프링빈 인식을 위해 사용
 * @Aspect는 AOP 기능을 위해 추가
 * @Before는 해당 메소드 먼저 실행 후 target 메소드가 실행
 * execution은 Pointcut 지정하는 문법으로 org.mycom.service.MessageService로 시작하는 모든 메소드를 지정
 * 빈으로 등록을 위해 root-context.xml 에 component-scan 등록해야 함
 */

@Component
@Aspect
public class SampleAdvice {

	private static final Logger logger = LoggerFactory
			.getLogger(SampleAdvice.class);

	// 트랜잭션 test 위해 잠시 주석
	//@Before("execution(* org.mycom.service.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {

		logger.info("----------------------------");
		logger.info("----------------------------");
		logger.info(Arrays.toString(jp.getArgs()));

	}

	// 트랜잭션 test 위해 잠시 주석
	//@Around("execution(* org.mycom.service.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		// Throwable을 사용해야 함
		
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));

		// proceed는 실제 메소드를 호출함
		Object result = pjp.proceed();

		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + " : " + (endTime - startTime));
		logger.info("=================time log============================");

		// 반드시 리턴 타입은 Object로 선언해야함
		return result;
	}

}
