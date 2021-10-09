package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	//OJDBC 드라이버
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	//DB연결 정보
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "scott";
	private static final String PASSWORD = "tiger";
	
	//private 생성자
	private JDBCTemplate() { }
	
	//DB연결 객체
	private static Connection conn;
	
	//Connection객체 반환 - **싱글톤 패턴을 적용하여 객체를 딱 한 번만 생성 후 유지되도록 한다
	public static Connection getConnection() {
		
		if( conn == null ) {
			try {
				//드라이버 로드
				Class.forName(DRIVER);
				
				//DB 연결
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
				//AutoCommit 설정 끄기
				//자동 커밋 설정
				// -> true - AutoCommit 한다
				// -> false - AutoCommit 하지 않는다, 직접 commit 또는 rollback 해야한다
				conn.setAutoCommit(false);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//DB연결 객체 반환
		return conn;
	}	
	
	//JDBC라이브러리처럼 생성-------------------------------------------
	
	//Connection 객체 닫기
	// -> JDBCTemplate 클래스에 정적메소드로 생성하여
	// -> 매개변수로 Connection을 전달하면 close 코드가 실행되는 메소드
	// -> 매번 try-catch하지 않아도 된다 
	public static void close(Connection conn) {
		try {
			//!conn.isClosed(): 
			if(conn!=null && !conn.isClosed())   conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Statement 객체 닫기
	public static void close(Statement st) {
		try {
			if(st!=null && !st.isClosed())   st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//PreparedStatement 객체 닫기
	public static void close(PreparedStatement ps) {
		try {
			if(ps!=null && !ps.isClosed())    ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//ResultSet 객체 닫기
	public static void close(ResultSet rs) {
		try {
			if(rs!=null && !rs.isClosed())    rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//commit 수행 - try-catch가 필요한 코드이기에 사전에 반영하여 작성하는 것
	public static void commit(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed())	 conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//rollback 수행
	public static void rollback(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed())	 conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
}




