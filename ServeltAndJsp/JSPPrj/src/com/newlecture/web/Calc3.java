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
			// ��Ű ����
			expCookie.setMaxAge(0);
		}
		
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");
	}
}


/*
 * Session�� �ֿ� �Լ�
 * void setAttribute(String name, Object value)
 * : ������ �̸����� ��ü�� ����
 * 
 * Object getAttribute(String name)
 * : ������ �̸��� ��ü�� ��ȯ
 * 
 * void invalidate()
 * : ���ǿ��� ���Ǵ� ��ü���� �ٷ� ����
 * 
 * vodi setMaxInactivateInterval(int interval)
 * : ���� Ÿ�Ӿƿ��� ����(��)�� ����
 * 
 * boolean isNew()
 * : ������ ���� �����Ǿ������� Ȯ��
 * 
 * Long getCreateTime()
 * : ������ ���۵� �ð��� ��ȯ, 1970�� 1�� 1���� �������� �ϴ� �и���
 * 
 * long getLastAccessedTime()
 * : ������ ��û �ð�, 1970�� 1�� 1���� �������� �ϴ� �и���
 */