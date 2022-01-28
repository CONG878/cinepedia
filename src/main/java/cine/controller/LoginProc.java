package cine.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cine.dao.LoginDAO;
import cine.model.MemberVO;

/**
 * Servlet implementation class LoginProc 로그인 실행 서블렛
 */
@WebServlet("/LoginProc")
public class LoginProc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginProc() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String url = "";

		HttpSession session = request.getSession();

		MemberVO mvo = new MemberVO();
		mvo.setLoginId(loginId);
		mvo.setLoginPw(loginPw);
		mvo = new LoginDAO().loginChk(mvo);

		// System.out.println(mvo.getLoginId());
		// System.out.println(mvo.getPermission());

		// 관리자 권한으로 로그인
		/*
		 * if (mvo.getPermission() == 1) { session.setAttribute("loginId",
		 * mvo.getLoginId()); session.setAttribute("loginPw", mvo.getLoginPw());
		 * session.setAttribute("loginName", mvo.getRealName()); url = "AdminPage"; }
		 * //사용자 로그인 else if (mvo.getPermission() == 0) {
		 */
		// 관리자 권한 미구현 관계로 권한 구분 없이 로그인
		if (mvo.getPermission() == 1 || mvo.getPermission() == 0) {
			session.setAttribute("loginId", mvo.getLoginId());
			session.setAttribute("loginPw", mvo.getLoginPw());
			session.setAttribute("loginName", mvo.getRealName());
			url = "MainPage";
		}
		// 로그인 되지 않았을 경우 로그인 권유
		else {
			url = "Login";
		}
		// System.out.println(mvo.getPermission());
		response.sendRedirect(url);

	}

}
