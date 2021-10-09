package dao.face;

import java.sql.Connection;

import dto.Member;

public interface MemberDao {

	/**
	 * userNo과 userId, userPw가 일치하는 회원의 수를 조회
	 * 
	 * @param connection - DB
	 * @param member - 조회할 회원의 정보
	 * @return int - 1(존재하는 회원), 0(존재하지 않는 회원), -1(에러)
	 */
	int selectCntMerberByUserNoUserIdUserPw(Connection conn, Member member);

	/**
	 * userNo을 이용해 회원정보 조회
	 * 
	 * @param connection
	 * @param member - 조회할 회원
	 * @return Member - 죄회될 결과 객체
	 */
	Member selectCntMerberByUserNo(Connection con, Member member);
	
	
}
