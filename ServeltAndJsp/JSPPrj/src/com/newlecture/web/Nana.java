package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi")
public class Nana extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자에게 보내는 encoding 방식 설정
		response.setCharacterEncoding("UTF-8");
		
		// 브라우저(클라이언트)에서 해석하는 방식 설정
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String cnt_ = request.getParameter("cnt");
		
		int cnt = 100;
		
		if(cnt_ != null && !cnt_.equals("")) {
			cnt = Integer.parseInt(request.getParameter("cnt"));
		}
		
		for(int i=0; i<cnt; i++) {
			out.println("안녕 Servlet!!<br />");
		}
	}
}


/* url 설정 방법
1. web.xml
	: <servlet>, <servlet-mapping> 이용
2. annotation : 추천 -> 협업 시 분업화 하기 편리
	:@WebServlet()
*/