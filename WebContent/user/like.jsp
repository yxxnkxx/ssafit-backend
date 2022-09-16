<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SSAFIT</title>
  <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

  <style>
    @import url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css);

    body {
      width: 960px;
      margin: 0px auto;
      font-family: 'NanumSquare', sans-serif;
    }

    .container {
      display: flex;
      justify-content: center;

    }

    i {
      font-size:x-large
    }



    .video-title {
      padding: 5px;
      font: bolder;
      max-width: 300px;
      font-size: large;
    }


    .img-thumbnail {
      min-width: 300PX;
      height: 200px;
      text-align: center;
    }

    .header-img {
      text-align: center;
    }
  </style>
</head>

<%@ include file="../include/header.jsp" %>

<body>
  <h3>내가 찜한 영상</h3>
  <div class="container mb-1 flex-wrap">
  	<c:forEach items="${likeList }" var ="like">
  	    <div class="thumbnail"><a id=${like.youtubeId } href="${pageContext.request.contextPath}/main?action=likeList&youtubeId=${like.youtubeId }"><img class="img-thumbnail"
          src="https://img.youtube.com/vi/${like.youtubeId }/hqdefault.jpg"></a>
      	<div class="d-flex flex-column">
	         <div class="video-title">${like.title }</div>
	         <div class="d-flex flex-row justify-content-around">
			      	<div class="caption">
			        	<span class="badge text-bg-primary">${like.fitPartName }</span>
			        	<p class="video-channel">${like.channelName }</p>
			      	</div>
			      	<div>
			      	 <span class="badge text-bg-secondary"><i class="bi bi-eye-fill fs-10"></i>${like.viewCnt }</span>
			      	</div>
		      	</div>
		      </div>
    	</div>
  	
  	</c:forEach>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
    crossorigin="anonymous"></script>

  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  <script src="/data/video.json" type="text/javascript"></script>
  <script src="index.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>

</body>

</html>