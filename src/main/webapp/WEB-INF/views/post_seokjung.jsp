<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="header.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/postseokjung.css" />

<div class="wrapper">

  <!-- 상단 로고 -->
  <div style="text-align: center; margin-top: 20px;">
    <img src="${pageContext.request.contextPath}/img/logo.png" alt="星結び" style="height: 40px;" />
    <h2 style="margin-top: 5px;">星結び</h2>
  </div>

  <!-- 별자리 선택 아이콘 영역 -->
  <div style="display: flex; flex-wrap: wrap; justify-content: center; margin: 30px 0;">
    <c:forEach var="zodiac" items="${zodiacList}">
      <a class="zodiac-link" href="${pageContext.request.contextPath}/zodiac/${zodiac.id}">
        <img src="${pageContext.request.contextPath}/img/zodiac/${zodiac.codename}.png" alt="${zodiac.koname_ja}" />
        <div>${zodiac.koname_ja}</div>
      </a>
    </c:forEach>
  </div>

  <!-- 정렬/작성 컨트롤 -->
  <div style="text-align: right; margin-right: 20px; margin-bottom: 10px;">
    <a href="${pageContext.request.contextPath}/post/create">
      <button>作成</button>
    </a>
    <select name="sort" onchange="location.href='?sort=' + this.value;">
      <option value="newest">最新順</option>
      <option value="popular">人気順</option>
    </select>
  </div>

  <!-- 게시글 테이블 -->
  <table style="width: 90%; margin: 0 auto; border-collapse: collapse;">
    <thead>
      <tr style="background-color: #f0e6fa;">
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
        <tr style="text-align: center; border-bottom: 1px solid #ddd;">
          <td><fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd" /></td>
          <td>${post.nickname}</td>
          <td style="text-align: left;">
            <a href="${pageContext.request.contextPath}/post/${post.id}">
              ${post.title}
            </a>
          </td>
          <td>${post.views}</td>
          <td>${post.likeCount}</td>
          <td>${post.commentCount}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <!-- 페이지네이션 -->
  <div style="text-align: center; margin: 20px 0;">
    <a href="?page=1">&lt;</a>
    <c:forEach begin="1" end="5" var="page">
      <a href="?page=${page}" style="margin: 0 10px;">${page}</a>
    </c:forEach>
    <a href="?page=6">&gt;</a>
  </div>

  <%@ include file="footer.jsp" %>
</div>
