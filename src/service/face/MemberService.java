package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Member;

public interface MemberService {

	/**
	 * 로그인 정보 추출
	 * 
	 * @param req - 요청 정보 객체
	 * @return Member - 로그인 정보
	 */
	Member getLoginMember(HttpServletRequest req);

	/**
	 * 로그인 처리
	 * 
	 * @param member - 로그인 정보
	 * @return true - 인증됨, false - 인증되지 않음
	 */
	boolean login(Member member);

	/**
	 * 유저정보 가져오기
	 * 
	 * @param member - 회원 아이디를 가진 객체
	 * @return Member - 조회된 회원 정보
	 */
	Member info(Member member);

}
