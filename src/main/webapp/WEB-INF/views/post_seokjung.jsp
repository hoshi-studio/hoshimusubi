<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/postseokjung.css" />

<div class="container">

    <!-- 별자리 아이콘 리스트 -->
<div class="zodiac-grid">
    <c:forEach var="zodiac" items="${zodiacs}">
        <div class="zodiac-item">
            <a href="${pageContext.request.contextPath}/zodiac/${zodiac.id}" 
               class="zodiac-link ${zodiac.id == selectedZodiacId ? 'active-zodiac' : ''}">
                <img src="${pageContext.request.contextPath}${zodiac.symbol}" 
                     alt="${zodiac.koname_ja}" class="zodiac-icon" />
            </a>
            <div class="zodiac-name">${zodiac.koname_ja}</div>
        </div>
    </c:forEach>
</div>

<!-- 버튼 영역 -->
<div class="board-controls">
    <a href="${pageContext.request.contextPath}/post_write" class="btn-create">作成</a>
    <select class="sort-select" onchange="location.href='?sort=' + this.value">
        <option value="recent" ${currentSort eq 'recent' ? 'selected' : ''}>最新順</option>
        <option value="like" ${currentSort eq 'like' ? 'selected' : ''}>いいね順</option>
        <option value="view" ${currentSort eq 'view' ? 'selected' : ''}>閲覧順</option>
    </select>
</div>

    <!-- 게시글 테이블 -->
    <table class="post-table">
        <thead>
        <tr>
            <th>Date</th>
            <th>作成者</th>
            <th>題目</th>
            <th>照会数</th>
            <th>Like</th>
            <th>コメント</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="post" items="${posts}">
            <tr>
                <td>${post.createdAt}</td>
                <td>${post.nickname}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/post_detail?id=${post.id}">${post.title}</a>
                </td>
                <td>${post.views}</td>
                <td>${post.likeCount}</td>
                <td>${post.commentCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- 페이지네이션 -->
<div class="pagination">
    <!-- 이전 페이지 화살표 -->
    <c:if test="${currentPage > 1}">
        <a href="?sort=${currentSort}&page=${currentPage - 1}" class="arrow">&#x25C0;</a>
    </c:if>

    <!-- 페이지 번호 -->
    <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="?sort=${currentSort}&page=${i}" class="page-link ${i == currentPage ? 'active' : ''}">${i}</a>
    </c:forEach>

    <!-- 다음 페이지 화살표 -->
    <c:if test="${currentPage < totalPages}">
        <a href="?sort=${currentSort}&page=${currentPage + 1}" class="arrow">&#x25B6;</a>
    </c:if>
</div>
</div>

<%@ include file="footer.jsp" %>
