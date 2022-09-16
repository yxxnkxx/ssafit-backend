<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<title>영상 상세 화면</title>
<style>
@import
	url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css)
	;

body {
	width: 960px;
	margin: 5px auto;
	font-family: 'NanumSquare', sans-serif;
}

header {
	margin: 5px, auto;
}

#videoBox {
	text-align: center;
}
</style>
</head>

<%@ include file="./include/header.jsp"%>

<body>
	<div class="container">
		<header>
			<div class="container" id="titleTab">
				<h1>
					<i class="bi bi-camera-video-fill"></i> 운동 영상 리뷰
				</h1>
			</div>
		</header>
		<hr>
	<p align="middle"><iframe  src="https://www.youtube.com/embed/${youtubeId }" width=420 height=315 ></iframe></p>	
		<div class="container" id="videoBox"></div>
		<hr>
		<div class="d-flex justify-content-between">
			<!-- Button trigger modal -->
			<button type="submit" class="btn btn-primary" data-bs-toggle="modal"
				data-bs-target="#staticBackdrop">리뷰 작성</button>

			<!-- Modal -->
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="staticBackdropLabel">리뷰 작성하기</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<form action="${ pageContext.request.contextPath }/main" method="post">
							<input type="hidden" name="action" value="write"> 
							<input type="hidden" name="youtubeId" value="${youtubeId}">
							<div class="modal-body">
								<div class="mb-3">
									<label for="title" class="form-label">리뷰 제목</label> <input
										type="text" class="form-control" id="title" name="title"
										aria-describedby="emailHelp">
								</div>
								<div class="mb-3">
									<label for="content" class="form-label">리뷰 내용</label>
									<textarea rows="10" class="form-control" id="content"
										name="content"></textarea>
								</div>
							</div>
							<div class="modal-footer">
								<input type="submit" class="btn btn-primary" value="저장">
								<input type="reset" class="btn btn-primary" value="취소">
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="floatingInput"
					placeholder="name@example.com"> <label for="floatingInput"><i
					class="bi bi-search"></i>검색어를 입력하세요.</label>
			</div>

		</div>
		<hr>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">조회수</th>
					<th scope="col">작성시간</th>
				</tr>
			</thead>
			<tbody id="review-table">
				<c:forEach items="${reviewList}" var="review">
					<tr>
						<td>${review.reviewId }</td>
						<td><a
							href="${pageContext.request.contextPath}/main?action=detail&reviewId=${review.reviewId}&youtubeId=${review.youtubeId}"
							class="btn"><input type="hidden" name="action" value="detail">${review.title}</a></td>
						<td>${review.writer }</td>
						<td>${review.viewCnt }</td>
						<td>${review.regDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<script src="review.js"></script>
</body>
</html>