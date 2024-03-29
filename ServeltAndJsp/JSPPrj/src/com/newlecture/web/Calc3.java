package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
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

@WebServlet("/calc3")
public class Calc3 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		String exp = "";
		
		if(cookies != null) {
			for(Cookie c: cookies) {
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
			}
		}
		
		if(operator != null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
		else if(operator != null && operator.equals("C")) {
			exp = "";
		}
		else {
			exp += (value == null) ? "" : value;
			exp += (operator == null) ? "" : operator;
			exp += (dot == null) ? "" : dot;
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		
		if(operator != null && operator.equals("C")) {
			// 쿠키 삭제
			expCookie.setMaxAge(0);
		}
		
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");
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
