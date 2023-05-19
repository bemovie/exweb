package com.exam.member;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDaoBatis implements MemberDao{

	SqlSessionFactory sqlSessionFactory;
	
	{ //~ 초기화 블록 ~
		
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
	
	
	@Override
	public List<MemberVo> selectMemberList() {
		
		List<MemberVo> list = null; //new ArrayList<MemberVo>(); <<이렇게 초기값을 줘도 된다
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//실행할 SQL문과 동일한 이름의 메서드를 사용하여 SQL문 실행
			//SELECT결과가 1행인 경우(result값이 하나) selectOne, 2행이상인 경우(list값으로) selectList 메서드 사용 
			//첫번째 인자로 실행할 SQL문의 고유한 이름을 지정
			//두번째 인자로 SQL문 실행시 필요한 데이터(를 담은 객체)를 전달
			list = session.selectList("com.exam.member.MemberDao.selectMemberList");
			}
		
		return list;
	}

	@Override
	public int insertMember(MemberVo vo) {

		int num = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			num = session.insert("com.exam.member.MemberDao.insertMember", vo);
			session.commit(); //INSERT,UPDATE,DELETE 후에는 commit 필요
			}
		
		return num;
	}

	//삭제버튼을 클릭하면,
	//삭제가 되도록 MemberDaoBatis 클래스와 MemberMapper.xml 파일을 변경하세요.
	
	@Override
	public int deleteMember(String memIdv) {
		
		int num = 0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			num = session.delete("com.exam.member.MemberDao.deleteMember", memIdv);
			session.commit(); //INSERT,UPDATE,DELETE 후에는 commit 필요
			}
		
		return num;
	}

	@Override
	public MemberVo selectMember(String memId) {
		
		MemberVo vo = null; //new ArrayList<MemberVo>(); <<이렇게 초기값을 줘도 된다
		
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//실행할 SQL문과 동일한 이름의 메서드를 사용하여 SQL문 실행
			//SELECT결과가 1행인 경우(result값이 하나) selectOne, 2행이상인 경우(list값으로) selectList 메서드 사용 
			//첫번째 인자로 실행할 SQL문의 고유한 이름을 지정
			//두번째 인자로 SQL문 실행시 필요한 데이터(를 담은 객체)를 전달
			vo = session.selectOne("com.exam.member.MemberDao.selectMember", memId);
			}
		
		return vo;

	}

	
//	public List<MemberVo> selectListMember() {
//	}
//	
//	public List<MemberVo> selectMemList() {
//	}
//	
//	memberDao.selectMemberList();
//	
//	새로운 걸 만드는 사람이 만들었다. 메서드 이름들이 다 바껴야함
//	뭘 가지고 구현하던간에 메서드 이름을 고치지 않고 강제할 수 있다
//	이름에 대한 규칙을 정의 = 인터페이스
	
		
}
