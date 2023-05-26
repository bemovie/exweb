package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

//2023-05-26
//로그인하지 않은 상태에서 회원목록 페이지에 접속하면,
//로그인 화면으로 이동하도록 구현

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
	
	
//	private MemberDao memberDao = new MemberDaoJdbc();
	private MemberDao memberDao = new MemberDaoBatis(); 
	//만약 MemberDao가 아닌 MemberDaoJdbc로 받았으면 고쳐줘야됨. 그러나 MemberDao 인터페이스로 받았으므로 고칠 필요x
	
	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		//db에서 회원목록 가져오려면 시간 오래걸림, 가져오기 "전에" 로그인 되었나 안되었나 검사
		//list.do를 접속할때, 직접 치거나 link 통해서 접속하므로 doGet
		
		//요청보낸 사용자의 세션을 가져와서 
		HttpSession session = req.getSession();
		
		//세션에 로그인 정보를 꺼내와서
		MemberVo vo = (MemberVo) session.getAttribute("loginUser"); 
		//세션에 로그인한 사용자정보 꺼내옴, 
		//빨간줄 뜨는 이유는 MemberVo 타입이라는 보장이 없어서 => 하지만, 우리는 알고 있으므로 강제 형변환해줌
		
		//로그인 정보가 없다면,
		if (vo==null) { //로그인 실패시, 로그인 화면으로 이동
		
		//로그인 페이지로 이동
			resp.sendRedirect(req.getContextPath() + "/member/login.do"); // 로그인 화면으로 이동
			return; 
			// 아래쪽 코드 else로 감싸도 되지만, 보기가 안 좋음(들여쓰기도 해야되고)
			// => if가 실행되면 아래쪽 코드는 실행 안 되게 해야함 
			// => method 함수의 실행을 그만두고 돌아가라	=> return;
		}
		
		List<MemberVo> list = memberDao.selectMemberList(); //db에서 회원목록 가져옴
		
		req.setAttribute("memberList", list); //가져온 회원목록을 요청객체에 저장
		
		req.getRequestDispatcher("/WEB-INF/views/member/memList.jsp").forward(req, resp); // memList.jsp로 forward
		
//				// ~ 여기부터 ~ 응답객체에 출력 설정 [형식 + 파이프 라인 생성]
//				resp.setCharacterEncoding("UTF-8"); //응답객체 인코딩 설정
//				resp.setContentType("text/html"); //응답객체 타입 설정
//				PrintWriter out = resp.getWriter(); //응답객체 출력 파이프 생성
//		
//				out.println("<!DOCTYPE html>          ");
//				out.println("<html>                   ");
//				out.println("<head>                   ");
//				out.println("<meta charset=\"UTF-8\"> ");
//				out.println("<title>회원관리</title>     ");
//				out.println("<style> a{text-decoration-line: none;} a:visited {color:red;} a:hover{color:pink;}");
//				out.println("</style>");
//				out.println("</head>                  ");
//				out.println("<body>                   ");			
//				// ~ 여기까지는 한번만 있어도 되는 부분 ~ , DB 코드와 분리하기 위해 그 위로 올린다
//				out.println("<h1>회원목록</h1>");
//				out.println("<button><a href=\"" + req.getContextPath() + "/member/addform.do\">회원추가</a></button>");
//				out.println("<button><a href=\"" + req.getContextPath() + "/member/delform.do\">회원삭제</a></button>");
//				
//				
//		
//				for (MemberVo vo : list) {
//					
//				
//		
////				System.out.println(memId + ":" + memPass + ":" + memName + ":" + memPoint);
////				System.out.println(memId + ":" + memPass + ":" + memName + ":" + memPoint);
//				
//				// ~ 출력이 반복되는 부분 , console에 출력되는 부분을 웹 브라우저 화면에 출력 ~
//				out.print("<p>" + vo.getMemId() + ":" + vo.getMemPass() + ":" + vo.getMemName() + ":" + vo.getMemPoint());
//		//		out.println("<form action=\"" + req.getContextPath() + "/member/del.do\" method=\"post\">");
//		//		out.println("<button type=\"button\" onclick=\"location.href='\" + req.getContextPath() + \"/member/del.do?memId=memId">삭제</button>");
//		//		out.println(" <button><a href=\"" + req.getContextPath() + "/member/del.do?memId=" + memId + "\">삭제</a></button>");
//				out.println(" <a href=\"" + req.getContextPath() + "/member/del.do?memId=" + vo.getMemId() + "\"><button type='button'>삭제</button></a>");
//		//		out.println("<input type=\"submit\" />");
//		//		out.println("</form>                  ");
//				out.println("</p>");
//		
//				}
//				
//				// ~ 여기부터 ~
//				out.println("</body>                  ");
//				out.println("</html>                  ");
//				// ~ 아랫부분도 한번만 있으면 되서 catch문 아래쪽으로 이동 ~

		
//		회원목록이 이클립스 콘솔이 아닌
//		웹 브라우저 화면에 출력되도록 MemListServlet을 변경하세요.
		
			
	}

	
	
}
