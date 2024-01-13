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
		
		
		// ���
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
		// ���� ����
		else {
			// application.setAttribute("value", v);
			// application.setAttribute("op", op);
			
			// session.setAttribute("value", v);
			// session.setAttribute("op", op);
			
			// ��Ű�� url�� ����� �� �ִ� ���ڿ��� ������
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			
			// ��� ������ ��û�� ������ ��Ű ���޵�
			// valueCookie.setPath("/");
			// opCookie.setPath("/");
			
			// add��� �ּҸ� ��û�� ���� ��Ű ���޵�
			valueCookie.setPath("/calc2");
			opCookie.setPath("/calc2");
			
			// �ʴ���
			valueCookie.setMaxAge(24*60*60);
			
			
			// response Header �� �ɾ����� ���޵�
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html");
		}
		
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