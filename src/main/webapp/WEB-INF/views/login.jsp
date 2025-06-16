<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
	
<%@ include file="header.jsp" %>
	
<c:if test="${param.error eq 'true'}">
  <script>window.addEventListener('load', () => showModal('error'));</script>
</c:if>

<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal.css">
</head>

<body>
	<div class="login-wrapper">
	<div class="login-container">
    <h2>ログイン</h2>
	<form action="/dologin" method="post">
	        <label for="username">ユーザーID</label>
	        <input type="text" id="id" name="username" />
	        
	        <label for="password">パスワード</label>
	        <input type="password" id="password" name="password" />

	        <input type="submit" value="ログイン" />
	    </form>
		</div>
		</div>
		
		<div id="modalOverlay" class="modal-overlay" style="display: none;"></div>

		<div id="successModal" class="modal" style="display: none;">
		  <div class="modal-content">
		    <p>ログインに成功しました！</p>
		    <button onclick="closeModal()">OK</button>
		  </div>
		</div>

		<div id="errorModal" class="modal" style="display: none;">
		  <div class="modal-content">
		    <p>ログインに失敗しました。</p>
		    <button onclick="closeModal()">OK</button>
		  </div>
		</div>
	
<script src="${pageContext.request.contextPath}/resources/js/modal.js"></script>	
</body>

<%@ include file="footer.jsp" %>

</html>