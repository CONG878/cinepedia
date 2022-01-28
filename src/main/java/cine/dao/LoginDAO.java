package cine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cine.base.BaseDB;
import cine.model.MemberVO;

public class LoginDAO extends BaseDB {
	// 로그인 DAO
	public MemberVO loginChk(MemberVO mvo) {
		// ID, PW 확인
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			StringBuffer qry = new StringBuffer().append(" SELECT * FROM member ")
					.append(" WHERE loginId = ? AND loginPw = sha2(?, 224) ");
			// sha2(?, 224)는 암호화라는 의미
			String sql = qry.toString();
			// 드라이버 로드
			// 연결
			conn = getConn();
			// statement 생성
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, mvo.getLoginId());
			stmt.setString(2, mvo.getLoginPw());
			// SQL 실행
			rs = stmt.executeQuery();

			if (rs.next()) {
				mvo = new MemberVO();
				mvo.setLoginId(rs.getString("loginId"));
				mvo.setLoginPw(rs.getString("loginPw"));
				mvo.setRealName(rs.getString("realName"));
				mvo.setNickName(rs.getString("nickName"));
				mvo.setEmail(rs.getString("email"));
				mvo.setPermission(rs.getInt("permission"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, rs);
		}

		return mvo;
	}

	public String idchk(String loginId) {
		// 아이디 체크(미구현)
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String msg = null;
		try {
			StringBuffer qry = new StringBuffer().append(" SELECT * FROM member WHERE loginId = ? ");
			String sql = qry.toString();
			// 드라이버 로드
			// 연결
			conn = getConn();
			// statement 생성
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, loginId);
			// sql 실행
			rs = stmt.executeQuery();

			if (rs.next()) {
				msg = "Y";
			} else {
				msg = "N";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, rs);
		}
		return msg;
	}
}
