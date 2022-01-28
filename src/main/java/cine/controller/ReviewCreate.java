package cine.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cine.dao.ReviewDAO;
import cine.model.ReviewVO;

/**
 * Servlet implementation class ReviewCreate 리뷰 작성 서블렛
 */
@WebServlet("/ReviewCreate")
public class ReviewCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewCreate() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/hrml; charset=UTF-8");
		HttpSession session = request.getSession();
		int fnum = Integer.parseInt(request.getParameter("fnum"));
		String pg = request.getParameter("pg");
		// String search = request.getParameter("search");
		String loginId = (String) session.getAttribute("loginId");
		// String writerName = (String) session.getAttribute("loginName");
		int rating = Integer.parseInt(request.getParameter("rating"));
		String comment = request.getParameter("comment");

		ReviewVO rvo = new ReviewVO();
		rvo.setFnum(fnum);
		rvo.setLoginId(loginId);
		rvo.setRating(rating);
		rvo.setComment(comment);

		new ReviewDAO().create(rvo);
		new ReviewDAO().update(rvo);
		response.sendRedirect("FilmView?fnum=" + fnum);
	}

}
