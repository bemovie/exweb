package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//회원목록 화면에 "회원추가" 링크를 추가하고,
//	그 링크를 클릭하면, 회원정보를 입력하는 폼 화면으로 이동하도록
//	MemListServlet 클래스를 변경하세요.

//회원정보 추가 후에 "회원목록으로 이동" 링크를 추가하고,
//	그 링크를 클릭하면, 회원목록 화면으로 이동하도록
//	MemAddServlet 클래스를 변경하세요.

//회원목록의 각 회원정보 옆에 "삭제" 링크를 출력하고,
//	링크를 클릭하면 해당 회원이 삭제되도록
//	MemListServlet 클래스를 변경하세요.

//삭제 링크가 버튼 모양이면 더 좋을 것 같아요.


@WebServlet("/member/list.do")
public class MemListServlet extends HttpServlet{
	
//	생성자, init, 초기화 블록 : tomcat 실행시 자동 실행
	
//	{
//		//애플리케이션에 JDBC 사용 전에 최초 1번은 JDBC 드라이버 클래스를 메모리에 로드 필요 
//				try {
//					Class.forName("oracle.jdbc.OracleDriver");
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
//	}
	
	String url ="jdbc:oracle:thin:@localhost:1521:xe"; //데이터베이스 서버 주소
	String user ="web"; //데이터베이스 접속 아이디
	String password ="web01"; //데이터베이스 접속 비밀번호
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
				// ~ 여기부터 ~ 응답객체에 출력 설정 [형식 + 파이프 라인 생성]
				resp.setCharacterEncoding("UTF-8"); //응답객체 인코딩 설정
				resp.setContentType("text/html"); //응답객체 타입 설정
				PrintWriter out = resp.getWriter(); //응답객체 출력 파이프 생성
		
				out.println("<!DOCTYPE html>          ");
				out.println("<html>                   ");
				out.println("<head>                   ");
				out.println("<meta charset=\"UTF-8\"> ");
				out.println("<title>회원관리</title>     ");
				out.println("<style> a{text-decoration-line: none;} a:visited {color:red;} a:hover{color:pink;}");
				out.println("</style>");
				out.println("</head>                  ");
				out.println("<body>                   ");			
				// ~ 여기까지는 한번만 있어도 되는 부분 ~ , DB 코드와 분리하기 위해 그 위로 올린다
				out.println("<h1>회원목록</h1>");
				out.println("<button><a href=\"" + req.getContextPath() + "/member/addform.do\">회원추가</a></button>");
				out.println("<button><a href=\"" + req.getContextPath() + "/member/delform.do\">회원삭제</a></button>");
				
		String sql = "SELECT mem_id, mem_pass, mem_name, mem_point FROM member";
		
		try( 
				//지정한 데이터베이스에 접속(로그인)
				Connection conn = DriverManager.getConnection(url, user, password);
				//해당 연결을 통해 실행할 SQL문을 담은 명령문 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);	
				//SQL문 실행 (SELECT 문 실행은 executeQuery()메서드 사용)
				ResultSet rs = pstmt.executeQuery(); //반환값은 조회 결과 레코드(row)들
				) {

			
				//처음 ResultSet 객체는 첫 레코드(row) 이전을 가리키고 있음
				// .next() 메서드를 실행하면 다음 레코드를 가리키게 된다
				// .next() 메서드는 다음 레코드가 있으면 true를 반환하고, 없으면 false를 반환한다
			while (rs.next()) {
				//rs.next();
				//컬럼값의 데이터타입에 따라서 get타입("컬럼명") 메서드를 사용하여 컬럼값 읽기
				String memId = rs.getString("mem_id"); //현재 가리키는 레코드(row)의 "mem_id"컬럼값 읽기
				String memPass = rs.getString("mem_pass"); //현재 가리키는 레코드(row)의 "mem_pass"컬럼값 읽기
				String memName = rs.getString("mem_name"); //현재 가리키는 레코드(row)의 "mem_name"컬럼값 읽기
				int memPoint = rs.getInt("mem_point"); //현재 가리키는 레코드(row)의 "mem_point"컬럼값 읽기
//				System.out.println(memId + ":" + memPass + ":" + memName + ":" + memPoint);
				System.out.println(memId + ":" + memPass + ":" + memName + ":" + memPoint);
				
				// ~ 출력이 반복되는 부분 , console에 출력되는 부분을 웹 브라우저 화면에 출력 ~
				out.print("<p>" + memId + ":" + memPass + ":" + memName + ":" + memPoint);
//				out.println("<form action=\"" + req.getContextPath() + "/member/del.do\" method=\"post\">");
//				out.println("<button type=\"button\" onclick=\"location.href='\" + req.getContextPath() + \"/member/del.do?memId=memId">삭제</button>");
//				out.println(" <button><a href=\"" + req.getContextPath() + "/member/del.do?memId=" + memId + "\">삭제</a></button>");
				out.println(" <a href=\"" + req.getContextPath() + "/member/del.do?memId=" + memId + "\"><button type='button'>삭제</button></a>");
//				out.println("<input type=\"submit\" />");
//				out.println("</form>                  ");
				out.println("</p>");
			}
				// ~ 여기까지 ~
				
			
//			conn.getAutoCommit(); // 자동 commit 상태 확인 (true=자동, false=수동)
//			conn.setAutoCommit(false); // 자동 commit 상태 setting(변경)
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
				// ~ 여기부터 ~
				out.println("</body>                  ");
				out.println("</html>                  ");
				// ~ 아랫부분도 한번만 있으면 되서 catch문 아래쪽으로 이동 ~

		
//		회원목록이 이클립스 콘솔이 아닌
//		웹 브라우저 화면에 출력되도록 MemListServlet을 변경하세요.
		
			
	}
	
}
