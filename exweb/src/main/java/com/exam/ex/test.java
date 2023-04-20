package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test.do")
public class test extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		
		PrintWriter out = resp.getWriter();
		
	}
}
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/test.do")
//public class test extends HttpServlet{
//
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String xval = req.getParameter("x");
//		String yval = req.getParameter("y");		
//	
//		resp.setCharacterEncoding("UTF-8");//응답내용을 쓸 때 사용할 문자인코딩 방식 지정
//		resp.setContentType("text/html");//응답내용의 데이터타입을 설정(웹브라우저에게 정보제공)
//		PrintWriter out = resp.getWriter();//응답객체에 내용을 쓸 수 있는 Writer 가져오기
//		out.println("<!DOCTYPE html>          ");
//		out.println("<html>                   ");
//		out.println("<head>                   ");
//		out.println("<meta charset=\"UTF-8\"> ");
//		out.println("<title>HELLO</title>     ");
//		out.println("</head>                  ");
//		out.println("<body>                   ");
//		out.println("<h1>x 파라미터값 : " + xval + "</h1>");
//		out.println("<h1>y 파라미터값 : " + yval + "</h1>");
//		out.println("</body>                  ");
//		out.println("</html>                  ");
//	}
//
//}
