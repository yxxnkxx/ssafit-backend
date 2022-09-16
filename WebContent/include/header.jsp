<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
	
<div class="d-flex flex-row-reverse" id="head-container">

  <a href="${pageContext.request.contextPath}/user/login.jsp" id="check-login">
   <div class="p-2">로그인</div>
   </a>
  <a href="${pageContext.request.contextPath}/user/signup.jsp">  
    <div class="p-2">회원가입</div>
  </a>
  <div class="p-2">헬스장 찾기</div>
  <div class="p-2"><a href="${pageContext.request.contextPath}/main">Home</a></div>

  <div class="me-auto p-2">SSAFIT</div>
</div>
<hr>