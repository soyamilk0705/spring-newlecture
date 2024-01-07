package spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.aop.entity.Exam;
import spring.aop.entity.NewlecExam;

public class Program {

	public static void main(String[] args) {
		
		// 2. spring을 이용한 AOP 구현
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("spring/aop/setting.xml");
		
//		ApplicationContext context = 
//			new AnnotationConfigApplicationContext(NewlecDIConfig.class);
		
		
		Exam exam = (Exam) context.getBean("exam");
		
		System.out.printf("total is %d\n", exam.total());
		System.out.printf("avg is %s\n", exam.avg());
		
		/*
		Exam exam = new NewlecExam(1, 1, 1, 1);
		
		1. Java를 이용한 AOP 구현
		부가적인 업무를 포함해서 Exam 업무를 시행 : 실제 업무 + 부가적인 업무 (ex 로그, 보안, 트랜잭션 등등)
		loader : 실제 업무를 하는 객체 로드
		interfaces : 업무의 인터페이스
		h : 부가적인 업무 시행 
		Exam proxy = (Exam) Proxy.newProxyInstance(NewlecExam.class.getClassLoader(), 
				new Class[] {Exam.class}, 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						long start = System.currentTimeMillis();
						
						// 실제 업무
						Object result = method.invoke(exam, args);
						
						long end = System.currentTimeMillis();
						String message = (end - start) + "ms 시간이 걸렸습니다.";
						
						System.out.println(message);
						
						return result;
					}
				}
			);
		*/

	}
	

}
