

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
<title>리뷰 상세 화면</title>
<style>
@import
	url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css)
	;
body {
      width: 960px;
      margin: 0px auto;
      font-family: 'NanumSquare', sans-serif;
    }
    #review {
      text-align: center;
      margin: 5px;
    }
 </style>
</head>


<%@ include file="./include/header.jsp"%>

<body>
  <div class="container">
    <header>
      <div class="container" id="titleTab">
        <h1>
        <i class="bi bi-camera-video-fill"></i>
        	운동 영상 리뷰
        </h1>
      </div>
    </header>
    <hr>
      <div class="d-flex justify-content-between">
        <div>
        <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath }/main?action=list&youtubeId=${review.youtubeId}'">목록</button>
 	<c:if test="${loginUser.id eq review.writer}">
                  <!-- Button trigger moda  l -->
      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" id="modi-btn">
        리뷰수정
      </button>
      
	</c:if>
<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">리뷰 수정하기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="${ pageContext.request.contextPath }/main" method="post">
        <input type="hidden" name="action" value="update"> 
		<input type="hidden" name="reviewId" value="${review.reviewId}">
		<input type="hidden" name="youtubeId" value="${review.youtubeId}">
		      <div class="modal-body">
		        <div class="mb-3">
		          <label for="exampleInputEmail1" class="form-label">제목</label>
		          <input type="text" class="form-control" id="title" name="title" aria-describedby="emailHelp" value="${review.title }">
		        </div>
		        <div class="mb-3">
		          <label for="InputContent" class="form-label">내용</label>
		          <input type="text" class="form-control" id="content" name="content" value="${review.content }">
		        </div>
		      </div>
		    	<div class="modal-footer">
					<input type="submit" class="btn btn-primary" value="수정하기">
					<input type="reset" class="btn btn-primary" value="취소">
				</div>
        </form>
		    </div>
		  </div>
		</div>
		<c:if test="${loginUser.id eq review.writer}">
          <button type="button" class="btn btn-danger" name="remove" id="remove" onclick="location.href='${pageContext.request.contextPath }/main?action=remove&youtubeId=${review.youtubeId}&reviewId=${review.reviewId }'">리뷰삭제</button>
          </c:if>
        </div>
      </div>

      <div class="d-flex flex-column justify-content-center border mt-3" >
        <div id="review">
	        <div class="fs-1"> 제목 : ${review.title }</div>
	  		<div class="d-flex flex-row justify-content-around"> 
		  	  	<div> ${review.viewCnt } </div>
		  		<div>작성자: ${review.writer } </div>	
		  		<div> ${review.regDate } </div>
	  		</div>
	 		<div class="fs-4"> ${review.content}</div>

        
        </div>
      </div>

  </div>

  	  

</body>
</html>
