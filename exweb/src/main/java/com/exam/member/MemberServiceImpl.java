package com.exam.member;

import java.util.List;

//싱글톤(Singleton) : 애플리케이션 전체에서 인스턴스를 1개만 생성하여 사용하는 객체

public class MemberServiceImpl implements MemberService {
//	private MemberDao memberDao = new MemberDaoBatis(); // MemberDaoBatis()는 MemberDao 구현체
	private MemberDao memberDao = MemberDaoBatis.getInstance();

	private MemberServiceImpl() { } //외부에서 생성자 호출 금지
	//클래스가 처음 로드될때, 객체를 생성하여 저장
	private static MemberService memberService =  new MemberServiceImpl(); //내부에서 객체 생성
	public static MemberService getInstance() { 
		//외부에서 memberService를 받아가기 위해서 getter 비슷한 애를 만듬
		//근데, getInstance()로 가져오려면 결국 객체 생성 해야함 => 객체 생성 없이 메서드 사용 => static
		//빨간줄 그어짐, memberService가 non-static 영역이라 그럼 => memberService 생성할때에도 static 달아주어야 사라짐
		return memberService; //생성해놓은 객체를 반환
	}
	
	@Override
	public List<MemberVo> selectMemberList() {
		return memberDao.selectMemberList();
	}

	@Override
	public int insertMember(MemberVo vo) {
		return memberDao.insertMember(vo);
	}

	@Override
	public int deleteMember(String memIdv) {
		return memberDao.deleteMember(memIdv);
	}

	@Override
	public MemberVo selectMember(String memId) {
		return memberDao.selectMember(memId);
	}

	@Override
	public int updateMember(MemberVo vo) {
		return memberDao.updateMember(vo);
	}

	@Override
	public MemberVo selectLogin(MemberVo vo) {
		return memberDao.selectLogin(vo);
	}

}
