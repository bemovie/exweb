<%@page import="java.util.HashMap"%>
<%@page import="com.exam.member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%
	String str = null;
	int len = str.length();
	
%>    --%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>HELLO JSP</h1>

<!-- HTML 주석 -->
<%-- JSP 주석 
JSP 구성요소
- 디렉티브 : page(현재JSP파일전체에대한설정), include(다른JSP파일포함), taglib(태그라이브러리사용)
	<%@ 디렉티브명	속성명="속성값" %>
- 스크립트요소
	-스크립트릿 : <% 서블릿의service메서드내부에들어갈자바코드 %>
	-표현식 	<%= 현재위치에결과값을출력할자바코드 %>
	-선언부 : <%! 서블릿의service메서드외부에들어갈자바코드 %>
	-주석
- 액션태그	: 자주 사용하는 자바코드를 대체할 수 있는 태그
- EL(표현언어) : <%= %> -> ${}
	(Expression Language) : 표현식을 대체할 수 있는 language
	${자바코드} 표현으로 JSP표현식(<%= %>)과 유사한 역할 수행
- 커스텀태그 : 지원하지 못하는 코드를 만들어서 사용하는 태그
	JSTL(JSP Standard Tag Library) : 자주 사용하는 코드를 대체하는 태그들을 정의한 라이브러리	
--%>

<%
//	변수 선언없이 사용가능한 내장객체(기본객체) :

//	request(요청객체), response(응답객체), session(세션객체), application(서블릿컨텍스트객체)
//	out(응답출력스트림), config(서블릿설정), pageContext(현재JSP파일에대한모든정보를담은객체)
//	page(현재JSP서블릿객체), exception(발생한예외,에러페이지에서만사용가능)

	
	out.println("출력할내용");
	out.println(session == request.getSession());
	out.println(application == getServletContext());
	out.println(config == getServletConfig());
//	pageContext 객체에 현재 JSP 파일에서만 사용가능한 데이터를 속성으로 저장 가능
	pageContext.setAttribute("pa", "pv");
	out.println( pageContext.getAttribute("pa") );
//	pageContext 객체에는 다른 내장객체들이 모두 저장되어 있다
	out.println( request == pageContext.getRequest() );
	out.println( response == pageContext.getResponse() );
	out.println( session == pageContext.getSession() );
	out.println( application == pageContext.getServletContext() );
	
%>

<%! //선언부 : JSP파일이 최초 실행될 때 한번만 실행할 자바 코드
	int glocal = 0;
%>

<%	//JSP파일이 실행될 때마다 한번씩 반복 실행할 자바 코드
	int local = 0;
	out.println("local : " + ++local );
	out.println("glocal : " + ++glocal );
%>
<hr>
표현식 : <% out.print(local); %> <%= local %>
<hr>
액션태그
<%
	MemberVo v = (MemberVo)pageContext.getAttribute("m");
	//pageContext.setAttribute("m", MemberVo); 한적이 없으므로 null값이 나옴
	if(v==null){
		v = new MemberVo();
		pageContext.setAttribute("m", v);
	}
	v.setMemId("a001");
	out.print(v.getMemId());
%>
<jsp:useBean id="m" class="com.exam.member.MemberVo" scope="page"></jsp:useBean>
<jsp:setProperty name="m" property="memId" value="a001" />
<jsp:getProperty name="m" property="memId" />

<%
	/* request.getRequestDispatcher("/menu.jsp").forward(request, response); // /menu.jsp이동 */
	request.getRequestDispatcher("/menu.jsp").include(request, response); // /menu.jsp포함
%>

<%-- <jsp:forward page="/menu.jsp"></jsp:forward> --%>
<%-- <jsp:include page="/menu.jsp"></jsp:include> --%>

<h1>EL</h1>
${123} ${456.789} ${'문자열1'} ${true} ${false} ${null} <br>
${5+3} ${5-3} ${5*3} ${5/3} <br>

<%
	//EL에서 ${x} 라고 쓰면, x는 변수 이름이 아니라,
	//pageContext,request,session,application(servletContext)에 저장한 속성 이름으로 해석
	String s = "테스트";
	pageContext.setAttribute("ps", s);
	int[] arr = {3,4,5};
	pageContext.setAttribute("pa", arr);
	HashMap map = new HashMap();
	map.put("k","v");
	pageContext.setAttribute("pm", map);
	MemberVo vo = new MemberVo();
	vo.setMemId("a001");
	pageContext.setAttribute("pv", vo);
%>
<%-- <%out.print( s ); %> --%> 
<%=s %>, ${ps} <br>
배열의 1번칸의 값: <%=arr[1] %>, ${pa[1]} <br>
맵에 k라는 이름으로 저장된 값: <%=map.get("k")%>, ${pm.get("k")}, ${pm.k}, ${pm["k"]}<br>
객체의 memId 속성(변수)값:<%=vo.getMemId()%>, ${pv.getMemId()}, ${pv.memId}, ${pv["memId"]} <br>
<%
	pageContext.setAttribute("pa", 12);
	request.setAttribute("ra", 34);
	session.setAttribute("sa", 56);
	application.setAttribute("aa", 78);
	
	//EL에서 xxxScope 을 생략한 경우,
	//pageScope > requestScope > sessionScope > applicationScope 을 순차적으로 찾아보고,
	//먼저 발견되는 속성값을 사용
%>
<%=pageContext.getAttribute("pa")%>, ${pageScope.pa}, ${pageScope["pa"]}, ${pa} <br>
<%=request.getAttribute("ra")%>, ${requestScope.ra}, ${requestScope["ra"]}, ${ra} <br>
<%=session.getAttribute("sa")%>, ${sessionScope.sa}, ${sessionScope["sa"]}, ${sa} <br>
<%=application.getAttribute("aa")%>, ${applicationScope.aa}, ${applicationScope["aa"]}, ${aa} <br>
EL에서 별도의 선언없이 사용가능한 기본객체(JSP기본객체와다르다)<br>
- pageScope, requestScope, sessionScope, applicationScope <br>
- 파라미터값 : <%=request.getParameter("x")%>, ${param.x}, ${param["x"]} <br>
  파라미터값이 2개이상인 경우, param 대신 paramValues 사용 <br>
- 요청헤더값 : <%=request.getHeader("User-Agent")%>, <%-- <br> ${header.User-Agent},  --%><br> ${header["User-Agent"]} <br>
  헤더값이 2개이상인 경우, header 대신 headerValues 사용 <br>
- 쿠키값: <%=request.getCookies()[0].getName()%>, ${cookie.JSESSIONID.name} <br>
		<%=request.getCookies()[0].getValue()%>, ${cookie.JSESSIONID.value} <br>
		<%-- <%=request.getHeader("Cookie")%> --%>
- 컨텍스트초기화파라미터값:
	<%=application.getInitParameter("driver")%>, ${initParam.driver} <br>
- EL에서 JSP기본객체를 사용하고 싶은 경우, pageContext를 통해서 사용 가능 <br>
- 컨텍스트패스(애플리케이션고유경로)
	<%=request.getContextPath() %>, ${pageContext.request.contextPath} <br>	
파라미터x의 값이 "abc"인지 확인 : (EL에서 문자열 비교는 ==, != 로 가능)
<%=request.getParameter("x")=="abc" %>, <%=request.getParameter("x").equals("abc") %>, ${param.x=="abc"} <br>	
		
</body>
</html>