<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	
<%@ include file="header.jsp" %>

<head>
    <meta charset="UTF-8">
    <title>会員登録</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/signupExtra.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/modal.css">
</head>

<!-- 회원가입을 진행했는데 이메일 중복되면 alert(중복되었습니다.) 띄워져야하고-->
<!-- 비밀번호 8글자이상 특수문자들어가게만 --> 
<body>

    <div class="signup-container">
        <h2>会員登録</h2>

        <form id="signupForm" action="/register" method="post" enctype="multipart/form-data">
            <label for="email">メールアドレス</label>
            <input type="text" id="email" name="email" class="form-input">
			
			<label for="password">パスワード</label>
			<input type="password" id="password" name="password" class="form-input">

            <label for="birthdate">生年月日</label>
            <input type="text" id="birthdate" name="birthdate"/>

            <label for="nickname">ニックネーム</label>
            <input type="text" id="nickname" name="nickname"/>

            <label>性別</label>
            <div class="gender-options">
                <label><input type="radio" name="gender" value="male"/> 男性</label>
                <label><input type="radio" name="gender" value="female" /> 女性</label>
            </div>

			<p class="file-title">プロフィール画像</p>
			<div class="file-upload">
			  <label for="profileImage" class="file-btn">ファイルを選択</label>
			  <span id="file-name">選択されたファイルはありません</span>
			  <input type="file" id="profileImage" name="profileImage" accept="image/*" style="display:none;">
			</div>

            <button type="submit">登録</button>
        </form>
    </div>
	
	<div class="modal-overlay" style="display: none;"></div>

	<div class="modal" style="display: none;">
	  <div class="modal-content">
	    <p id="modal-message">ここにメッセージ</p>
	    <button id="modal-close-btn">OK</button>
	  </div>
	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
	<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ja.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/flatpicker.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/fileupload.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/submitcheck.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/modal.js"></script>	

</body>
<%@ include file="footer.jsp" %>

</html>
