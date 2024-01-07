package spring.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class LogBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// before advice 이기 때문에 target이 호출될 코드인 invocation.proceed(); 사용할 필요 없음
		System.out.println("앞에서 실행될 로직");
	}

}
