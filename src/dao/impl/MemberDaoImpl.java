package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dto.Member;

public class MemberDaoImpl implements MemberDao {

	private PreparedStatement ps = null;  //SQL수행 객체
	private ResultSet rs = null;	//SQL조회 결과 객체
	
	@Override
	public int selectCntMerberByUserNoUserIdUserPw(Connection conn, Member member) {
	
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM member";
		sql += " WHERE 1=1";
//		sql += " 	AND uesrNo = ?";		
		sql += " 	AND uesrNo = member_seq.nextval";		
		sql += " 	AND uesrId = ?";
		sql += " 	AND uesrPw = ?";
		
		//결과 저장 변수
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			
			ps.setInt(1, member.getUserNo());
			ps.setString(2, member.getUserId());
			ps.setString(3, member.getUserPw());

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		//최종 결과 반환
		return cnt;
	}

	@Override
	public Member selectCntMerberByUserNo(Connection conn, Member member) {
		
		String sql = "";
		sql += "SELECT * FROM member";
		sql += " WHERE 1=1";
		sql += " 	AND userNo = ?";
		
		//조회결과를 저장할 객체
		Member result = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL 수행 객체
		
			rs = ps.executeQuery();	//SQL 수행 및 결과집합 저장
		
			while(rs.next()) {
				result = new Member();
				
				result.setUserNo( rs.getInt("userNo") );
				result.setUserKind(rs.getInt("userKind"));
				result.setUserNick( rs.getString("userNick") );
				result.setUserId( rs.getString("userId") );
				result.setUserPw( rs.getString( "userPw") );
				result.setUserName(rs.getString("userName"));
				result.setUserNick(rs.getString("userNick"));
				result.setUserPh(rs.getString("userPh"));
				result.setUserEm(rs.getString("userEm"));
				result.setUserGen(rs.getString("userGen"));
				result.setUserBirth(rs.getDate("userBirth"));
				result.setJoinDate(rs.getDate("userjoinDate"));
				result.setBusinessNo(rs.getString("userbusinessNo"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}

}
