package spring.aop.advice;

import org.springframework.aop.ThrowsAdvice;

public class LogAfterThrowingAdvice implements ThrowsAdvice {
	
	// 어떤 오류가 발생하냐에 따라서 인자값으로 들어갈 오류가 다양함
	public void afterThrowing(IllegalArgumentException e) throws Throwable{
		
		// 예외 발생 했을 시에만 실행됨(예외 발생 안하면 실행 안됨)
		System.out.println("예외가 발생하였습니다. : " + e.getMessage());
	}
}
