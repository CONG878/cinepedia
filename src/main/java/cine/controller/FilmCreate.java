package cine.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import cine.base.Thumbnail;
import cine.dao.FilmDAO;
import cine.model.FilmVO;

/**
 * Servlet implementation class FilmCreate 영화 등록 서블렛
 */
@WebServlet("/FilmCreate")
public class FilmCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FilmCreate() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// filmCreate.jsp 파일로 연결
		RequestDispatcher dispatcher = request.getRequestDispatcher("filmCreate.jsp");
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

		String savePath = request.getServletContext().getRealPath("upload");
		int fileSize = 1024 * 1024 * 10;// 10Mbyte
		MultipartRequest multi = null;
		// new MultipartRequest
		// (request객체, 업로드경로, 파일사이즈, 인코딩, 중복파일처리);
		multi = new MultipartRequest(request, savePath, fileSize, "UTF-8", new DefaultFileRenamePolicy());

		// HttpSession session = request.getSession();
		// request 객체들
		String title = multi.getParameter("title");
		String runningTime = multi.getParameter("runningTime");
		String trailer = multi.getParameter("trailer");
		String actor = multi.getParameter("actor");
		String overview = multi.getParameter("overview");
		String fileName = multi.getFilesystemName("poster");
		String realFileName = multi.getOriginalFileName("poster");
		// 썸네일 생성
		Thumbnail.createThumb(savePath + "/" + fileName);
		// VO 변수 생성
		FilmVO fvo = new FilmVO();
		fvo.setTitle(title);
		fvo.setRunningTime(runningTime);
		fvo.setTrailer(trailer);
		fvo.setActor(actor);
		fvo.setOverview(overview);
		fvo.setFileName(fileName);
		fvo.setRealFileName(realFileName);
		// DAO(SQL 실행문)에 VO 변수 입력
		new FilmDAO().create(fvo);
		// 메인 페이지로 리다이렉트
		response.sendRedirect("MainPage");
	}
}