package com.vietcombank.training.aop.log;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.vietcombank.training.aop.log.LoggerMessage.LoggerMessageBuilder;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
public class LogAspect {

	@Around(value = "com.vietcombank.training.aop.log.AppPointCuts.mainPointCut()")
	public Object caculateMethodTimeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		if (!logger.isDebugEnabled()) {
			return joinPoint.proceed();
		}
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
		String methodArgs = Stream.of(joinPoint.getArgs()).collect(Collectors.toList()).toString();
		long startTime = System.nanoTime();
		Object result = joinPoint.proceed();
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;

		LoggerMessageBuilder message = LoggerMessage.builder().className(className).methodName(methodName)
				.methodArgs(methodArgs).elapsedTimeInMillis(TimeUnit.NANOSECONDS.toMillis(elapsedTime))
				.elapsedTimeInMicros(TimeUnit.NANOSECONDS.toMicros(elapsedTime)).result(result);
		logger.debug(String.valueOf(message));
		return result;
	}
}
