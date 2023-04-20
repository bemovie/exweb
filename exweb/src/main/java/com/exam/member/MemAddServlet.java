package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add.do")
public class MemAddServlet extends HttpServlet{
	
//	{
//	//애플리케이션에 JDBC 사용 전에 최초 1번은 JDBC 드라이버 클래스를 메모리에 로드 필요 
//			try {
//				Class.forName("oracle.jdbc.OracleDriver");
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//}	
	
	String url ="jdbc:oracle:thin:@localhost:1521:xe"; //데이터베이스 서버 주소
	String user ="web"; //데이터베이스 접속 아이디
	String password ="web01"; //데이터베이스 접속 비밀번호
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String memIdv = req.getParameter("memId");
		String memPassv = req.getParameter("memPass");
		String memNamev = req.getParameter("memName");
		int memPointv = Integer.parseInt(req.getParameter("memPoint"));
		
		
//		String memId = "a004";
//		String memPass = "1234";
//		String memName = "호랭이";
//		int memPoint = 90;
		
		String sql = "INSERT INTO member (mem_id, mem_pass, mem_name, mem_point) "
				+ " VALUES ( ?, ?, ?, ? )";

		
//try() 사용하지 않을 경우의 close 방식
//		Connection conn=null;			//try 안의 지역 변수를 밖으로 빼냄
//		PreparedStatement pstmt=null;	//try 안의 지역 변수를 밖으로 빼냄

		int n = 0;
		//try () 내부에 선언된 변수의 값은
		//try-catch 블럭의 실행이 완료된 후 자동으로 close() 메서드 실행
		try( 
				//지정한 데이터베이스에 접속(로그인)
				Connection conn = DriverManager.getConnection(url, user, password);
				//해당 연결을 통해 실행할 SQL문을 담은 명령문 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);	
				) {
			
			//pstmt 명령문 객체에 담겨 있는 SQL문의 ?에 값을 채워넣기
			//채워넣는 값의 타입에 따라서 set타입명() 메서드 사용
			pstmt.setString(1, memIdv); //1번째 ?에 memId 값을 넣기
			pstmt.setString(2, memPassv); //2번째 ?에 memPass 값을 넣기
			pstmt.setString(3, memNamev); //3번째 ?에 memName값을 넣기
			pstmt.setInt(4, memPointv); //4번째 ?에 memPoint 값을 넣기
			
			//SQL문 실행 (INSERT, UPDATE, DELETE 문 실행은 executeUpdate()메서드 사용)
			
			n = pstmt.executeUpdate(); //반환값은 SQL문 실행으로 영향받은 레코드(row) 수
			System.out.println( n + "명의 회원 추가 성공");
			
		
//			resp.setCharacterEncoding("UTF-8");
//			resp.setContentType("text/html");
//			PrintWriter out = resp.getWriter();
//			out.println("<!DOCTYPE html>          ");
//			out.println("<html>                   ");
//			out.println("<head>                   ");
//			out.println("<meta charset=\"UTF-8\"> ");
//			out.println("<title>HELLO</title>     ");
//			out.println("</head>                  ");
//			out.println("<body>                   ");		
//			out.println("<h1>" + n + "명의 회원 추가</h1>");
//			out.println("</body>                  ");
//			out.println("</html>                  ");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//회원목록 출력
		//MemListServlet 실행!
		//forward : 요청객체와 응답객체를 전달하면서 다른 서블릿을 실행
		//		현재 서블릿에서는 더 이상 응답을 출력하지 않는다. (화면처리(print)는 더 이상 관여하지 못한다.)
//		req.getRequestDispatcher("/member/list.do").forward(req, resp); //req.getRequestDispatcher("이동할주소")
		//include : 요청객체와 응답객체를 전달하면서 다른 서블릿을 실행
		//		현재 서블릿의 출력 내용 중간에 지정한 서블릿의 출력 내용을 포함
//		req.getRequestDispatcher("/member/list.do").include(req, resp);
		//redirect : 지정한 주소로 이동하라는 명령을 담은 응답을 웹브라우저에게 전송
		resp.sendRedirect(req.getContextPath() + "/member/list.do");
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>          ");
		out.println("<html>                   ");
		out.println("<head>                   ");
		out.println("<meta charset=\"UTF-8\"> ");
		out.println("<title>회원관리</title>     ");
//		out.println("<style> a{text-decoration-line: none;} a:visited {color:blue;} a:hover{color:pink;}");
//		out.println("</style>");
		out.println("</head>                  ");
		out.println("<body>                   ");			
		out.println("<h1>" + n + "명의 회원 추가 성공</h1>");
		out.println("<a href=\"" + req.getContextPath() + "/member/list.do\">회원목록</a>");
		out.println("</body>                  ");
		out.println("</html>                  ");
			
	
//try() 사용하지 않을 경우의 close 방식		
//		finally {
//			try {
//				if(pstmt!=null)
//				pstmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} //명령문 객체가 사용하던 자원 반납
//			try {
//				if(conn!=null)
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} //데이터베이스와 연결 종료	
//		}
	
	
	}
	
}
