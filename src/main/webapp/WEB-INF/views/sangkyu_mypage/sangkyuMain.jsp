<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>星結び - マイページ</title>
    <link rel="stylesheet" href="/resources/css/mypage.css">
</head>
<body style="background: url('/resources/img/mypage_scene.PNG'); background-size: cover;">
    <div class="mypage-container">
        <div class="profile">
            <img src="${profile.profilePic}" alt="프로필" class="profile-pic">
            <div class="nickname">${profile.nickname}</div>
            <div class="info">
                <div>性別: ${profile.gender}</div>
                <div>イーメール: ${profile.email}</div>
                <div>星座: ${profile.zodiacName}</div>
            </div>
        </div>
        <div class="stats">
            <a href="/sangkyu/posts" class="stat-btn">掲示文 <span>${postCount}</span></a>
            <a href="/sangkyu/comments" class="stat-btn">コメント <span>${commentCount}</span></a>
            <a href="/sangkyu/likes" class="stat-btn">like <span>${likeCount}</span></a>
            <a href="/sangkyu/scraps" class="stat-btn">スクラップ <span>${scrapCount}</span></a>
        </div>
    </div>
</body>
</html>
