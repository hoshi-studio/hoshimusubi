<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>星結び - 一覧</title>
    <link rel="stylesheet" href="/resources/css/mypage.css">
</head>
<body style="background: url('/resources/img/mypage_item_background.PNG'); background-size: cover;">
    <div class="list-container">
        <table class="mypage-list">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>題目</th>
                    <th>照会数</th>
                    <th>like</th>
                    <th>コメント</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${list}">
                    <tr>
                        <td><fmt:formatDate value="${item.createdAt}" pattern="yyyy-MM-dd"/></td>
                        <td>${item.title}</td>
                        <td>${item.viewCount}</td>
                        <td>${item.likeCount}</td>
                        <td>${item.commentCount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!-- 필요시 뒤로가기 버튼 등 추가 -->
    </div>
</body>
</html>
