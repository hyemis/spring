package kh.spring.s01.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AdviceLog {
	
	
	private static final Logger logger = LoggerFactory.getLogger(AdviceLog.class);

	@Pointcut("execution(public * kh.spring.s01..*Controller.*(..) )")
	public void controllerPointCut() {}
	
	@Pointcut("execution(public * kh.spring.s01..*Dao.*(..) )")
	public void daoPointCut() {}
	
	@Pointcut("execution(public * kh.spring.s01..*ServiceImp1.*(..) )")
	public void ServiceImp1PointCut() {}
	
	@Around("controllerPointCut()")
	public Object aroundControllerPointCut(ProceedingJoinPoint pjp) throws Throwable {
		
		Object returnObj = null;
		Object[] args = pjp.getArgs();
		
		for(int i=0; i<args.length; i++) {
			logger.info("@>- args[" + i + "]: " + args[i]);
		}
		
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		
		
		returnObj=pjp.proceed();
		stopwatch.stop();
//		System.out.println("컨트롤러 모든 메소드가 호출되면 해당 메소드(target 메소드) 실행되기 "
//				+ "전 Before  바로 이 메소드(controllerPointCut)를 실행하고 "
//				+ "컨트롤러의 해당 메소드(target 메소드)로 가서 실행된다.");
		logger.info("@>- Ctrl Return["+stopwatch.getTotalTimeMillis()+"]: " + returnObj);

		return returnObj;
	}
	
	
	@Around("daoPointCut()")
	public Object aroundDaoPointCut(ProceedingJoinPoint pjp) throws Throwable {
		Object returnObj = null;
		
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			logger.info("@@@>-- args[" + i + "]: " + args[i]);
		}
	
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		
		// 타켓 메소드 실행
		returnObj = pjp.proceed();
		stopwatch.stop();
		logger.info("@@@>-- Dao Return["+stopwatch.getTotalTimeMillis()+"]: " + returnObj);
		
		return returnObj;
	}
	
	@Around("ServiceImp1PointCut()")
	public Object aroundServicePointCut(ProceedingJoinPoint pjp) throws Throwable {
		Object returnObj = null;
		
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			logger.info("@@>-- args[" + i + "]: " + args[i]);
		}
	
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		
		// 타켓 메소드 실행
		returnObj = pjp.proceed();
		stopwatch.stop();
		logger.info("@@>-- Srvc Return["+stopwatch.getTotalTimeMillis()+"]: " + returnObj);
		
		return returnObj;
	}

	
	
	
	
	
}
