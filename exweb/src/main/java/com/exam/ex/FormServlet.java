package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://eosin.mooo.com:8080/
//아이디 : 2023학번마지막2자리
//비밀번호: Kopo-23학번마지막2자리

@WebServlet("/form.do")
public class FormServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String pval = req.getParameter("prod");
		String fval = req.getParameter("fruit");
		String[] dval = req.getParameterValues("drink");
		
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
		out.println("</body>                  ");
		out.println("</html>                  ");
		out.println("<h1>요청주소 : " + req.getRequestURL() + "</h1>");
		out.println("<h1>요청주소 : " + req.getRequestURI() + "</h1>");
		out.println("<h1>애플리케이션 고유경로 : " + req.getContextPath() + "</h1>");
		out.println("<h1>요청방식 : " + req.getMethod() + "</h1>");
		out.println("<h1>User-Agent 요청헤더 : " + req.getHeader("User-Agent") + "</h1>");
		out.println("<h1>사용자IP주소 : " + req.getRemoteAddr() + "</h1>");
		
		
		out.println("<h1> 상품 : " + pval + "</h1>");
		out.println("<img src=\"https://api.lorem.space/image/" + pval + "?w=150&h=150\" />");
		if (fval != null)
		out.println("<h1> 과일 : " + fval + "</h1>");
		else
		out.println("<h1> 선택한 과일이 없습니다. </h1>");
		
		if (dval != null) { //파라미터가 전송되지 않는 경우 null
		out.println("<h1> 음료 : ");
			for (int i = 0; i < dval.length; i++) {
								//배열의 길이(음료를 하나 선택하면 1, 두개 선택하면 2, 세개 선택하면 3)
				out.println("[" + dval[i] + "]");
		
			}
				out.println("</h1>");
		}		
		else
		out.println("<h1> 선택한 음료가 없습니다. </h1>");
		out.println("</body>                  ");
		out.println("</html>                  ");

//		out.println("[" + dval[0] + "]");
//		out.println("[" + dval[1] + "]");
//		out.println("[" + dval[2] + "]");
//		
//		req.setCharacterEncoding("UTF-8");
//		String prodval = req.getParameter("prod");
//		String fruitval = req.getParameter("fruit");
//		String[] drinkval = req.getParameterValues("drink");
//		
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
//		
//		switch (prodval) {
//			case "shoes" :
//			out.println("<img src=\"https://api.lorem.space/image/shoes?w=150&h=150\">");
//			break;
//			case "watch" :
//			out.println("<img src=\"https://api.lorem.space/image/watch?w=150&h=150\">");	
//			break;
//			case "furniture" :
//			out.println("<img src=\"https://api.lorem.space/image/furniture?w=150&h=150\">");	
//			break;
//		}	
//		
//		out.println("</body>                  ");
//		out.println("</html>                  ");
//		out.println("<h1>" + fruitval +  "</h1>");
//		out.println("<h1>" + drinkval[0] + drinkval[1] + drinkval[2]  + "</h1>");
		
	}
	
}
