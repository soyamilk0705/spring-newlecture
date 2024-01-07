package spring.di;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.di.entity.Exam;
import spring.di.entity.NewlecExam;
import spring.di.ui.ExamConsole;
import spring.di.ui.GridExamConsole;
import spring.di.ui.InlineExamConsole;
/**
 * 코드 흐름
 * Exam(interface) -> NewlecExam 객체 생성
 * ExamConsole(interface) -> GridExamConsole, InlineExamConsole 객체 생성
 * 
 * GridExamConsole, InlineExamConsole 객체에 NewlecExam 주입
 * 
 * Exam exam = new NewlecExam();
 * ExamConsole console = new GridExamConsole(exam);
 * ExamConsole console = new InlineExamConsole(exam);
 */
public class Program {

	public static void main(String[] args) {
		
		/* 스프링에게 지시하는 방법으로 코드 변경
		 * Exam exam = new NewlecExam();
		 * Exam exam = new NewlecExam(10, 10, 10, 10);
		 * ExamConsole console = new GridExamConsole();
		 * console.setExam(exam);
		 */
		
		// 1. XML 사용 방법
		// 지시서(setting.xml)를 읽어서 객체를 만들고 컨테이너에 담는데 컨테이너의 이름이 context임
//		ApplicationContext context = 
//				new ClassPathXmlApplicationContext("spring/di/setting.xml");
		
		// 2. Annotation 사용 방법
		ApplicationContext context = new AnnotationConfigApplicationContext(NewlecDIConfig.class);
		
		ExamConsole console = (ExamConsole) context.getBean("console");	// setting.xml 의 id
//		ExamConsole console = context.getBean(ExamConsole.class);	// 자료형을 넘겨줌으로써 해당 자료형의 객체 얻어줌 -> 형식 변환 할 필요 없어서 선호
		
//		Exam exam = context.getBean(Exam.class);
//		System.out.println(exam.toString());
		console.print();
		
		/*
		List<Exam> exams = new ArrayList<>();
		exams.add(new NewlecExam(1, 1, 1, 1));
		
		for(Exam e : exams) {
			System.out.println(e);
		}
		*/
		
//		List<Exam> exams = (List<Exam>) context.getBean("exams");
//		exams.add(new NewlecExam(1, 1, 1, 1));
				
//		for(Exam e : exams) {
//			System.out.println(e);
//		}

	}

}

/*
 * 빈 가져오는 방법
 * 1. 빈의 id 이용 : 형변환 필수
 * 		ExamConsole console = (ExamConsole) context.getBean("console");
 * 2. 클래스 자료형 이용
 * 		ExamConsole console = context.getBean(ExamConsole.class);
 */
 