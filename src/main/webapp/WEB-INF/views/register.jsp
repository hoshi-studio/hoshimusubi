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

<!-- 회원가입을 진행했는데 이메일 중복되면 alert(중복되었습니다.) 띄워져야하고-->
<!-- 비밀번호 8글자이상 특수문자들어가게만 --> 
<body>
	
    <div class="form-container">
        <form action="/register" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <div class="form-label">ID</div>
                <input type="text" name="email" class="form-input" required>
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
