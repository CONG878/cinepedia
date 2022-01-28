<%@page import="java.util.Iterator"%>
<%@page import="cine.model.FilmVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String session_id = (String) session.getAttribute("loginId");
String search = request.getParameter("search");

int pg = (int) request.getAttribute("pg");
int ppn = (int) request.getAttribute("ppn");
int lastPage = (int) request.getAttribute("lastPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<!-- 내부  -->
<link rel="stylesheet" href="css/mainStyle.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="style.css">
<script src="common.js"></script>
</head>
<body>
	<div class="container">
		<header></header>
		<nav class="navbar">
			<%@ include file="navInclude.jsp"%>
		</nav>
		<main>
			<section>
				<article>
					<div>
						<%
						ArrayList<FilmVO> al = (ArrayList) request.getAttribute("list");
						Iterator<FilmVO> it = al.iterator();
						// 영화 정보가 있을 동안 반복해서 이미지, 제목 게시
						while (it.hasNext()) {
							FilmVO fvo = it.next();
						%>
						<div style="float: left; margin: 10px;">
							<img
								src="<%=fvo.getFileName() == null || "".equals(fvo.getFileName()) ? "noimage.gif" : "upload/t-" + fvo.getFileName()%>"
								style="width: 100px;">
							<div style="text-align: center;">
								<a href="FilmView?fnum=<%=fvo.getFnum()%>"><%=fvo.getTitle()%></a>
							</div>
						</div>
						<%
						}
						%>
					</div>
				</article>
				<article style="clear: both;">
					<button class="btn btn-default pull-right"
						onclick="location.href='FilmCreate'">영화 등록</button>
				</article>
			</section>
			<nav>
				<!-- 5편 단위로 페이징 -->
				<ul class="pager">
					<li class=""><a href="?page=1">첫 페이지</a></li>
					<%
					if (pg != 1) {
					%>
					<li class=""><a href="?page=<%=pg - 1%>">Previous</a></li>
					<%
					}
					for (int i = Math.max(1, Math.min(pg - 2, lastPage - 4)); i <= Math.min(lastPage, Math.max(pg + 2, 5)); i++) {
					if (i != pg) {
					%>
					<li class="page-item"><a href="?page=<%=i%>"><%=i%></a></li>
					<%
					} else {
					%>
					<li class="page-item active"><a href="?page=<%=i%>"><%=i%></a></li>
					<%
					}
					}
					if (pg != lastPage) {
					%>
					<li class=""><a href="?page=<%=pg + 1%>">Next</a></li>
					<%
					}
					%>
					<li class=""><a href="?page=<%=lastPage%>">마지막 페이지</a></li>
				</ul>
			</nav>
		</main>
	</div>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/list.js"></script>
</body>
</html>