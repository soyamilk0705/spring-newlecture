package com.newlecture.web.controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@MultipartConfig(
	fileSizeThreshold=1024*1024,		// 전송하는 파일 크기가 1MB 넘으면 자바에서 정한 임시 디렉토리를 쓴다.
	maxFileSize=1024*1024*50,			// 각각의 파일 최대 사이즈
	maxRequestSize=1024*1024*50*5		// 전체 파일 최대 사이즈
)
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp")
		.forward(request, response);
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		
		Part filePart = request.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		InputStream fis = filePart.getInputStream();
		
		String realPath = request.getServletContext().getRealPath("/upload");
		String filePath = realPath + File.separator + fileName;
		FileOutputStream fos = new FileOutputStream(filePath);
		
		byte[] buf = new byte[1024];
		int size = 0;
		while((size=fis.read(buf)) != -1) {
			fos.write(buf, 0, size); //0부터 size 갯수 만큼 반복되서 write 됨
		}
		
		fos.close();
		fis.close();
		
		boolean pub = false;
		
		if(isOpen != null)
			pub = true;
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setWriterId("newlec");
		
		NoticeService service = new NoticeService();
//		service.insertNotice(notice);
		
		response.sendRedirect("list");
		
	}
}
