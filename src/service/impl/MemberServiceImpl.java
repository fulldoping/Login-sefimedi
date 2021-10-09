package service.impl;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.Member;
import service.face.MemberService;

public class MemberServiceImpl implements MemberService {

	//MemberDao 객체 생성
	private MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	public Member getLoginMember(HttpServletRequest req) {
		
		try {
			//한글 인코딩
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Member member = new Member();
		
		member.setUserId( req.getParameter("userId") );
		member.setUserPw( req.getParameter("userPw") );
		
		return member;
	}

	@Override
	public boolean login(Member member) {

		if( memberDao.selectCntMerberByUserNoUserIdUserPw(JDBCTemplate.getConnection(), member) > 0 ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Member info(Member member) {
		
		return memberDao.selectCntMerberByUserNo(JDBCTemplate.getConnection(), member);
	}

}
