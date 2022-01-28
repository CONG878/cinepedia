package cine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cine.base.BaseDB;
import cine.model.MemberVO;

public class MemberDAO extends BaseDB {
	// 회원 DAO
	public void create(MemberVO mvo) {
		// 회원 가입
		Connection conn = null;
		PreparedStatement stmt = null;

		StringBuffer qry = new StringBuffer().append(" INSERT INTO member SET loginId = ?, ")
				.append(" loginPw = ?, realName = ?, nickName = ?, email = ? ");

		String sql = qry.toString();

		try {
			// 드라이브로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 디비연결
			conn = getConn();
			// statement 생성
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mvo.getLoginId());
			stmt.setString(2, mvo.getLoginPw());
			stmt.setString(3, mvo.getRealName());
			stmt.setString(4, mvo.getNickName());
			stmt.setString(5, mvo.getEmail());

			// SQL실행
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 닫기
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
