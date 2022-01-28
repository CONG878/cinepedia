package cine.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cine.dao.MemberDAO;
import cine.model.MemberVO;

/**
 * Servlet implementation class MemberCreate 회원 가입 서블렛
 */
@WebServlet("/MemberCreate")
public class MemberCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberCreate() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("memberCreate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/hrml; charset=UTF-8");
		// HttpSession session = request.getSession();
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String realName = request.getParameter("realName");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");

		MemberVO mvo = new MemberVO();
		mvo.setLoginId(loginId);
		mvo.setLoginPw(loginPw);
		mvo.setRealName(realName);
		mvo.setNickName(nickName);
		mvo.setEmail(email);

		new MemberDAO().create(mvo);
		response.sendRedirect("Login");
	}

}
