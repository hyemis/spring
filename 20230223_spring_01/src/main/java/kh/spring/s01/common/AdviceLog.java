package kh.spring.s01.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AdviceLog {

	@Pointcut("execution(public * kh.spring.s01..*Controller.*(..) )")
	public void controllerPointCut() {}
	
	@Pointcut("execution(public * kh.spring.s01..*Dao.*(..) )")
	public void daoPointCut() {}
	
	@Pointcut("execution(public * kh.spring.s01..*ServiceImp1.*(..) )")
	public void ServicePointCut() {}
	
	@Before("controllerPointCut()")
	public void beforeControllerPointCut(JoinPoint jp) {
		
		Object[] args = jp.getArgs();
		for(int i=0; i<args.length; i++) {
			System.out.println("args[" + i + "]: " + args[i]);
		}
		System.out.println("컨트롤러 모든 메소드가 호출되면 해당 메소드(target 메소드) 실행되기 "
				+ "전 Before  바로 이 메소드(controllerPointCut)를 실행하고 "
				+ "컨트롤러의 해당 메소드(target 메소드)로 가서 실행된다.");
	}
	
	
	@Around("daoPointCut()")
	public Object aroundDaoPointCut(ProceedingJoinPoint pjp) throws Throwable {
		Object returnObj = null;
		
		Object[] args = pjp.getArgs();
		for(int i=0; i<args.length; i++) {
			System.out.println("args[" + i + "]: " + args[i]);
		}
		System.out.println("dao 모든 메소드가 호출되면 해당 메소드(target 메소드) 실행되기 "
				+ "전 후에 바로 이 메소드(controllerPointCut)를 실행하고 "
				+ "dao의 해당 메소드(target 메소드)로 가서 실행된다.");
	
		
		// 타켓 메소드 실행
		returnObj = pjp.proceed();
		
		System.out.println("DAO Return:" + returnObj);
		
		return returnObj;
	}
	
	
	
	
	
	
}
