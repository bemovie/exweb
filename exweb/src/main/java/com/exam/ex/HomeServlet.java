package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//다수의 서블릿들이 공유하는 데이터를 저장하는 공간
//1.서버(톰캣)에 저장
//	(1)요청 객체 (HttpServletRequest req를 말함)
//		- 요청 1개마다 1개의 요청객체 생성, 요청처리가 끝나면 소멸
//		- 하나의 요청을 처리하기 위해 사용되는 서블릿들간의 데이터 공유 (forward,include)
//	(2)세션 객체
//	(3)서블릿컨텍스트 객체
//2.클라이언트(웹브라우저)에 저장
//	(1)쿠키


@WebServlet("/home") 
public class HomeServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");//응답내용을 쓸 때 사용할 문자인코딩 방식 지정
		resp.setContentType("text/html");//응답내용의 데이터타입을 설정(웹브라우저에게 정보제공)
//		resp.setContentType("text/html; charset=UTF-8");//문자인코딩과 데이터타입을 한번에 설정 가능
		PrintWriter out = resp.getWriter();//응답객체에 내용을 쓸 수 있는 Writer 가져오기
//		out.println("Hello SERVLET"); //응답객체에 문자열을 출력
		//응답객체에 출력한 내용은 클라이언트(웹브라우저)로 전송된다
		
		out.println("<!DOCTYPE html>          ");
		out.println("<html>                   ");
		out.println("<head>                   ");
		out.println("<meta charset=\"UTF-8\">   ");
		out.println("<title>HOME</title>     ");
		out.println("</head>                  ");
		out.println("<body>                   ");
		out.println("	<h1>HOME</h1>");

		//SaverServlet에서 저장한 데이터를 읽어서 출력
		HttpSession session = req.getSession();
		String nickName = (String) session.getAttribute("nick");	//세션 객체에 "nick"라는 이름으로 저장된 데이터 읽기
		out.println("세션에 저장된 닉네임: " + nickName + "<br>");
		
		//SaverServlet에서 저장한 데이터를 읽어서 출력
		ServletContext context = getServletContext();
		String contextNick = (String) context.getAttribute("nick");	//서블릿컨텍스트 객체에 "nick"라는 이름으로 저장된 데이터 읽기
		out.println("서블릿컨텍스트에 저장된 닉네임: " + contextNick + "<br>");
		
//		req.getHeader(nickName);로 요청헤더에 있는 여러 정보에서 그 중 Cookie 값에서 nick 값만 꺼내오려면 복잡하다 Cookies 메소드 사용
		//요청헤더(Cookie)에 포함된 쿠키 값들을 읽기
		Cookie[] cookies = req.getCookies(); //쿠키 값들이 여러 개이므로 배열로 변수가 생성된다
		for (Cookie c : cookies) {
			if ("nick".equals(c.getName())) { //쿠키이름이 "nick"인 경우
				String v = URLDecoder.decode(c.getValue(), "UTF-8");
				out.println("쿠키에 저장된 닉네임: " + v + "<br>");
			}
		}
		
		out.println("</body>                  ");
		out.println("</html>                  ");
		
	}
}
