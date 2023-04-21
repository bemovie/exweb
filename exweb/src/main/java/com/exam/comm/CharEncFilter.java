package com.exam.comm;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//필터 : 서블릿의 실행 전후에 끼어들어가서 실행
//	다수의 서블릿들이 수행하는 공통작업을 실행할때 사용
//	Filter 인터페이스를 구현하여 필터 클래스 정의
//web.xml 에 <filter> 태그로 등록하거나, 클래스에 @WebFilter 적용 (서블릿의 방식과 유사)

public class CharEncFilter implements Filter{
	
	private String enc;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 필터가 처음 생성됐을 때 1번 실행
		System.out.println("CharEncFilter init() 실행");
		// FilterConfig : 필터의 초기설정을 불러옴
		// 필터의 초기화 파라미터 값 읽기
		enc = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 필터의 경로에 맞는 요청이 올때마다 한번씩 실행
		System.out.println("CharEncFilter doFilter() 실행");
		
		// 1) 필터의 설정이 들어가는 부분 (요청 파라미터 읽기전에 인코딩 설정,)
		
		request.setCharacterEncoding(enc); //enc라는 변수가 없다. enc를 field로 선언
		
		//이후 실행될 필터 또는 서블릿들을 실행
		chain.doFilter(request, response);
		
		// 2) 서블릿이 어떤 응답을 만들어냈을때 그 응답을 조작하고 싶을때 여기 코딩
	}
	
	@Override
	public void destroy() {
		// 필터 객체가 소멸(삭제)되기 전에 1번 실행
		System.out.println("CharEncFilter destroy() 실행");
	}

}
