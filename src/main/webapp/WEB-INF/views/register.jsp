<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会員登録</title>
    <style>
        body {
            background: #f3e4fc;
            font-family: 'Arial', sans-serif;
        }

        .form-container {
            width: 400px;
            margin: 50px auto;
            padding: 20px;
        }

        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }

        .form-label {
            width: 120px;
            padding: 10px;
            background-color: #fff;
            border-radius: 10px;
            margin-right: 10px;
            font-weight: bold;
            text-align: center;
            letter-spacing: 1px;
        }

        .form-input {
            flex: 1;
            padding: 10px;
            border-radius: 10px;
            border: none;
            outline: none;
        }

        .submit-button {
            width: 120px;
            padding: 10px;
            border: none;
            border-radius: 12px;
            background-color: #d5c6f5;
            font-weight: bold;
            font-size: 16px;
            letter-spacing: 3px;
            cursor: pointer;
            margin: 0 auto;
            display: block;
        }

        .submit-button:hover {
            background-color: #bda9f2;
        }
    </style>
</head>
<body>
<<<<<<< Updated upstream
	
    <div class="form-container">
        <form action="/register" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <div class="form-label">ID</div>
                <input type="text" name="email" class="form-input" required>
=======

    <div class="signup-container">
        <h2>会員登録</h2>

        <form id="signupForm" action="/register" method="post" enctype="multipart/form-data">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
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
>>>>>>> Stashed changes
            </div>

            <div class="form-group"> 
                <div class="form-label">PWD</div>
                <input type="password" name="password" class="form-input" required>
            </div>

            <div class="form-group">
                <div class="form-label">生年月日</div>
                <input type="date" name="birthdate" required />
            </div>

            <div class="form-group">
                <div class="form-label">ニックネーム</div>
                <input type="text" name="nickname" class="form-input" required>
            </div>

			<div class="form-group">
			    <div class="form-label">性別</div>
			    <select name="gender" class="form-input" required>
			        <option value="">選択してください</option>
			        <option value="male">男性</option>
			        <option value="female">女性</option>
			    </select>
			</div>
			
            <div class="form-group">
                <div class="form-label">image</div>
                <input type="file" name="profileImage" class="form-input">
            </div>

            <button type="submit" class="submit-button">登 録</button>
        </form>
    </div>
</body>
</html>
