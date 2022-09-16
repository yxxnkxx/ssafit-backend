<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex flex-row-reverse" id="head-container">
	<c:if test="${empty loginUser }">
  <div class="p-2"><a href="${pageContext.request.contextPath}/user/login.jsp" id="check-login">
   		로그인 </a></div>
  <div class="p-2"><a href="${pageContext.request.contextPath}/user/signup.jsp">  
  			회원가입</a></div>
  </c:if>
  <c:if test="${!empty loginUser }">
     <div class="p-2"> ${ loginUser.id }님 환영합니다.</div>
    <div class="p-2"><a href="${pageContext.request.contextPath}/main?action=logout">로그아웃</a></div>
    <div class="p-2"><a href="${pageContext.request.contextPath}/main?action=likeList">찜 리스트</a></div>
   </c:if>

  <div class="p-2"><a href="${pageContext.request.contextPath}/main">Home</a></div>

  <div class="me-auto p-2">SSAFIT</div>
</div>
<hr>