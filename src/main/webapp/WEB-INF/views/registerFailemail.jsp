<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>登録失敗</title>
    <script>
        window.onload = function () {
            alert("重複されたメールがあります。\n登録できません。");
            window.history.back();
        };
    </script>
</head>
<body></body>
</html>