package com.vietcombank.training.aop.log;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppPointCuts {

	@Pointcut("winth(@org.springframework.web.bind.annotation.RestController *)")
	public void employeeControllerPointCut() {
		
	}
	@Pointcut("winth(@org.springframework.stereotype.Service *)")
	public void employeeServicePointCut() {
		
	}
	@Pointcut("winth(@org.springframework.stereotype.Respository *)")
	public void employeeResponsestoryPointCut() {
		
	}
	
	@Pointcut("execution(* com.vietcombank.training..*(..))")
	public void appPointCut() {
		
	}
	
	@Pointcut("appPointcut() && (employeeControllerPointCut()||employeeServicePointCut()||RespositoryControllerPointCut())")
	public void mainPointCut() {
		
	}
}
