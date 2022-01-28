package cine.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cine.base.BaseDB;
import cine.model.FilmVO;

public class FilmDAO extends BaseDB {
	// 영화 DAO(SQL 실행문 모음)
	public void create(FilmVO fvo) {
		// 등록
		Connection conn = null;
		PreparedStatement stmt = null;

		StringBuffer qry = new StringBuffer().append(" INSERT INTO filminfo (title, runningTime, ")
				.append(" trailer, actor, overview, fileName, realFileName) VALUES (?,?,?,?,?,?,?) ");

		String sql = qry.toString();

		try {
			// 드라이브로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 디비연결
			conn = getConn();
			// statement 생성
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fvo.getTitle());
			stmt.setString(2, fvo.getRunningTime());
			stmt.setString(3, fvo.getTrailer());
			stmt.setString(4, fvo.getActor());
			stmt.setString(5, fvo.getOverview());
			stmt.setString(6, fvo.getFileName());
			stmt.setString(7, fvo.getRealFileName());

			// SQL 실행
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

	public ArrayList<FilmVO> read(int pg, int ppn) {
		// 전체 읽기(게시용)
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<FilmVO> al = new ArrayList<FilmVO>();

		try {
			StringBuffer qry = new StringBuffer().append(" SELECT fnum, title, fileName, realFileName ")
					.append(" FROM filminfo ORDER BY fnum DESC LIMIT " + ((pg - 1) * ppn) + ", " + ppn);
			String sql = qry.toString();
			// 드라이버 로드
			// 연결
			conn = getConn();
			// statement 생성
			stmt = conn.prepareStatement(sql);
			// SQL 실행
			rs = stmt.executeQuery();
			// ResultSet 결과처리
			while (rs.next()) {
				FilmVO fvo = new FilmVO();

				fvo.setFnum(rs.getInt("fnum"));
				fvo.setTitle(rs.getString("title"));
				fvo.setFileName(rs.getString("fileName"));
				fvo.setRealFileName(rs.getString("RealFileName"));

				al.add(fvo);
			}
			// 연결 닫기
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;
	}

	// Read

	public FilmVO read(int fnum) {
		// 선택한 영화 정보 읽기
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		FilmVO fvo = null;

		try {
			StringBuffer qry = new StringBuffer().append(" SELECT * FROM filminfo WHERE fnum = ? ");
			String sql = qry.toString();
			// 드라이버 로드

			// 연결
			conn = getConn();

			// statement 생성
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, fnum);

			// sql 실행
			rs = stmt.executeQuery();
			// Resultset 결과처리
			if (rs.next()) {
				fvo = new FilmVO();
				fvo.setFnum(rs.getInt("fnum"));
				fvo.setTitle(rs.getString("title"));
				fvo.setRunningTime(rs.getString("runningTime"));
				fvo.setTrailer(rs.getString("trailer"));
				fvo.setActor(rs.getString("actor"));
				fvo.setOverview(rs.getString("overview"));
				fvo.setFileName(rs.getString("fileName"));
				fvo.setRealFileName(rs.getString("realFileName"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 연결 닫기
			dbClose(conn, stmt, rs);
		}

		return fvo;
	}

	public int pCounting() {
		// 영화 갯수 세기(페이징용)
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalRows = 0;
		StringBuffer qry = new StringBuffer().append(" SELECT COUNT(*) FROM filminfo ");

		String sql = qry.toString();
		try {
			// 드라이브로드
			// 디비연결
			conn = getConn();
			// statement 생성
			stmt = conn.prepareStatement(sql);
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

	public void update(FilmVO fvo) {
		// 수정(미구현)
		Connection conn = null;
		PreparedStatement stmt = null;

		StringBuffer qry = new StringBuffer().append(" UPDATE filminfo (title, runningTime, trailer, ")
				.append(" actor, overview, fileName, realFileName) VALUES (?,?,?,?,?,?,?) WHERE fnum = ? ");

		String sql = qry.toString();
		try {
			// 드라이브로드
			// 디비연결
			conn = getConn();
			// statement 생성
			stmt = conn.prepareStatement(sql);
			// SQL 실행
			stmt.setString(1, fvo.getTitle());
			stmt.setString(2, fvo.getRunningTime());
			stmt.setString(3, fvo.getTrailer());
			stmt.setString(4, fvo.getActor());
			stmt.setString(5, fvo.getOverview());
			stmt.setString(6, fvo.getFileName());
			stmt.setString(7, fvo.getRealFileName());
			stmt.setInt(8, fvo.getFnum());

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, stmt, null);
		}
	}

	public void delete(int fnum) {
		// 삭제
		Connection conn = null;
		PreparedStatement stmt = null;

		StringBuffer qry = new StringBuffer().append(" DELETE FROM filminfo WHERE fnum = ? ");

		String sql = qry.toString();
		try {
			// 드라이브로드
			// 디비연결
			conn = getConn();

			// statement 생성
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, fnum);

			// sql 실행
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 연결 닫기
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