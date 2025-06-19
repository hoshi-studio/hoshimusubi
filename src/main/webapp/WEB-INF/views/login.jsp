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
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;700&display=swap" rel="stylesheet">
</head>

<body>
	<div class="login-wrapper">
		
		<div class="fortune-header">
		  <img src="${pageContext.request.contextPath}/resources/img/login.png" alt="cat" class="fortune-cat">
		  <div class="fortune-text">
		    今日の運勢をお知らせします！<br>
		    🌙 簡単ログインから始めてください
		  </div>
		</div>	
		
		
	<div class="login-container">
	<form id="loginForm" action="/dologin" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	        
	        <input type="text" id="id" name="username" placeholder = "ID insert plz...." />
	        
	        
	        <input type="password" id="password" name="password" placeholder = "PW insert plz...." />

	        <input type="submit" value="ログイン" />
	    </form>
	
	
		</div>
	
		<div class="btn-group">
				<a href="${pageContext.request.contextPath}/register" class="signup-btn">会員登録</a>
				<a href="${pageContext.request.contextPath}/oauth2/authorization/google" class="google-btn">Google</a>
									  
									</div>	
		
		</div>
		
		<div id="modalOverlay" class="modal-overlay" style="display: none;"></div>

		<div id="successModal" class="modal" style="display: none;">
		  <div class="modal-content">
		    <p>ログインに成功しました！</p>
		    <button onclick="closeModal()">OK</button>
		  </div>
		</div>
		
		<div id="erroridModal" class="modal" style="display: none;">
				  <div class="modal-content">
				    <p>IDを入力してください。</p>
				    <button onclick="closeModal()">OK</button>
				  </div>
				</div>
				
				<div id="errorpwModal" class="modal" style="display: none;">
								  <div class="modal-content">
								    <p>パスワードを入力してください。</p>
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