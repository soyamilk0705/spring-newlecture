package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.MappingMatch;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		
		// 계산
		if(op.equals("=")) {
			// int x = (Integer) application.getAttribute("value");
			// int x = (Integer) session.getAttribute("value");
			int x = 0;
			
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			
			int y = v;
			// String operator = (String) application.getAttribute("op");
			// String operator = (String) session.getAttribute("op");
					
			String operator = "";
			
			for(Cookie c : cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			}
			
			
			int result = 0;
			
			if(operator.equals("+")) {
				result = x+y;
			} else {
				result = x-y;
			}
			
			response.getWriter().printf("result is %d\n", result);
			
		} 
		// 값을 저장
		else {
			// application.setAttribute("value", v);
			// application.setAttribute("op", op);
			
			// session.setAttribute("value", v);
			// session.setAttribute("op", op);
			
			// 쿠키는 url에 사용할 수 있는 문자열만 저장함
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			
			// 모든 페이지 요청할 때마다 쿠키 전달됨
			// valueCookie.setPath("/");
			// opCookie.setPath("/");
			
			// add라는 주소를 요청할 때만 쿠키 전달됨
			valueCookie.setPath("/calc2");
			opCookie.setPath("/calc2");
			
			// 초단위
			valueCookie.setMaxAge(24*60*60);
			
			
			// response Header 에 심어져서 전달됨
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html");
		}
		
	}
	
}


/*
 * Session의 주요 함수
 * void setAttribute(String name, Object value)
 * : 지정된 이름으로 객체를 설정
 * 
 * Object getAttribute(String name)
 * : 지정한 이름의 객체를 반환
 * 
 * void invalidate()
 * : 세션에서 사용되는 객체들을 바로 해제
 * 
 * vodi setMaxInactivateInterval(int interval)
 * : 세션 타임아웃을 정수(초)로 설정
 * 
 * boolean isNew()
 * : 세션이 새로 생성되었는지를 확인
 * 
 * Long getCreateTime()
 * : 세션이 시작된 시간을 반환, 1970년 1월 1일을 시작으로 하는 밀리초
 * 
 * long getLastAccessedTime()
 * : 마지막 요청 시간, 1970년 1월 1일을 시작으로 하는 밀리초
 */
