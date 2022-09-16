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

<%@ include file="./include/header.jsp" %>

<div class="header-img">
  <img src="./data/The-Gymasium-Cosy-Beach-View.jpg">
</div>
<%! boolean check = false;  %>
<%
	if (!check) {
	String root = request.getContextPath();
    response.sendRedirect(root+"/main");
    check = true;
	}
%>


<body>


  <div class="input-group mb-3">
    <span class="input-group-text" id="basic-addon1"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
        fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
        <path
          d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
      </svg></span>
    <input type="text" class="form-control" placeholder="운동 제목 검색" aria-label="Username"
      aria-describedby="basic-addon1">
  </div>


  <h3>최근 많이 본 영상</h3>
  <div class="container mb-1">

  	<c:forEach items="${interestList }" var ="interest">
  	    <div class="thumbnail"><a id=${interest.youtubeId } href="${pageContext.request.contextPath}/main?action=list&youtubeId=${interest.youtubeId }"><img class="img-thumbnail"
          src="https://img.youtube.com/vi/${interest.youtubeId }/hqdefault.jpg"></a>
      	<div class="d-flex flex-column">
	         <div class="video-title">${interest.title }</div>
	         <div class="d-flex flex-row justify-content-around">
			      	<div class="caption">
			        	<span class="badge text-bg-primary">${interest.fitPartName }</span>
			        	<p class="video-channel">${interest.channelName }</p>
			      	</div>
			      	<div>
			      	 <span class="badge text-bg-secondary"><i class="bi bi-eye-fill fs-10"></i>${interest.viewCnt }</span></div>
			      	 <div>
			      	 <c:if test="${!empty loginUser }">
			   			<form action="main" method="get">
							<input type="hidden" name="action" value="like">
							<input type="hidden" name="youtubeId" value=${interest.youtubeId }>		
			      	 		<input type="submit" class="btn btn-info" name="like" value="찜">
			      	 	</form>
			      	 </c:if>
			      	</div>
		      	</div>
		      </div>
    	</div>
  	
  	</c:forEach>

  </div>


  <div class="">
    <h3>운동 부위 선택</h3>
	<form action="main" method="get">
	<input type="hidden" name="action" value="select">
    <div class="buttons">
      <input type="submit" class="btn btn-secondary" name="part" value="전신">
      <input type="submit"  class="btn btn-secondary" name="part" value="상체">
      <input type="submit"  class="btn btn-secondary" name="part" value="하체">
      <input type="submit"  class="btn btn-secondary" name="part" value="복부">
    </div>
	</form>
    <div id="select-video" class="container">
		<c:forEach items="${partList }" var ="part">
	  	    <div class="thumbnail"><a id=${part.youtubeId } href="${pageContext.request.contextPath}/main?action=list&youtubeId=${part.youtubeId }"><img class="img-thumbnail"
	          src="https://img.youtube.com/vi/${part.youtubeId }/hqdefault.jpg"></a>
	         <div class="video-title">${part.title }</div>
	      	<div class="d-flex flex-row justify-content-around	">
		      	<div class="caption">
	
		        	<span class="badge text-bg-primary">${part.fitPartName }</span>
		        	<p class="video-channel">${part.channelName }</p>
		      	</div>
		      	<div>
			      	 <span class="badge text-bg-secondary"><i class="bi bi-eye-fill fs-10"></i>${part.viewCnt }</span></div>
			      	 <div>
			      	 <c:if test="${!empty loginUser }">
			   			<form action="main" method="get">
							<input type="hidden" name="action" value="like">
							<input type="hidden" name="youtubeId" value=${part.youtubeId }>		
			      	 		<input type="submit" class="btn btn-info" name="like" value="찜">
			      	 	</form>
			      	 </c:if>
			     </div>
	      	</div>
	    	</div>
  		</c:forEach>
  		

    </div>
  </div>
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
    crossorigin="anonymous"></script>

  <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
  <script src="/data/video.json" type="text/javascript"></script>
  <script src="index.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>

</body>

</html>