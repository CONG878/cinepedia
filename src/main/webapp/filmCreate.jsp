<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 등록 페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<link rel="stylesheet" href="css/mainStyle.css" />
</head>
<body>

	<div class="container">
		<header>
			<nav class="nav_container">
				<%@ include file="navInclude.jsp"%>
			</nav>
		</header>
		<main class="main_container">
			<section>
				<article>
					<div class="title_outer">
						<h2>영화 등록</h2>
					</div>
					<form method="post" action="" enctype="multipart/form-data">
						<div class="container">
							<div class="createId_box col-md-10">
								<div class="col-md-2 inner">영화 제목</div>
								<div class="col-md-4">
									<input name="title" id="title" class="form-control" required>
								</div>
								<!-- <div id="msg"></div> -->
							</div>
							<div class="createPw_box col-md-10">
								<div class="col-md-2 inner">러닝타임</div>
								<div class="col-md-4">
									<input name="runningTime" id="runningTime" class="form-control">
								</div>
							</div>
							<div class="createName_box col-md-10">
								<div class="col-md-2 inner">예고편</div>
								<div class="col-md-4">
									<input name="trailer" id="trailer" class="form-control">
								</div>
							</div>
							<div class="createN_Name_box col-md-10">
								<div class="col-md-2 inner">배우</div>
								<div class="col-md-4">
									<input name="actor" id="actor" class="form-control">
								</div>
							</div>
							<div class="createEmail_box col-md-10">
								<div class="col-md-2 inner">소개문</div>
								<div class="col-md-4">
									<textarea name="overview" id="overview" class="form-control"></textarea>
								</div>
							</div>
							<div class="col-md-10">
								<div class="col-md-2 inner">포스터</div>
								<div class="col-md-4">
									<input type="file" accept="image/*" name="poster" id="poster"
										class="form-control">
								</div>
							</div>
						</div>
						<button type="button" class="btn btn-default pull-right"
							id="btn_btn" onclick="location.href='MainPage'">홈으로</button>
						<button type="submit" class="btn btn-success pull-right"
							id="btn_btn">영화 등록</button>
					</form>
				</article>
			</section>
		</main>
	</div>
</body>
</html>