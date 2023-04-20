package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿 URL패턴(주소,경로) 지정 규칙
//- 반드시 "/" 또는 "*." 으로 시작
//- "*"은 0개 이상의 모든 문자열과 일치

@WebServlet("/hi.do")
public class HiServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String x = req.getParameter("user");//요청처리
		
		resp.setCharacterEncoding("UTF-8");//응답내용을 쓸 때 사용할 문자인코딩 방식 지정
		resp.setContentType("text/html");//응답내용의 데이터타입을 설정(웹브라우저에게 정보제공)
		PrintWriter out = resp.getWriter();//응답객체에 내용을 쓸 수 있는 Writer 가져오기
		
		out.println("<!DOCTYPE html>          ");
		out.println("<html>                   ");
		out.println("<head>                   ");
		out.println("<meta charset=\"UTF-8\"> ");
		out.println("<title>HELLO</title>     ");
		out.println("</head>                  ");
		out.println("<body>                   ");
		out.println("<h1>" + x + "님 환영합니다</h1>");
		out.println("</body>                  ");
		out.println("</html>                  ");
		
//		String aval = req.getParameter("user");
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
////		out.println("Welcome!!");
//		
//		
//		
//		
//		
//		out.println(aval + "님 환영합니다");
		
	}

}

//"/hi.do?user=둘리" 로 요청을 보내면, 화면에 "둘리님 환영합니다"라고 출력되고,
//"/hi.do?user=고길동" 으로 요청을 보내면, 화면에 "고길동님 환영합니다"라고 출력되도록
//HiServlet의 내용을 변경하세요.



//다이나믹웹프로젝트(톰캣)이 실행 중인 상태에서
//	*.java 파일을 변경하면, 이클립스가 톰캣을 자동 재시작
// src/main/webapp 폴더의 정적 리소스(*.html, *.css...) 파일들을 변경하면
// 즉시 톰캣에 반영되므로 톰캣 재시작 없이 웹브라우저에서 새로고침만 필요
// web.xml 등 설정파일 변경시에는, 수동으로 톰캣 재시작 필요