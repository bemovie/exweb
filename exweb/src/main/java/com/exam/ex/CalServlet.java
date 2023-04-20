package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc.do")
public class CalServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String xval = req.getParameter("x");
		String yval = req.getParameter("y");
		String opval = req.getParameter("op");
		
		double xnum = Double.parseDouble(xval); //실수문자열을 실수로 변환
		double ynum = Double.parseDouble(yval);
		double result = 0;
		
		String operator ="";
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>          ");
		out.println("<html>                   ");
		out.println("<head>                   ");
		out.println("<meta charset=\"UTF-8\"> ");
		out.println("<title>HELLO</title>     ");
		out.println("</head>                  ");
		out.println("<body>                   ");
//		out.println("<h1>" + xval + operator + yval + "=" + result + "</h1>");	
		if (opval.equals("plu")) {
			result = xnum + ynum;
			operator = "+"; 
			out.println("<h1>" + xval + operator + yval + "=" + result + "</h1>"); }
		else if (opval.equals("min")) {
			result = xnum - ynum;
			operator = "-";
			out.println("<h1>" + xval + operator + yval + "=" + result + "</h1>"); }
		else if (opval.equals("mul")) {
			result = xnum * ynum;
			operator = "*";
			out.println("<h1>" + xval + operator + yval + "=" + result + "</h1>"); }
		else if (opval.equals("div")) {
			if(ynum==0)
				out.println("<h1>0으로 나눌 수 없습니다~</h1>");
			else {
				result = xnum / ynum;
				operator = "/";
				out.println("<h1>" + xval + operator + yval + "=" + result + "</h1>"); }
		}
		out.println("</body>                  ");
		out.println("</html>                  ");
		
		
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		out.println("<!DOCTYPE html>          ");
//		out.println("<html>                   ");
//		out.println("<head>                   ");
//		out.println("<meta charset=\"UTF-8\"> ");
//		out.println("<title>HELLO</title>     ");
//		out.println("</head>                  ");
//		out.println("<body>                   ");
//		out.println("<h1>" + xval + operator + yval + "=" + result + "</h1>");
//		out.println("</body>                  ");
//		out.println("</html>                  ");
		
		
//		switch (opval) { 
//		case "plu" :
//			result = xnum + ynum;
//			operator = "+";
//			break;
//		case "min" :
//			result = xnum - ynum;
//			operator = "-";
//			break;
//		case "mul" :
//			result = xnum * ynum;
//			operator = "*";
//			break;
//		case "div" :
//			result = xnum / ynum;
//			operator = "/";
//			break;
//		default:
//			break;			
//		}	
		
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		out.println("<!DOCTYPE html>          ");
//		out.println("<html>                   ");
//		out.println("<head>                   ");
//		out.println("<meta charset=\"UTF-8\"> ");
//		out.println("<title>HELLO</title>     ");
//		out.println("</head>                  ");
//		out.println("<body>                   ");
//		out.println("<h1>" + xval + operator + yval + "=" + result + "</h1>");
//		out.println("</body>                  ");
//		out.println("</html>                  ");

//		switch (opval) { 
//			case "plu" :
//				result = xnum + ynum;
//				opval = "+";
//				break;
//			case "min" :
//				result = xnum - ynum;
//				opval = "-";
//				break;
//			case "mul" :
//				result = xnum * ynum;
//				opval = "*";
//				break;
//			case "div" :
//				result = xnum / ynum;
//				opval = "/";

//		}
		
		//op 파라미터값에 맞는 사칙연산을 수행
		//문자열 값을 동등비교하는 경우, == 연산자가 아닌 .equals() 메서드 사용
		//		"문자열1" == "문자열2" 		(X)
		//		"문자열1".equals("문자열2") (O)
		
//		숫자타입클래스명.parse숫자타입명("숫자문자열")
//		Integer.parseInt("123") == 123
//		Float.parseFloat("123.456") == 123.456
//		Double.parseDouble("123.456") == 123.456
				
//		double result = xnum + ynum;
	
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		out.println("<!DOCTYPE html>          ");
//		out.println("<html>                   ");
//		out.println("<head>                   ");
//		out.println("<meta charset=\"UTF-8\"> ");
//		out.println("<title>HELLO</title>     ");
//		out.println("</head>                  ");
//		out.println("<body>                   ");
//		out.println("<h1>" + xval + opval + yval + "=" + result + "</h1>");
//		out.println("</body>                  ");
//		out.println("</html>                  ");
		
		
//	req.setCharacterEncoding("UTF-8");
//	int xval = Integer.parseInt(req.getParameter("x"));
//	int yval = Integer.parseInt(req.getParameter("y"));
//	int sum = xval + yval;
//	
//	resp.setCharacterEncoding("UTF-8");
//	resp.setContentType("text/html");
//	PrintWriter out = resp.getWriter();
//	
//	out.println("<!DOCTYPE html>          ");
//	out.println("<html>                   ");
//	out.println("<head>                   ");
//	out.println("<meta charset=\"UTF-8\"> ");
//	out.println("<title>HELLO</title>     ");
//	out.println("</head>                  ");
//	out.println("<body>                   ");
//	out.println("<h1>"+ xval + "+" + yval + "=" + sum + "</h1>");
//	out.println("</body>                  ");
//	out.println("</html>                  ");
		
	}
	
}
