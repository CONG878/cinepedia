package cine.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cine.dao.FilmDAO;
import cine.dao.ReviewDAO;
import cine.model.FilmVO;
import cine.model.ReviewVO;

/**
 * Servlet implementation class FilmView 선택한 영화 정보 서블렛
 */
@WebServlet("/FilmView")
public class FilmView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FilmView() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "filmView.jsp";// jsp 파일 주소
		int fnum = Integer.parseInt(request.getParameter("fnum"));
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");// 로그인 아이디 속성을 받음
		// int permission = (int) session.getAttribute("permission");

		try {
			FilmVO fvo = new FilmDAO().read(fnum);
			request.setAttribute("filmView", fvo);

			int count = new ReviewDAO().counting(fnum);
			request.setAttribute("count", count);

			ArrayList<ReviewVO> rvo = new ReviewDAO().read(fnum);
			request.setAttribute("replyView", rvo);// new FilmDAO().readHit(fnum);
		} catch (Exception e) {
			url = "errorPage500.jsp";// 예외 처리
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
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
		int fnum = Integer.parseInt(request.getParameter("fnum"));
		// String search = request.getParameter("search");
		String pg = request.getParameter("pg");

		response.sendRedirect("FilmDelete?fnum=" + fnum);
	}
}
