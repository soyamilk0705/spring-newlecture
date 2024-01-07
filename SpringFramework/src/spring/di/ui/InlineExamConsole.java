package spring.di.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import spring.di.entity.Exam;

@Component("console")
public class InlineExamConsole implements ExamConsole {
	
	/*
	 * 필드 방식을 이용한 injection은 생성자를 통해서 하기 때문에 기본 생성자 필요함
	 * -> overload 생성자가 없으면 자동으로 기본 생성자를 자바에서 만들어주기 때문에 오류가 나지 않지만
	 * 		overload 생성자가 존재하는 경우 기본 생성자가 만들어지지 않아 오류가 남 
	 */
	
	@Autowired		// required=false 로 설정하는 경우 Exam bean을 생성하는 코드가 존재하지 않아도 오류 발생 안함
	@Qualifier("exam")
	private Exam exam;			
	
	public InlineExamConsole() {
		System.out.println("constructor");
	}
	
	
	/*
	 * overload 생성자의 경우 파라미터에 똑같은 형식의 객체가 존재할 수 있기 때문에
	 * 해당 overload 생성자의 상단이 아닌 파라미터에 직접 사용함
	 */
	
	/*
	@Autowired
	public InlineExamConsole(@Qualifier("exam2") Exam exam) {
		System.out.println("overloaded constructor");
		this.exam = exam;
	}
	*/
	
	public InlineExamConsole(Exam exam) {
		System.out.println("overloaded constructor");
		this.exam = exam;
	}

	
	@Override
	public void print() {
		if (exam == null) {
			System.out.printf("total is %d, avg is %f\n", 0, 0.0);
		} else {
			System.out.printf("total is %d, avg is %f\n", exam.total(), exam.avg());
		}
		
	}
	
	
	@Override
	public void setExam(Exam exam) {
		System.out.println("setter");
		this.exam = exam;
		
	}

}


 