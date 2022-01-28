package cine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cine.base.BaseDB;
import cine.model.ReviewVO;

public class ReviewDAO extends BaseDB {
	// 리뷰 DAO
	public void create(ReviewVO rvo) {
		// 리뷰 작성
		Connection conn = null;
		PreparedStatement stmt = null;

		StringBuffer qry = new StringBuffer()
				.append(" INSERT INTO reviews SET fnum = ?, loginId = ?, rating=?, comment = ? ");

		String sql = qry.toString();

		try {
			// 드라이브로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 디비연결
			conn = getConn();
			// statement 생성
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, rvo.getFnum());
			stmt.setString(2, rvo.getLoginId());
			stmt.setInt(3, rvo.getRating());
			stmt.setString(4, rvo.getComment());
			// sql실행

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

	public int counting(int fnum) {
		// 리뷰 수 계산
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalRows = 0;
		StringBuffer qry = new StringBuffer().append(" SELECT COUNT(*) FROM reviews WHERE fnum = ? ");

		String sql = qry.toString();
		try {
			// 드라이브로드
			// 디비연결
			conn = getConn();
			// Statement 생성
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fnum);
			// SQL 실행
			rs = stmt.executeQuery();

			if (rs.next()) {
				totalRows = rs.getInt("COUNT(*)");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, null);
		}
		return totalRows;
	}

	public ArrayList<ReviewVO> read(int fnum) {
		// 리뷰 읽기
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> al = new ArrayList<ReviewVO>();

		try {

			StringBuffer qry = new StringBuffer()
					.append(" SELECT * FROM reviews WHERE fnum = ? ORDER BY num DESC LIMIT 0 , 10 ");
			String sql = qry.toString();
			// 드라이버 로드

			// 연결
			conn = getConn();

			// statement 생성
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, fnum);

			// SQL 실행
			rs = stmt.executeQuery();
			// ResultSet 결과처리
			while (rs.next()) {
				ReviewVO rvo = new ReviewVO();
				rvo.setRnum(rs.getInt("rnum"));
				rvo.setLoginId(rs.getString("loginId"));
				rvo.setRating(rs.getInt("rating"));
				rvo.setComment(rs.getString("comment"));
				rvo.setRvdate(rs.getTimestamp("rvdate"));

				al.add(rvo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 연결 닫기
			dbClose(conn, stmt, rs);
		}

		return al;
	}

	public void update(ReviewVO rvo) {
		// 리뷰 수정
		Connection conn = null;
		PreparedStatement stmt = null;

		StringBuffer qry = new StringBuffer()
				.append(" UPDATE reviews SET loginId = ?, rating=?, comment = ? WHERE rnum = ? ");

		String sql = qry.toString();
		try {
			// 드라이브로드
			// 디비연결
			conn = getConn();
			// Statement 생성
			stmt = conn.prepareStatement(sql);
			// SQL 실행
			stmt.setString(1, rvo.getLoginId());
			stmt.setInt(2, rvo.getRating());
			stmt.setString(3, rvo.getComment());
			stmt.setInt(4, rvo.getRnum());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, null);
		}
	}

	public void delete(int rnum) {
		// 리뷰 삭제
		Connection conn = null;
		PreparedStatement stmt = null;
		StringBuffer qry = new StringBuffer().append(" DELETE FROM reviews WHERE rnum = ? ");

		String sql = qry.toString();
		try {
			// 드라이브로드
			// 디비연결
			conn = getConn();
			// Statement 생성
			stmt = conn.prepareStatement(sql);
			// SQL 실행
			stmt.setInt(1, rnum);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, null);
		}
	}
}