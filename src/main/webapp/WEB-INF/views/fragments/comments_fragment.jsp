<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty comments}">
<!-- 댓글 목록 -->
<c:forEach var="comment" items="${comments}">
    <div class="comment" id="comment-${comment.id}">
        <strong>${comment.nickname}</strong>:
        <span class="comment-content">${comment.content}</span>

        <c:if test="${loginUserId eq comment.userId}">
            <div class="comment-actions">
                <a href="javascript:void(0);" class="edit-btn" data-id="${comment.id}">修整</a>
                <a href="javascript:void(0);" class="delete-btn" data-id="${comment.id}">削除</a>
            </div>
        </c:if>
    </div>
</c:forEach>

<!-- 댓글 페이징 -->
<div class="comment-pagination" style="margin-top: 15px;">
    <c:if test="${currentPage > 1}">
        <a href="#" class="comment-page-link" data-page="${currentPage - 1}">◀ 以前</a>
    </c:if>

    <span style="margin: 0 10px;">${currentPage} / ${totalPages}</span>

    <c:if test="${currentPage < totalPages}">
        <a href="#" class="comment-page-link" data-page="${currentPage + 1}">次 ▶</a>
    </c:if>
</div>
</c:if>
<c:if test="${empty comments}">
    <p style="text-align:center; color: #999;">まだ登録されたコメントがありません。</p>
</c:if>