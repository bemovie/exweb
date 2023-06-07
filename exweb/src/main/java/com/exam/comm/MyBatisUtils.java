package com.exam.comm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	
	private static SqlSessionFactory sqlSessionFactory;	
	
	static { //~ 초기화 블록 ~
		
		try {
			
			//마이바티스 전체 설정파일 위치 (클래스패스 (현재, /exweb/src/main/java/) 기준)
			String resource = "batis/mybatis-config.xml"; 
			//(설정파일 위치 지정, inputStream입력용 파이프를 박아서 값을 read하면 pipe에 있는 내용이 나옴)//
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//설정파일의 내용대로 SqlSessionFactory(마이바티스본체)를 생성
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//(inputStream을 주면서, 설정파일에 써있는대로 SessionFactory를 만들어라)//
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	} //~ 초기화 블록 ~
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

}
